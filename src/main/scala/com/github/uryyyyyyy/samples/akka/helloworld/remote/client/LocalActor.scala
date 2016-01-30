package com.github.uryyyyyyy.samples.akka.helloworld.remote.client

import akka.actor.Actor

class LocalActor extends Actor {

	override def receive: Receive = {
		case x => {
			println("post to remote")
			val remoteActor = context.actorSelection("akka.tcp://remote@127.0.0.1:5150/user/remote")
			remoteActor ! "hello"
		}
	}
}