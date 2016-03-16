package com.github.uryyyyyyy.samples.akka

import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.github.uryyyyyyy.samples.akka.helloworld.ask.MyActor

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object HelloAkkaSpec {

	def main(args: Array[String]) :Unit = {
		val timeout = Timeout(5000, TimeUnit.MILLISECONDS)
		val system = ActorSystem()
		val actor = system.actorOf(Props[MyActor])
		val res:Future[Any] = actor.ask("HelloWorld!")(timeout)
		res.mapTo[String].map(v => println(v))
		Await.result(res, Duration.Inf)
		system.shutdown()
	}

}
