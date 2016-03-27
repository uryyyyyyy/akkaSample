package com.github.uryyyyyyy.akka.cluster.helloworld.remote

import akka.actor.Actor
import com.github.uryyyyyyy.akka.cluster.helloworld.common.Massages.Hello

class RemoteActor extends Actor {
	override def receive: Receive = {
		case Hello(name) => println(s"receive msg: $name")
	}
}