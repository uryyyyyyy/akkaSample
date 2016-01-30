package com.github.uryyyyyyy.samples.akka.stream

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, ClosedShape}
import akka.stream.scaladsl._

object Start2 {

	def main (args: Array[String]) {
		implicit val system = ActorSystem()
		implicit val mat = ActorMaterializer()

		val g = RunnableGraph.fromGraph(GraphDSL.create() { implicit builder: GraphDSL.Builder[Unit] =>
			import GraphDSL.Implicits._
			val in = Source(1 to 10)
			val out = Sink.foreach[Int](println)

			val bcast = builder.add(Broadcast[Int](2))
			val merge = builder.add(Merge[Int](2))

			val f1 = Flow[Int].map(_ + 10)
			val f2 = Flow[Int].map(_ * 2)
			val f3 = Flow[Int].map(_ - 1)
			val f4 = Flow[Int].map(_ + 1000)

			in ~> f1 ~> bcast ~> f2 ~> merge ~> f3 ~> out
			bcast ~> f4 ~> merge
			ClosedShape
		})
		g.run()
		system.shutdown()
	}
}
