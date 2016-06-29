package com.github.uryyyyyyy.akka.persistence.helloworld


//#persistent-actor-example
import akka.actor._

object PersistentActorExample extends App {

	val system = ActorSystem("example")
	val persistentActor = system.actorOf(Props[ExamplePersistentActor], "persistentActor-4-scala")

	persistentActor ! Cmd("foo")
	persistentActor ! Cmd("baz")
	persistentActor ! Cmd("bar")
	persistentActor ! "snap"
	persistentActor ! Cmd("buzz")
	persistentActor ! "print"

	Thread.sleep(1000)
	system.terminate()
}