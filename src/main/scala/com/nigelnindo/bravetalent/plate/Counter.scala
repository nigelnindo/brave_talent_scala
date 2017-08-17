package com.nigelnindo.bravetalent.plate

/**
  * Created by nigelnindo on 8/17/17.
  */
object Counter {

  private val FIRST_PREFIX_CONSTANT = 999 * 26 * 26
  private val SECOND_PREFIX_CONSTANT = 999 * 26

  def getDifference(firstPlate: String, secondPlate: String): Int = {

    val _firstPlate: Plate = Extractor.extract(firstPlate)
    val _secondPlate: Plate = Extractor.extract(secondPlate)

    if (firstPlate == "" || secondPlate == "") {
      0
    } else {

      val firstValueCounter: Option[PlateCounterModel] = _firstPlate.createPlateCounter
      val secondValueCounter: Option[PlateCounterModel] = _secondPlate.createPlateCounter

      if (firstValueCounter.isEmpty || secondValueCounter.isEmpty){
        0
      } else {

        val firstValue = compute2(firstValueCounter.get)
        val secondValue = compute2(secondValueCounter.get)

        if (firstValue > secondValue) firstValue - secondValue else secondValue - firstValue

      }
    }
  }

  def compute2(plateCounter: PlateCounterModel): Int = {
    ((plateCounter.firstPrefix - 1) * FIRST_PREFIX_CONSTANT) +
      ((plateCounter.secondPrefix - 1) * SECOND_PREFIX_CONSTANT) +
      (plateCounter.firstSuffix + (999 * (plateCounter.secondSuffix - 1)))
  }

}
