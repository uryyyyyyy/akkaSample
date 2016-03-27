package com.github.uryyyyyyy.akka.faultTolerance

import akka.actor.Actor

class ChildActor3 extends Actor {
	 def receive = {
		 case x => {
			 Thread.sleep(1000)
			 println(s"MyActor - $x")
		 }
	 }
 }