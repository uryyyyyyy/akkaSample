package com.github.uryyyyyyy.akka.helloworld.ask

import akka.actor.Actor

class MyActor extends Actor {
	def receive = {
		case x => sender().tell( s"MyActor - $x", self)
	}
}