package server.board

import scala.util.Random

class Card(val arrows: Array[Boolean],
           val magicalDefense: Int,
           val physicalDefense: Int,
           val power: Int,
           val powerType: String) {

  def lowestDefense: Int = {
    if (physicalDefense > magicalDefense)
      magicalDefense
    else
      physicalDefense
  }
}

object Card {
  def random(): Card = {
    val arrows = Array.fill(8){ Random.nextBoolean() }
    val powerType = Random.nextInt(3) match {
      case 0 => "P"
      case 1 => "M"
      case 2 => "X"
    }
    new Card(arrows, Random.nextInt(16), Random.nextInt(16), Random.nextInt(16), powerType);
  }
}