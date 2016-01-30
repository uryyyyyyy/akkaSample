package com.github.uryyyyyyy.samples.akka.helloworld.pingpong

object MyMessage {
	case class ExecuteMessage()
	case class ReturnMessage(i:Int)
}