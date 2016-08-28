package com.github.uryyyyyyy.akka.http.sample

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.Future

object Main {
  def main(args: Array[String]): Unit = {

    val port = if(args.length == 0) 8080 else args(0).toInt

    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val ec = system.dispatcher

    val route = path("") {
      get {
        complete("ok")
      }
    } ~ get {
      path("cookie.html") {
        getFromResource("cookie.html")
      } ~ path("1stPartyTracking.js") {
        getFromResource("1stPartyTracking.js")
      }
    } ~ post {
      path("tracking") {
        formFields('trackingID) { trackingID =>
          println(trackingID)
          complete("tracked")
        }
      }
    } ~ get {
      pathPrefix("api") {
        path("execute" / IntNumber) { num =>
          Logic.validateCustomHeader{ _ =>
            req => Logic.execute(num, req)
          }
        }
      }
    }

    val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(route, "127.0.0.1", port)

    println(s"Server online at http://127.0.0.1:${port}/\nPress RETURN to stop...")
    scala.io.StdIn.readLine()

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}