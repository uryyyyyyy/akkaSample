package com.github.uryyyyyyy.samples.akka.helloworld.tell

import akka.actor.{ActorRef, ActorSystem, Props}

object Main {

	def main(args: Array[String]) :Unit = {
		val system = ActorSystem()
		val actor = system.actorOf(Props[MyActor])
		val res:Unit = actor.tell("HelloWorld!", ActorRef.noSender)
		val res2:Unit = actor ! "HelloWorld!"
		system.terminate()
	}

}