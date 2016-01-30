package com.github.uryyyyyyy.samples.akka.sample.exception

import java.io.IOException

import akka.actor.SupervisorStrategy.{Resume, Restart, Escalate}
import akka.actor.{Actor, OneForOneStrategy, Props}

import scala.concurrent.duration.DurationInt

class ParentActor extends Actor {

	override val supervisorStrategy =OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = new DurationInt(1).second ) {
		case _: IOException => Resume
		case _: Exception => Escalate
	}

	def receive = {
		case x => {
			val actor1 = context.actorOf(Props[ChildActor1])
			val actor2 = context.actorOf(Props[ChildActor2])
//			val actor3 = context.actorSelection("akka.tcp://notExist")
			val f1 = actor1 ! "hello"
			val f2 = actor2 ! "hello"
//			val f3 = actor3 ! "hello"
		}
	}
}