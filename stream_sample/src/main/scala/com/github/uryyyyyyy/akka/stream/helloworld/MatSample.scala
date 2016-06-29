package com.github.uryyyyyyy.akka.stream.helloworld

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.stream.ActorMaterializer
import akka.stream.actor.ActorPublisher
import akka.stream.scaladsl._

object MatSample {

	def main (args: Array[String]) {
		val system = ActorSystem.create("sample")
		implicit val materializer = ActorMaterializer.create(system)

		val source: Source[Int, ActorRef] = Source.actorPublisher[Int](Props[SampleActor])
		val rg: RunnableGraph[ActorRef] = source.to(Sink.foreach(println))
		val actor = rg.run()

		Thread.sleep(1000) // 調整のためのsleep

		actor ! Message(1)
		actor ! Message(2)
		actor ! Message(3)
		actor ! END

		system.terminate()
	}
}


case class Message(n: Int)
case object END

class SampleActor extends ActorPublisher[Int] {
	def receive = {
		case Message(n) => onNext(n)
		case END => onComplete() // ※ちゃんと終了シグナルを送らないとfoldのような処理がDownstreamにいた場合、どこがデータの終了かわからなくなってfoldが終わらなくなる
	}
}