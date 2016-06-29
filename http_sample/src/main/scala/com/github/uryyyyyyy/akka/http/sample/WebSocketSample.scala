package com.github.uryyyyyyy.akka.http.sample

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.http.scaladsl.server.Directives
import akka.stream._
import akka.stream.scaladsl.{Flow, Source}
import akka.stream.stage._

import scala.collection.JavaConverters._
import scalaz.Scalaz.{get => _}

object WebSocketSample extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()

  val server = new Server()

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  Http().bindAndHandle(server.route, "localhost", 8080)
}

import java.util.concurrent.ConcurrentHashMap

class Server {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()

  val piyoMap = new ConcurrentHashMap[Int, Map[String, List[String]]]().asScala

  import Directives._
  val route =
    get {
      pathSingleSlash {
        getFromFile("index.html")
      } ~
        path("app.js") {
          getFromFile("public/js/bundle.js")
        } ~
        path("room" / Remaining) { roomName =>
          if(!roomName.contains("/")) complete("")
          val ff = for {
            userName: String <- parameter('name)
          } yield handleWebSocketMessages(webSocketFlow(roomName, userName))
          ff.apply(identity)
        }
    }

  def webSocketFlow(roomName: String, userName: String): Flow[Message, Message, Any] =
    Flow[Message].mapConcat {
      case tm: TextMessage =>
        TextMessage(Source.single(s"$userName of $roomName said: ") ++ tm.textStream) :: Nil
    }

  def reportErrorsFlow[T]: Flow[T, T, Any] = Flow[T].via(new MyFlow[T])
}


class MyFlow[A] extends GraphStage[FlowShape[A, A]] {

  val in = Inlet[A]("Map.in")
  val out = Outlet[A]("Map.out")

  override val shape = FlowShape.of(in, out)

  override def createLogic(attr: Attributes): GraphStageLogic =
    new GraphStageLogic(shape) {
      setHandler(in, new InHandler {
        override def onPush(): Unit = {
          push(out, grab(in))
        }
      })
      setHandler(out, new OutHandler {
        override def onPull(): Unit = {
          pull(in)
        }
      })
    }
}