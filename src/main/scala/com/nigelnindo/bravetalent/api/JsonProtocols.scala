package com.nigelnindo.bravetalent.api

import com.nigelnindo.bravetalent.api.model.{CounterResponse, ExtractorResponse}
import spray.json.DefaultJsonProtocol

/**
  * Created by nigelnindo on 8/17/17.
  */
object JsonProtocols extends DefaultJsonProtocol {

  implicit val extractorResponse = jsonFormat1(ExtractorResponse)
  implicit val computeResponse = jsonFormat1(CounterResponse)

}
