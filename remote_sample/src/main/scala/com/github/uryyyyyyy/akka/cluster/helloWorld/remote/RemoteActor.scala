package com.github.uryyyyyyy.akka.cluster.helloWorld.remote

import akka.actor.Actor
import com.github.uryyyyyyy.akka.cluster.helloWorld.common.Massages.Hello

class RemoteActor extends Actor {
	override def receive: Receive = {
		case Hello(name) => println(s"receive msg: $name")
	}
}