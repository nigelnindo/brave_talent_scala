package com.nigelnindo.bravetalent.plate

/**
  * Created by nigelnindo on 8/17/17.
  */
case class Plate(prefix: String, suffix: String) extends PlateCounterCreator {

  override def toString = (prefix + " " + suffix).toUpperCase()

  override def createPlateCounter: Option[PlateCounterModel] = {
    if (toString == "NO PLATE"){
      None
    } else {
      Some(PlateCounterModel(
        getAlphabetValue(prefix.tail.take(1)), //don't take first character "K"
        getAlphabetValue(prefix.tail.slice(1, 2)),
        suffix.slice(0, 3).toInt,
        getAlphabetValue(suffix.takeRight(1))
      ))
    }
  }
}
