package com.github.uryyyyyyy.samples.akka.sample.clusterSingleton

import akka.actor.{ActorSystem, PoisonPill, Props}
import akka.cluster.singleton.{ClusterSingletonProxy, ClusterSingletonManager}
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object Main {

	def main(args: Array[String]) :Unit = {
		if(args.length == 0) {
			println("set cluster port num")
			return
		}

		val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + args(0))
			.withFallback(ConfigFactory.load("sample_clusterRemote_application"))

		val system = ActorSystem("myCluster", config)

		val actor = system.actorOf(ClusterSingletonManager.props(
			singletonProps = Props(classOf[RemoteActor]),
			singletonName = "active",
			terminationMessage = PoisonPill,
			role = None),
			name = "mySingletonActor")
		ClusterReceptionistExtension(system).registerService(actor)
		println(actor.path.toStringWithoutAddress)
		println("Remote is ready")


		val proxy = system.actorOf(ClusterSingletonProxy.props(
			singletonPath = "/user/mySingletonActor/active",
			role = None),
			name = "singletonProxy")

		system.scheduler.schedule(0 milliseconds,
			1 second,
			proxy,
			"tick")
	}
}