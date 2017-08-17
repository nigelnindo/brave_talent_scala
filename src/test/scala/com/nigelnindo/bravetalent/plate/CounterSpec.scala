package com.nigelnindo.bravetalent.plate

import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by nigelnindo on 8/17/17.
  */
class CounterSpec extends FlatSpec with Matchers {

  "Counter" should "make sure that both plates supplies are valid Kenyan plates" in {
    Counter.getDifference("","") should be (0)
  }

  it should "ensure that we get valid results for varius number plates" in {
    /**
      * -> values that come after one another should have a difference of
      * -> values different in the tens should still calculate correctly
      * -> values different in the hundreds should still calculate correctly
      * -> changing last letter of plate by moving to the next largest figure should have a difference of one
      * -> moving to new last letter of 'prefix' should result in a difference of one
      * -> moving to first letter of 'prefix should result in a difference of one
      */
    assert(Counter.getDifference("KBA 049A","KBA 050A") === 1)
    assert(Counter.getDifference("KBE 050A","KBE 098A") === 48)
    assert(Counter.getDifference("KBD 050A","KBD 250A") === 200)
    assert(Counter.getDifference("KAA 999A","KAA 001B") === 1)
    assert(Counter.getDifference("KAA 999Z","KAB 001A") === 1)
    assert(Counter.getDifference("KAZ 999Z","KBA 001A") === 1)
  }

}
