package com.github.uryyyyyyy.samples.akka.helloworld.remote.remote

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object Main {

	def main(args: Array[String]) :Unit = {
		val config = ConfigFactory.load("hello_remote_application")
		val system = ActorSystem("remote", config)
		val actor = system.actorOf(Props[RemoteActor], name="remote")
		println(actor.path.toStringWithoutAddress)
		println("Remote is ready")
	}

}