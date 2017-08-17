package com.nigelnindo.bravetalent.plate

/**
  * Created by nigelnindo on 8/17/17.
  */
object Extractor {

  def extract(sentence: String): Plate = if ("" == sentence) Plate("NO","PLATE") else searchForPlate(splitSentence(sentence.toUpperCase))

  private def splitSentence(sentence: String): List[String] = sentence.split("\\s+").toList

  private def isPlatePrefix(word: String): Boolean = {
    if (!(word.take(1) == "K")) false else if (word.length != 3) false else ! word.matches(".*\\d+.*")
  }

  private def isPlateSuffix(word: String): Boolean = {
    if (word.length != 4) false else if (word.tail.matches("[0-9]+")) false else word.take(3).matches("[0-9]+")
  }

  private def searchForPlate(wordList: List[String]): Plate = {
    val platePrefix: PlatePrefixModel = new PlatePrefixModel("",0)
    var plateSuffix = ""

    def updatePrefixIfTrue(testString: String, index: Int): Unit = {
      if (isPlatePrefix(testString)){
        platePrefix.word = testString
        platePrefix.index = index
      }
    }

    for ((word, index) <- wordList.zipWithIndex){
      if (platePrefix.word == ""){
        updatePrefixIfTrue(word, index)
      } else {
        if (plateSuffix == ""){
          if (index - platePrefix.index == 1){
            if (isPlateSuffix(word)){
              plateSuffix = word
            }
          } else {
            updatePrefixIfTrue(word,index)
          }
        }
      }
    }

    if (plateSuffix == "") Plate("NO","PLATE") else Plate(platePrefix.word, plateSuffix)

  }

}
