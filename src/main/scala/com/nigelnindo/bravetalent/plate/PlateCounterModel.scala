package com.nigelnindo.bravetalent.plate

/**
  * Created by nigelnindo on 8/17/17.
  */
case class PlateCounterModel
  (firstPrefix: Int, secondPrefix: Int,
   firstSuffix: Int, secondSuffix: Int)

trait PlateCounterCreator {
  def createPlateCounter: Option[PlateCounterModel]

  def getAlphabetValue(letter: String): Int = letter match {
    case "A" => 1
    case "B" => 2
    case "C" => 3
    case "D" => 4
    case "E" => 5
    case "F" => 6
    case "G" => 7
    case "H" => 8
    case "I" => 9
    case "J" => 10
    case "K" => 11
    case "L" => 12
    case "M" => 13
    case "N" => 14
    case "0" => 15
    case "P" => 16
    case "Q" => 17
    case "R" => 18
    case "S" => 19
    case "T" => 20
    case "U" => 21
    case "V" => 22
    case "W" => 23
    case "X" => 24
    case "Y" => 25
    case "Z" => 26
    case _ => 1
  }

}
