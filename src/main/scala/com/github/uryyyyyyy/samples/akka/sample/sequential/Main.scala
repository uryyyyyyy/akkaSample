package com.github.uryyyyyyy.samples.akka.sample.sequential

import akka.actor.{ActorSystem, Props}

object Main {

	//	def main(args: Array[String]) :Unit = {
	//		val list = (1 to 10).toSeq
	//		val system = ActorSystem()
	//		val actor = system.actorOf(Props[MyActor])
	//		list.par.foreach(v => {
	//			actor ! v
	//		})
	//	}

	def main(args: Array[String]) :Unit = {
		val list = (1 to 10).toSeq
		MyCounter.reset()
		incorrectMode(list)
		println(MyCounter.getVal())

		MyCounter.reset()
		actorMode(list)
		println(MyCounter.getVal())
	}

	def incorrectMode(list:Seq[Int]) :Unit = {
		list.par.foreach(i => MyCounter.add(i))
	}

	def actorMode(list:Seq[Int]) :Unit = {
		val system = ActorSystem()
		val actor = system.actorOf(Props[MyActor])
		list.par.foreach(i => actor ! i)
		Thread.sleep(2000)
		system.shutdown()
	}

}