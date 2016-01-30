package com.github.uryyyyyyy.samples.akka.sample.exception

import akka.actor.Actor

class ChildActor3 extends Actor {
	 def receive = {
		 case x => {
			 Thread.sleep(1000)
			 println(s"MyActor - $x")
		 }
	 }
 }