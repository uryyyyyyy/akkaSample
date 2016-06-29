package com.github.uryyyyyyy.akka.stream.helloworld

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.stream.ActorMaterializer
import akka.stream.actor.ActorPublisher
import akka.stream.scaladsl.{Keep, RunnableGraph, Sink, Source}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

object MatSample3 {

  def main (args: Array[String]) {
    val system = ActorSystem.create("sample")
    implicit val materializer = ActorMaterializer.create(system)

    val source: Source[Int, ActorRef] = Source.actorPublisher[Int](Props[SampleActor])
    val sink: Sink[Int, Future[String]] = Sink.fold[String,Int](""){ _ + _.toString }

    val rg: RunnableGraph[(ActorRef, Future[String])] = source.toMat(sink)(Keep.both)

    val (actor, f) = rg.run()

    f.map(println)

    Thread.sleep(1000)

    actor ! Message(1)
    actor ! Message(2)
    actor ! Message(3)
    actor ! END

    Await.ready(f, Duration.Inf)

    system.terminate()
  }
}