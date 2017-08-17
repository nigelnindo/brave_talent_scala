package com.nigelnindo.bravetalent.api

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.nigelnindo.bravetalent.utils.Config

import scala.concurrent.ExecutionContextExecutor

/**
  * Created by nigelnindo on 8/17/17.
  */
object Boot extends App with Routes {

  override implicit val system: ActorSystem = ActorSystem()
  override implicit val materializer: ActorMaterializer = ActorMaterializer()
  override implicit val executor: ExecutionContextExecutor = system.dispatcher

  //override implicit val logger: LoggingAdapter = Logging(system, getClass)

  Http().bindAndHandle(routes, Config.server.getString("interface"), Config.server.getInt("port"))

}
