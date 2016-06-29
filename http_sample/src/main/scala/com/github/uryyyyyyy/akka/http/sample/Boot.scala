package com.github.uryyyyyyy.akka.http.sample

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import akka.stream.ActorMaterializer

import scala.collection.JavaConverters._

object Boot extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()

  val myService = new MyService()

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  Http().bindAndHandle(myService.route, "localhost", 8080)
}

import java.util.concurrent.ConcurrentHashMap

class MyService {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()

  val piyoMap = new ConcurrentHashMap[Int, Map[String, List[String]]]().asScala

  import Directives._
  val route = path("hoge") {
    (get | post) {
      complete("OK")
    }
  } ~ pathPrefix("piyo" / ".+".r) { str =>
    pathEnd {
      sys.error("bomb!!")
    } ~ path(IntNumber) { num =>
      get {
        val piyoOpt = piyoMap.get(num)
        complete(s"${str}, ${num}, ${piyoOpt}")
      } ~ post {
        formFieldMultiMap { form =>
          piyoMap.put(num, form.toMap)
          complete(s"${str}, ${num}, ${form}")
        }
      }
    }
  }
}