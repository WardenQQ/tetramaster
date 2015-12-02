class CardCell(val team: Int, val connectedCells: List[(Int, Int)]) extends Cell {

  def isDifferentTeam(other: CardCell): Boolean = {
    team != other.team
  }


  def isConnectedTo(pos: (Int, Int)): Boolean = {
    connectedCells.contains(pos)
  }


  override def toString: String = {
    team match {
      case 0 => " Red "
      case 1 => "Blue "
    }
  }
}

object CardCell {
  def apply(card: Card, pos: (Int, Int), team: Int): CardCell = {
    val connectedCells =
      card.fleches
        .toList
        .zipWithIndex
        .filter{ case (b, i) => b }
        .map{
          case (true, 0) => (-1, -1) // North-West
          case (true, 1) => (0, -1) // North
          case (true, 2) => (1, -1) // North-East
          case (true, 3) => (1, 0) // East
          case (true, 4) => (1, 1) // South-East
          case (true, 5) => (0, 1) // South
          case (true, 6) => (-1, 1) // South-West
          case (true, 7) => (-1, 0) // West
        }
        .map{
          case (i, j) => (pos._1 + i, pos._2 + j)
        }
        .filter{
          case (-1, _) => false
          case (GameGrid.width, _) => false
          case (_, -1) => false
          case (_, GameGrid.height) => false
          case _ => true
        }

    return new CardCell(team, connectedCells)
  }
}