package com.github.uryyyyyyy.akka.helloworld.router

import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinPool

object Main {

	def main(args: Array[String]) :Unit = {
		val system = ActorSystem()
		val router = system.actorOf(RoundRobinPool(2).props(Props[MyActor]))
		(1 to 10).foreach(router ! _)
		Thread.sleep(6000)
		system.terminate()
	}

}