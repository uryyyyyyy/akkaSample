package com.github.uryyyyyyy.samples.akka.helloworld.pingpong

import akka.actor.{Actor, Props}
import MyMessage.{ExecuteMessage, ReturnMessage}
import com.github.uryyyyyyy.samples.akka.helloworld.pingpong.MyMessage.ExecuteMessage

class ParentActor extends Actor {
	def receive = {
		case ExecuteMessage => {
			println("ParentActor - execute 100 * 2 and print")
			val actor = context.actorOf(Props[ChildActor])
			actor.tell(100, self)
		}
		case ReturnMessage(s) => {
			println(s"ParentActor - result is $s")
		}
	}
}