package api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import api.routes.UserRoute
import util.DefaultConfiguration

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

class Server {

  implicit val system: ActorSystem = ActorSystem("graphql-server")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  private val port = DefaultConfiguration.HTTP_PORT
  private val host = DefaultConfiguration.HTTP_HOST

  def createServer(): Unit = {
    val server = Http().newServerAt(host, port).bind(UserRoute.route())

    server onComplete {
      case Success(_) =>
        println("Successfully started the server")
      case Failure(exception) =>
        println("An error occurred when starting the server", exception)
    }
  }
}
