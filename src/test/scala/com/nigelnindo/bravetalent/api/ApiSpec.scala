package com.nigelnindo.bravetalent.api

import akka.http.scaladsl.server.MissingQueryParamRejection
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
      responseAs[String] should equal ("{\"result\":\"KAB 892F\"}")
    }
  }

  it should "fail for the extractor route if 'sentence' query parameter is missing" in {
    Get("/api/extractor") ~> routes ~> check {
      rejection should be (MissingQueryParamRejection("sentence"))
    }

  }

  it should "return 'NO PLATE' if its an empty request" in {
    Get("/api/extractor?sentence=") ~> routes ~> check {
      status should be (OK)
      responseAs[String] should equal ("{\"result\":\"NO PLATE\"}")
    }
  }

  it should "return the correct count for two valid plates" in {
    Get("/api/difference?firstPlate=KBA%20001A&secondPlate=KBA%20002A") ~> routes ~> check {
      status should be (OK)
      responseAs[String] should be ("{\"result\":1}")
    }
  }

  it should "return 0 if one of the plates is invalid" in {
    Get("/api/difference?firstPlate=KB1%20001A&secondPlate=KBA%20002A") ~> routes ~> check {
      status should be (OK)
      responseAs[String] should be ("{\"result\":0}")
    }
  }

}
