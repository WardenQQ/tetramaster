import scala.util.Random

class GameGrid {
  var grid = Array.fill[Cell](GameGrid.width, GameGrid.height) { ReplaceableCell }
  var curPos = (0, 0)

  val numBlocks = Random.nextInt(GameGrid.maxBlocks)
  for (i <- 0 until numBlocks)
    grid(Random.nextInt(GameGrid.width))(Random.nextInt(GameGrid.height)) = BlockCell


  def playableCells(): List[(Int, Int)] = {
    var playable = List[(Int, Int)]()

    for (i <- 0 until GameGrid.width)
      for (j <- 0 until GameGrid.height)
        playable = (i, j)::playable

    playable = playable.reverse

    playable = playable
      .filter{
        case (i, j) => grid(i)(j) match {
          case ReplaceableCell => true
          case _ => false
        }
      }

    playable
  }


  def addCard(card: Card, pos: (Int, Int), team: Int) = {
    var playable = playableCells()

    if (playable.contains(pos)) {
      grid(pos._1)( pos._2) = CardCell(Card.random(), (pos._1, pos._2), team)
      curPos = pos
    }
  }


  def battlesToResolve(): List[(Int, Int)] = {
    var battles: List[(Int, Int)] = null
    grid(curPos._1)(curPos._2) match {
      case curCard: CardCell =>
        battles = curCard.connectedCells
          .filter {
            case (i, j) => grid(i)(j) match {
              case card: CardCell => card.isDifferentTeam(curCard) && card.isConnectedTo((curPos._1, curPos._2))
              case _ => false
            }
          }
    }

    battles
  }


  override def toString: String = {
    var string = ""
    for (array <- grid) {
      for (cell <-array)
        string += cell.toString + " "
      string += "\n"
    }
    string
  }
}

object GameGrid {
  val width = 4
  val height = 4
  val maxBlocks = 6
}