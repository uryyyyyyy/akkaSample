package com.github.uryyyyyyy.samples.akka.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.Directives
import akka.stream.ActorMaterializer

/**
  * Created by shiba on 16/03/16.
  */
class Main {

}

object Main {

  def main(args: Array[String]) {
    implicit val actorSystem = ActorSystem("my-system")
    implicit val flowMaterializer = ActorMaterializer()

    import Directives._

    val route = get {
      pathEndOrSingleSlash {
        getFromFile("path/to/index.html")
      }
    } ~ path("test") {
      get {
        handleWith((a: HttpRequest) => s"your request is\n\n${a.headers.mkString("\n")}")
      }
    }

    val serverBinding = Http(actorSystem).bindAndHandle(route ,interface = "localhost", port = 8080)
  }
}