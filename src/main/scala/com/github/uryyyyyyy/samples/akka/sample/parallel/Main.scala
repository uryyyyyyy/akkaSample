package com.github.uryyyyyyy.samples.akka.sample.parallel

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object Main {

	def main(args: Array[String]) :Unit = {
		val list = (1 to 10).toSeq
		normalMode(list)
		actorMode(list)
	}

	def normalMode(list:Seq[Int]) :Unit = {
		printExecutionTime{
			val str = list.map(v => Util.heavyMethod(v))
				.foldLeft("")((l, r) => l + "\n"+ r)
			println(str)
		}
	}

	def actorMode(list:Seq[Int]) :Unit = {
		val timeout = Timeout.intToTimeout(1000)
		val system = ActorSystem()
		printExecutionTime{
			val futureList = list.map(v => {
				val actor = system.actorOf(Props[MyActor])
				actor.ask(v)(timeout).mapTo[Int]
			})
			val str = Await.result(Future.sequence(futureList), Duration.Inf)
				.foldLeft("")((l, r) => l + "\n"+ r)
			println(str)
		}
		system.shutdown()
	}

	def printExecutionTime(func: => Unit) = {
		val start = System.currentTimeMillis
		func
		println((System.currentTimeMillis - start) + "ms")
	}
}