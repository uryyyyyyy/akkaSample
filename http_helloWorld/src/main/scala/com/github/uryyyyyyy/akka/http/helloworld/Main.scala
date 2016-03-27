package com.github.uryyyyyyy.akka.http.helloworld

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.Directives
import akka.stream.ActorMaterializer

object Main {

	def main(args: Array[String]) {
		implicit val actorSystem = ActorSystem("my-system")
		implicit val flowMaterializer = ActorMaterializer()

		import Directives._

		val route = get {
			pathEndOrSingleSlash {
				getFromResource("assets/index.html")
			}
		} ~ path("test") {
			get {
				handleWith((a: HttpRequest) => s"your request is\n\n${a.headers.mkString("\n")}")
			}
		}

		val serverBinding = Http(actorSystem).bindAndHandle(route ,interface = "localhost", port = 8080)
	}
}