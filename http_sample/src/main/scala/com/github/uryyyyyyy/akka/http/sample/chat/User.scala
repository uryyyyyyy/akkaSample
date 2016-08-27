package com.github.uryyyyyyy.akka.http.sample.chat

import akka.actor.{Actor, ActorRef}

object User {
  case class Connected(outgoing: ActorRef)
  case class IncomingMessage(text: String)
  case class OutgoingMessage(user: String, text: String)
}

class User(chatRoom: ChatRoom, user: String) extends Actor {
  import User._

  def receive = {
    case Connected(outgoing) =>
      context.become(connected(outgoing))
  }

  def connected(outgoing: ActorRef): Receive = {
    chatRoom.Join(user, self)

    {
      case IncomingMessage(text) =>
        chatRoom.msg(ChatRoom.ChatMessage(user, text))

      case ChatRoom.ChatMessage(user2, text) =>
        outgoing ! OutgoingMessage(user2, text)
    }
  }

}