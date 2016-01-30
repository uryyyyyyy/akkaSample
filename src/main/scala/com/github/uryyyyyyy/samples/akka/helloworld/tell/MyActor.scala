package com.github.uryyyyyyy.samples.akka.helloworld.tell

import akka.actor.Actor

class MyActor extends Actor {
	def receive = {
		case x => println(x)
	}
}