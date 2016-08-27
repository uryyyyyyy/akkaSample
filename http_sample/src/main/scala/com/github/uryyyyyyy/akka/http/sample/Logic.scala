package com.github.uryyyyyyy.akka.http.sample

import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.RouteResult.Complete
import akka.http.scaladsl.server._

import scala.concurrent.{ExecutionContextExecutor, Future}

object Logic {

  val validateCustomHeader: Directive1[String] = {
    val value = headerValueByName("customHeaderKey")
    value.filter(_ == "customHeaderValue", MalformedHeaderRejection("customHeaderKey", "value was wrong"))
  }

  def execute(num: Int, req: RequestContext)(implicit ec: ExecutionContextExecutor): Future[RouteResult] = {
    Future{
      println(req.request.headers.mkString(","))
      val header1 = `Set-Cookie`(HttpCookie("key", "value"))
      val header2 = `Content-Type`(ContentTypes.`text/plain(UTF-8)`)
      val response = HttpResponse(StatusCodes.OK, List(header1, header2), HttpEntity(num.toString), HttpProtocols.`HTTP/1.1`)
      Complete(response)
    }
  }
}
