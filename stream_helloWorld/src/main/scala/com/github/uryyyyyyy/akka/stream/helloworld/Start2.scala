package com.github.uryyyyyyy.akka.stream.helloworld

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._

import scala.concurrent.Await
import scala.concurrent.duration._

object Start2 {

	def main (args: Array[String]) {
		implicit val system = ActorSystem()

		import GraphDSL.Implicits._

		implicit val materializer = ActorMaterializer()
		val source = Source(List(1, 2, 3))
		val sink = Flow[Int].limit(10).toMat(Sink.seq)(Keep.right)
		val step1 = Flow[Int].map(_ * 2)

		val g = RunnableGraph.fromGraph(GraphDSL.create(sink) { implicit b =>
			s =>
				source ~> step1 ~> s.in
				ClosedShape
		})
		val result = Await.result(g.run(), 1.second)
		println(result)
		system.terminate()
	}
}
