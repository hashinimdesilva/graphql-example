package graphql

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCode
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import persistence.model.dao.implementation.{DefaultPostDAO, DefaultUserDAO}
import persistence.model.dao.{PostDAO, UserDAO}
import sangria.ast.Document
import sangria.execution._
import sangria.marshalling.sprayJson._
import sangria.parser.QueryParser
import spray.json.{JsObject, JsString, JsValue}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object GraphQLServer {

  private val userDAO: UserDAO = new DefaultUserDAO
  private val postDAO: PostDAO = new DefaultPostDAO

  def endpoint(requestJSON: JsValue)(implicit ec: ExecutionContext): Route = {
    val JsObject(fields) = requestJSON
    val JsString(query) = fields("query")

    QueryParser.parse(query) match {
      case Success(queryAst) =>
        val operation = fields.get("operationName") collect {
          case JsString(op) => op
        }

        val variables = fields.get("variables") match {
          case Some(obj: JsObject) => obj
          case _ => JsObject.empty
        }
        complete(executeGraphQLQuery(queryAst, operation, variables))
      case Failure(error) =>
        complete(BadRequest, JsObject("error" -> JsString(error.getMessage)))
    }

  }

  private def executeGraphQLQuery(query: Document, operation: Option[String], vars: JsObject)(implicit ec: ExecutionContext): Future[(StatusCode, JsValue)] = {
    Executor.execute(
        SchemaDefinition.userSchema,
        query,
        MyContext(userDAO, postDAO),
        variables = vars,
        operationName = operation
      ).map(OK -> _)
      .recover {
        case error: QueryAnalysisError => BadRequest -> error.resolveError
        case error: ErrorWithResolver => InternalServerError -> error.resolveError
      }
  }

}