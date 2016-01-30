package com.github.uryyyyyyy.samples.akka.helloworld.pingpong

import akka.actor.Actor
import MyMessage.ReturnMessage

class ChildActor extends Actor {
	def receive = {
		case i:Int => {
			println("ChildActor - calc 100 * 2")
			val res = ReturnMessage(i * 2)
			sender ! res
		}
	}
}