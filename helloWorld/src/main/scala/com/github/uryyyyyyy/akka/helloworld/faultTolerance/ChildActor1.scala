package com.github.uryyyyyyy.akka.helloworld.faultTolerance

import akka.actor.Actor

class ChildActor1 extends Actor {
	def receive = {
		case x => {
			println(s"MyActor - $x")
		}
	}
}