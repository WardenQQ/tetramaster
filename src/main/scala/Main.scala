import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {
    val grid = new GameGrid
    var playable = grid.playableCells()
    var team = 0

    while (!playable.isEmpty) {
      print(grid.toString)

      print("x: ")
      var y = StdIn.readInt() % 4
      print("y: ")
      var x = StdIn.readInt() % 4

      grid.addCard(Card.random(), (x, y), team)

      val list = grid.battlesToResolve()

      println(list)

      playable = grid.playableCells()
      team = (team + 1) % 2
    }

    print(grid.toString)

//    val card = new Card(Array(true, true, true, true, true, true, true, true), 1, 1, 1, "Magic")
    var card = Card.random()

    print(grid.toString)
  }
}
