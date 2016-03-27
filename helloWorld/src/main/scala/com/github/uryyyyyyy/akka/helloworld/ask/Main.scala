package com.github.uryyyyyyy.akka.helloworld.ask

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, _}
import scala.concurrent.{Await, Future}

object Main {

	def main(args: Array[String]) :Unit = {
		val timeout = Timeout(1 seconds)
		val system = ActorSystem()
		val actor = system.actorOf(Props[MyActor])
		val res:Future[Any] = actor.ask("HelloWorld!")(timeout)
		res.mapTo[String].map(v => print(v))
		Await.result(res, Duration.Inf)
		system.terminate()
	}

}