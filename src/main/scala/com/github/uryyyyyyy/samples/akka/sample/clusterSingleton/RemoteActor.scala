package com.github.uryyyyyyy.samples.akka.sample.clusterSingleton

import akka.actor.Actor

class RemoteActor extends Actor {
	override def receive: Receive = {
		case msg: String => println(s"receive msg: $msg")
	}
}