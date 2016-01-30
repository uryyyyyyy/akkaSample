package com.github.uryyyyyyy.samples.akka.sample.sequential

import akka.actor.Actor
import akka.event.Logging

class MyActor extends Actor {
	val log = Logging(context.system, this)
	def receive = {
		case i:Int => {
			log.info(i.toString)
			MyCounter.add(i)
		}
	}
}