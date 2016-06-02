package com.github.uryyyyyyy.akka.cluster.remote

import akka.actor.Actor
import com.github.uryyyyyyy.akka.cluster.common.Massages.Hello

class RemoteActor extends Actor {
	override def receive: Receive = {
		case Hello(name) => println(s"receive msg: $name")
	}
}