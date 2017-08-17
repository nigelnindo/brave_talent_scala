package com.nigelnindo.bravetalent.api

import akka.actor.ActorSystem
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.server.Directives._
import com.nigelnindo.bravetalent.api.model.{CounterResponse, ExtractorResponse}
import com.nigelnindo.bravetalent.plate.{Counter, Extractor}

//import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

import com.nigelnindo.bravetalent.api.JsonProtocols._

import akka.stream.Materializer

import scala.concurrent.ExecutionContextExecutor

/**
  * Created by nigelnindo on 8/17/17.
  */
trait Routes {

  implicit val system: ActorSystem

  implicit def executor: ExecutionContextExecutor

  implicit val materializer: Materializer

  val routes = {
    path(""){
      get {
        complete("Hello world!")
      }
    } ~
    pathPrefix("api") {
      pathPrefix("extractor"){
        get {
          parameter('sentence.as[String]){ sentence =>
            complete(ToResponseMarshallable(ExtractorResponse(Extractor.extract(sentence).toString)))
          }
        }
      } ~
      pathPrefix("difference"){
        get {
          parameters('firstPlate.as[String], 'secondPlate.as[String]) { (first,second) =>
            complete(ToResponseMarshallable(CounterResponse(Counter.getDifference(first,second))))
          }
        }
      }
    }
  }
}
