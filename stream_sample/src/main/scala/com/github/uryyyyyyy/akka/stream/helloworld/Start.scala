package com.github.uryyyyyyy.akka.stream.helloworld

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Start {

	def main (args: Array[String]) {
		implicit val system = ActorSystem()
		implicit val mat = ActorMaterializer()

		val source = Source[Int](1 to 5)
		val sink = Sink.foreach[String](println)

		val f = source
				.map(_.toString)
				.runWith(sink)

		Await.result(f, Duration.Inf)
		system.terminate()
	}

}
