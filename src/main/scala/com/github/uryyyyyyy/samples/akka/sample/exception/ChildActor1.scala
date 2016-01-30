package com.github.uryyyyyyy.samples.akka.sample.exception

import akka.actor.Actor

class ChildActor1 extends Actor {
	def receive = {
		case x => {
			println(s"MyActor - $x")
		}
	}
}