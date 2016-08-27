package com.github.uryyyyyyy.akka.http.sample.chat

import akka.actor._
import com.github.uryyyyyyy.akka.http.sample.chat.ChatRoom.ChatMessage

object ChatRoom {
  case class Join(user: String)
  case class ChatMessage(user: String, message: String)
}

class ChatRoom {
  var users: Map[String, ActorRef] = Map.empty

  def Join(u: String, a: ActorRef) = {
    users += (u -> a)
  }

  def terminated(user:ActorRef) = {
    val target = users.find(_._2 == user).get
    users -= target._1
    users.foreach(_._2 ! new ChatMessage(target._1, "see you"))
  }

  def msg(msg: ChatMessage) = {
      users.foreach(_._2 ! msg)
  }
}