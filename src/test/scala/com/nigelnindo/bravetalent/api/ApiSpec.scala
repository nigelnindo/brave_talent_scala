package com.nigelnindo.bravetalent.api

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, FlatSpec}
import akka.http.scaladsl.model.StatusCodes._

/**
  * Created by nigelnindo on 8/17/17.
  */
class ApiSpec extends FlatSpec with Matchers with ScalatestRouteTest with Routes {

  "API" should "return 'Hello world!' for root route" in {
    Get("/") ~> routes ~> check {
      status should be (OK)
      responseAs[String] should equal ("Hello world!")
    }
  }

  it should "return the number plate in the provided sentence" in {
    Get("/api/extractor?sentence=I%20use%20to%20own%20a%20KAB%20892F") ~> routes ~> check {
      status should be (OK)
      responseAs[String] should equal ("KAB 892F")
    }
  }

  it should "fail for the extractor route if 'sentence' query parameter is missing" in {
    Get("/api/extractor") ~> routes ~> check {
      status should not be (OK)
      responseAs[String] should equal ("Request is missing required query parameter 'sentence'")
    }

  }

}
