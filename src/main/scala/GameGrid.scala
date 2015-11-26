import scala.util.Random
import Array._

class GameGrid {
  var grid = ofDim[Int](GameGrid.width, GameGrid.height)

  val numBlocks = Random.nextInt(GameGrid.maxBlocks)
  for (i <- 0 to numBlocks)
    grid(Random.nextInt(GameGrid.width))(Random.nextInt(GameGrid.height)) = 1

  override def toString: String = {
    grid.map(l => l.foldLeft("")((a, b) => a.concat(b.toString)))
      .foldLeft("")((a, b) => a.concat(b.toString).concat("\n"))
  }

}

object GameGrid {
  val width = 4
  val height = 4
  val maxBlocks = 6
}