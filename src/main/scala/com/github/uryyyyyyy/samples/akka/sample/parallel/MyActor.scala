package com.github.uryyyyyyy.samples.akka.sample.parallel

import akka.actor.Actor
import akka.event.Logging

class MyActor extends Actor {
	val log = Logging(context.system, this)

	def receive = {
		case i:Int => {
			val res = Util.heavyMethod(i)
			log.info(res.toString)
			sender().tell(res, self)
		}
	}
}