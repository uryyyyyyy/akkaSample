package com.github.uryyyyyyy.akka.cluster.helloWorld.remote

import akka.cluster.Cluster
import akka.cluster.ClusterEvent._
import akka.actor.{Props, ActorSystem, ActorLogging, Actor}
import com.typesafe.config.ConfigFactory

class Main extends Actor with ActorLogging {

	val cluster = Cluster(context.system)

	// subscribe to cluster changes, re-subscribe when restart
	override def preStart(): Unit = {
		//#subscribe
		cluster.subscribe(self, initialStateMode = InitialStateAsEvents,
			classOf[MemberEvent], classOf[UnreachableMember])
		//#subscribe
	}
	override def postStop(): Unit = cluster.unsubscribe(self)

	def receive = {
		case MemberUp(member) =>
			log.info("Member is Up: {}", member.address)
		case UnreachableMember(member) =>
			log.info("Member detected as unreachable: {}", member)
		case MemberRemoved(member, previousStatus) =>
			log.info("Member is Removed: {} after {}",
				member.address, previousStatus)
		case _: MemberEvent => // ignore
	}
}

object Main{
	def main(args: Array[String]) {
		val config = ConfigFactory.load("sample_clusterRemote_application")
		val system = ActorSystem("client", config)
		val actor = system.actorOf(Props[Main], name="remote")
	}
}