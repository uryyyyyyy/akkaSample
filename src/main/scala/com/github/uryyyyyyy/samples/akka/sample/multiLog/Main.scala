package com.github.uryyyyyyy.samples.akka.sample.multiLog

import akka.actor.{ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object Main {

	def main(args: Array[String]) :Unit = {
		//read conf from "application.conf"
		val config = ConfigFactory.load("sample_log_application1")
		println(config.getAnyRef("akka.loglevel"))
		val system = ActorSystem("sampleActor", config)
		val actor = system.actorOf(Props[MyActor])
		actor.tell("HelloWorld1!", ActorRef.noSender)


		val config2 = ConfigFactory.load("sample_log_application2")
		println(config2.getAnyRef("akka.loglevel"))
		val system2 = ActorSystem("sampleActor2", config2)
		val actor2 = system2.actorOf(Props[MyActor])
		actor2.tell("HelloWorld2!", ActorRef.noSender)

		system.shutdown()
		system2.shutdown()
	}

}