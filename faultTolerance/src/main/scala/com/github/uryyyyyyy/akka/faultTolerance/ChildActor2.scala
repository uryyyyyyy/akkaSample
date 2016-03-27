package com.github.uryyyyyyy.akka.faultTolerance

import java.io.IOException

import akka.actor.Actor

class ChildActor2 extends Actor {
	var errorFlag=true

	def receive = {
		case x => {
			if(errorFlag){
				errorFlag=false
				println(s"MyActor - $x error")
				throw new IOException()
			}
			println(s"MyActor - $x success")
		}
	}
}