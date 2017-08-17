package com.nigelnindo.bravetalent.plate

import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by nigelnindo on 8/17/17.
  */
class ExtractorSpec extends FlatSpec with Matchers {

  val NEGATIVE_RESULT = "NO PLATE"

  "The Extractor" should "Get the explicit number plate in the sentence" in {
    Extractor.extract("I saw a KCA 199A on the highway") should be ("KCS 199A")
  }

  it should "extract the number plate even whether in upper/lower/mixed case" in {
    Extractor.extract("Mercy just bought kBA 951j") should be ("KBA 951J")
  }

  it should "correctly identify invalid plate numbers" in {
    /**
      * -> should fail if a letter isn't the last character in plate suffix
      * -> should fail if any of first three characters in plate suffix is a string
      * -> should fail if any of the characters in plate suffix is a number
      */
    assert(Extractor.extract("KBA 9511 was bought by Mercy") === NEGATIVE_RESULT )
    assert(Extractor.extract("KBA 9A5J was bought by Mercy") === NEGATIVE_RESULT)
    assert(Extractor.extract("KB3 951J was bought by Mercy") === NEGATIVE_RESULT)
  }

  it should "identify true negatives" in {
    Extractor.extract("She sells sea shells at the sea shore") should be (NEGATIVE_RESULT)
  }

  it should "ignore 'Ken', which is a positive prefix, but is a name" in {
    Extractor.extract("Yesterday, Ken was driving a KBC 367A") should be ("KBC 367A")
  }


}
