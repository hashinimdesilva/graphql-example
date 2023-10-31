package api.routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import graphql.GraphQLServer
import spray.json.JsValue

import scala.concurrent.ExecutionContext.Implicits.global

object UserRoute {

  def route(): Route = concat(
    pathPrefix("graphql") {
      pathEnd {
        post {
          entity(as[JsValue]) { requestJson =>
            GraphQLServer.endpoint(requestJson)
          }
        }
      }
    }
  )
}