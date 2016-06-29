package com.github.uryyyyyyy.akka.http.sample.chat

import akka.actor._

object ChatRoom {
  case class Join(user: String)
  case class ChatMessage(user: String, message: String)
}

class ChatRoom extends Actor {
  import ChatRoom._
  var users: Map[String, ActorRef] = Map.empty

  def receive = {
    case Join(u) =>
      users += (u -> sender())
      // we also would like to remove the user when its actor is stopped
      context.watch(sender())

    case Terminated(user) =>
      val target = users.find(_._2 == user).get
      users -= target._1

    case msg: ChatMessage =>
      users.foreach(_._2 ! msg)
  }
}