package com.github.uryyyyyyy.akka.helloworld.router

import akka.actor.Actor

class MyActor extends Actor {
	def receive = {
		case x => {
			Thread.sleep(1000)
			println(s"MyActor - $x")
		}
	}
}