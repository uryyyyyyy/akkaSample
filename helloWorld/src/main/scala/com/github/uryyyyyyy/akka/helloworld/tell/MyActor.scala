package com.github.uryyyyyyy.akka.helloworld.tell

import akka.actor.Actor

class MyActor extends Actor {
	def receive = {
		case x => println(x)
	}
}