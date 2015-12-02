import scala.util.Random
import Array._

class GameGrid {
  var grid = Array.fill[Cell](GameGrid.width, GameGrid.height) { ReplaceableCell }

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
}

object GameGrid {
  val width = 4
  val height = 4
  val maxBlocks = 6
}