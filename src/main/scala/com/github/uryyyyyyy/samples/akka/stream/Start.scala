package com.github.uryyyyyyy.samples.akka.stream

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Start {

	def main (args: Array[String]) {
		implicit val system = ActorSystem()
		implicit val mat = ActorMaterializer()

		val source = Source[Int](1 to 5) // Publisherのこと
		val sink = Sink.foreach[Int](println) // Subscriberのこと

		val f = source
				.map(_ * 2) // Stageのこと（このstep一つ一つでActorが立ち上がる）
				.runWith(sink) // Sinkで結果を受け取る

		Await.result(f, Duration.Inf)
		system.shutdown()
	}

}
