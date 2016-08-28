package com.github.uryyyyyyy.akka.http.sample

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.HttpCookie
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.Future
import scala.util.Random

object Main {
  def main(args: Array[String]): Unit = {

    val random = new Random()

    val port = if(args.length == 0) 8080 else args(0).toInt

    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val ec = system.dispatcher

    val route = path("") {
      get {
        complete("ok")
      }
    } ~ get {
      path("3rd_party_tracking.html") {
        getFromResource("3rd_party_tracking.html")
      } ~ path("1st_party_tracking.html") {
        getFromResource("1st_party_tracking.html")
      } ~ path("1stPartyTracking.js") {
        getFromResource("1stPartyTracking.js")
      }~ path("3rdPartyTracking.img") {
        optionalCookie("3rdPartyTrackingKey"){ pair =>
          val trackingID = if (pair.isEmpty){
            val id = random.nextDouble().toString
            println("3rd party cookie. new user: " + id)
            id
          }else{
            val id = pair.get.value
            println("3rd party cookie. same user: " + id)
            id
          }
          setCookie(HttpCookie("3rdPartyTrackingKey", trackingID)){
            getFromResource("tracking.png")
          }
        }
      }
    } ~ get {
      path("tracking") {
        parameter('trackingID) { trackingID =>
          println("1st party cookie" + trackingID)
          getFromResource("tracking.png")
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

    val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(route, "0.0.0.0", port)

    println(s"Server online at http://127.0.0.1:${port}/\nPress RETURN to stop...")
    scala.io.StdIn.readLine()

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}