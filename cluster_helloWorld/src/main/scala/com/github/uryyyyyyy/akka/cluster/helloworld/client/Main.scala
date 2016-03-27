package com.github.uryyyyyyy.akka.cluster.helloworld.client

import akka.actor.ActorSystem
import akka.cluster.client.{ClusterClientSettings, ClusterClient}
import com.github.uryyyyyyy.akka.cluster.helloworld.common.Massages.Hello
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
		c ! ClusterClient.Send("/user/remote", Hello("hello1"), false)
		c ! ClusterClient.Send("/user/remote", Hello("hello2"), false)
		c ! ClusterClient.SendToAll("/user/remote", Hello("hello3"))
	}

}