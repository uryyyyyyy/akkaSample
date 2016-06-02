package com.github.uryyyyyyy.akka.helloworld.faultTolerance

import akka.actor.{ActorSystem, Props}

object Main {

	def main(args: Array[String]) :Unit = {
		val system = ActorSystem()
		val actor = system.actorOf(Props[ParentActor])
		actor ! "exec"
		Thread.sleep(10000)
		system.terminate()
	}

}