package com.github.uryyyyyyy.akka.helloworld.log

import akka.actor.Actor
import akka.event.Logging

class MyActor extends Actor {
	val log = Logging(context.system, this)
	def receive = {
		case x:String => {
			log.info(x)
			log.warning(x)
		}
	}
}