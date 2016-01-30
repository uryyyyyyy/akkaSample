package com.github.uryyyyyyy.samples.akka.helloworld.pingpong

import akka.actor.{ActorRef, ActorSystem, Props}
import MyMessage.ExecuteMessage
import com.github.uryyyyyyy.samples.akka.helloworld.pingpong.MyMessage.ExecuteMessage

object Main {

	def main(args: Array[String]) :Unit = {
		val system = ActorSystem()
		val actor = system.actorOf(Props[ParentActor])
		actor.tell(ExecuteMessage, ActorRef.noSender)

		Thread.sleep(500)
		system.terminate()
	}

}