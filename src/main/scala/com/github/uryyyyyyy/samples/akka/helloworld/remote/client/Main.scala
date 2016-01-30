package com.github.uryyyyyyy.samples.akka.helloworld.remote.client

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object Main {

	def main(args: Array[String]) :Unit = {
		val config = ConfigFactory.load("hello_client_application")
		val system = ActorSystem("client", config)
		val localActor = system.actorOf(Props[LocalActor], name = "local")
		localActor ! "start"
	}

}