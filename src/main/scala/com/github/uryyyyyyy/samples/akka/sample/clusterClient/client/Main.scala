package com.github.uryyyyyyy.samples.akka.sample.clusterClient.client

import akka.actor.ActorSystem
import akka.cluster.client.{ClusterClientSettings, ClusterClient}
import com.typesafe.config.ConfigFactory

import scala.collection.JavaConversions._

object Main {

	def main(args: Array[String]) :Unit = {
		val config = ConfigFactory.load("sample_clusterClient_application")
		val system = ActorSystem("client", config)
		val nodeList = config.getStringList("akka.my-target-cluster-node")
		val initialContacts = nodeList.map(v => system.actorSelection(s"$v/user/receptionist")).toSet

		val sett = ClusterClientSettings(system)
		val c = system.actorOf(ClusterClient.props(sett))
		c ! ClusterClient.Send("/user/remote", "hello1", false)
		c ! ClusterClient.Send("/user/remote", "hello2", false)
		c ! ClusterClient.SendToAll("/user/remote", "hello3")
	}

}