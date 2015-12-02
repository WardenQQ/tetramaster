import scala.util.Random

class Card(val fleches: Array[Boolean], val atq: Int, val defPhysique: Int, val defMagique: Int, val atqType: String) {
}

object Card {
  def random(): Card = {
    val fleches = Array(
      Random.nextBoolean(),
      Random.nextBoolean(),
      Random.nextBoolean(),
      Random.nextBoolean(),
      Random.nextBoolean(),
      Random.nextBoolean(),
      Random.nextBoolean(),
      Random.nextBoolean()
    )
    new Card(fleches, 0, 0, 0, "Magic");
  }
}