//package com.github.uryyyyyyy.akka.persistence.helloworld.query
//
//import akka.NotUsed
//import akka.actor.ActorSystem
//import akka.persistence.query.scaladsl.ReadJournal
//import akka.persistence.query.{EventEnvelope, PersistenceQuery}
//import akka.stream.ActorMaterializer
//import akka.stream.scaladsl.Source
//import com.github.uryyyyyyy.akka.persistence.helloworld.ExamplePersistentActor
//
//object ReadQuery {
//
//	def main(args: Array[String]) {
//		val system = ActorSystem("example")
//
//		// obtain read journal by plugin id
//		val readJournal:ReadJournal =
//			PersistenceQuery(system).readJournalFor[ExamplePersistentActor](
//				"akka.persistence.query.my-read-journal")
//
//		// issue query to journal
//		val source: Source[EventEnvelope, NotUsed] =
//			readJournal.eventsByPersistenceId("user-1337", 0, Long.MaxValue)
//
//		// materialize stream, consuming events
//		implicit val mat = ActorMaterializer()
//		source.runForeach { event => println("Event: " + event) }
//	}
//
//}
