package com.nigelnindo.bravetalent.plate

/**
  * Created by nigelnindo on 8/17/17.
  */
case class Plate(prefix: String, suffix: String) {

  override def toString = (prefix + " " + suffix).toUpperCase()


}
