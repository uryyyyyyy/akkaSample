//package com.github.uryyyyyyy.akka.persistence.helloworld.query
//
//import java.util.concurrent.TimeUnit
//
//import akka.actor.ExtendedActorSystem
//import akka.persistence.query.{EventEnvelope, ReadJournalProvider}
//import akka.stream.scaladsl.Source
//import com.typesafe.config.Config
//
//import scala.concurrent.duration.FiniteDuration
//
//class MyReadJournalProvider(system: ExtendedActorSystem, config: Config)
//	extends ReadJournalProvider {
//
//	override val scaladslReadJournal: MyScaladslReadJournal =
//		new MyScaladslReadJournal(system, config)
//
//	override val javadslReadJournal: MyScaladslReadJournal = scaladslReadJournal
//}
//
//class MyScaladslReadJournal(system: ExtendedActorSystem, config: Config)
//	extends akka.persistence.query.scaladsl.ReadJournal
//		with akka.persistence.query.scaladsl.EventsByTagQuery
//		with akka.persistence.query.scaladsl.EventsByPersistenceIdQuery
//		with akka.persistence.query.scaladsl.AllPersistenceIdsQuery
//		with akka.persistence.query.scaladsl.CurrentPersistenceIdsQuery {
//
//	private val refreshInterval: FiniteDuration = {
//		val ss = config.getDuration("refresh-interval", TimeUnit.MILLISECONDS)
//		FiniteDuration(ss, TimeUnit.MILLISECONDS)
//	}
//
//	override def eventsByTag(
//		                        tag: String, offset: Long = 0L): Source[EventEnvelope, Unit] = {
//		val props = MyEventsByTagPublisher.props(tag, offset, refreshInterval)
//		Source.actorPublisher[EventEnvelope](props)
//			.mapMaterializedValue(_ ⇒ ())
//	}
//
//	override def eventsByPersistenceId(
//		                                  persistenceId: String, fromSequenceNr: Long = 0L,
//		                                  toSequenceNr: Long = Long.MaxValue): Source[EventEnvelope, Unit] = {
//		// implement in a similar way as eventsByTag
//		???
//	}
//
//	override def allPersistenceIds(): Source[String, Unit] = {
//		// implement in a similar way as eventsByTag
//		???
//	}
//
//	override def currentPersistenceIds(): Source[String, Unit] = {
//		// implement in a similar way as eventsByTag
//		???
//	}
//
//	// possibility to add more plugin specific queries
//
//	def byTagsWithMeta(tags: Set[String]): Source[RichEvent, QueryMetadata] = {
//		// implement in a similar way as eventsByTag
//		???
//	}
//
//}