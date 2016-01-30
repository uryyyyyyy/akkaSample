package com.github.uryyyyyyy.samples.akka.helloworld.log

import akka.actor.{ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object Main {

	def main(args: Array[String]) :Unit = {
		val config = ConfigFactory.load("application")
		val system = ActorSystem("sampleActor", config)
		val actor = system.actorOf(Props[MyActor])
		actor.tell("HelloWorld!", ActorRef.noSender)
		system.terminate()
	}

}