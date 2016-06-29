package com.github.uryyyyyyy.akka.stream.helloworld

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.{Done, NotUsed}

import scala.concurrent.Future

object Start2 {

	def main (args: Array[String]) {
		implicit val system = ActorSystem()

		import GraphDSL.Implicits._

		implicit val materializer = ActorMaterializer()
		val source: Source[Int, NotUsed] = Source(List(1, 2, 3))
		val sink: Sink[String, Future[Done]] = Sink.foreach[String](println)
		val flow1: Flow[Int, Int, NotUsed] = Flow[Int].map(_ * 2)
		val flow2: Flow[Int, String, NotUsed] = Flow[Int].map(_.toString + " haha").limit(10)

		val g = RunnableGraph.fromGraph(GraphDSL.create() { implicit builder: GraphDSL.Builder[NotUsed] =>
				source ~> flow1 ~> flow2 ~> sink
				ClosedShape
		})
		val result = g.run()
		println(result)
		system.terminate()
	}
}
