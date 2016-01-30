package com.github.uryyyyyyy.samples.akka.helloworld.remote.remote

import akka.actor.Actor

class RemoteActor extends Actor {
	override def receive: Receive = {
		case msg: String => println(s"receive msg: $msg")
	}
}