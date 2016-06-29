package com.github.uryyyyyyy.akka.stream.helloworld

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object MatSample2 {

  def main (args: Array[String]) {
    val system = ActorSystem.create("sample")
    implicit val materializer = ActorMaterializer.create(system)

    val foldSink: Sink[Int, Future[String]] = Sink.fold(""){ _ + _.toString }
    val f: Future[String] = Source(1 to 10).runWith(foldSink)

    val res = Await.result(f, Duration.Inf)
    println(res)
    system.terminate()
  }
}