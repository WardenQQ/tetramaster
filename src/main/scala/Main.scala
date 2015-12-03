import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {
    val grid = new GameGrid
    var playable = grid.playableCells()
    var team = 0

    var decks = Array.tabulate[(Int, Card)](2, 5){ (i, j) => (1, Card.random()) }

    while (!(decks(0).isEmpty && decks(1).isEmpty)) {
      var deck = decks(team)

      println(grid.toString)

      println("Replaceable cells: "+ playable)

      print("Place card number?: ")
      val num = StdIn.readInt()
      print("Place card x: ")
      val x = StdIn.readInt()
      print("Place card y: ")
      val y = StdIn.readInt()

      val hasCard = deck.exists{
        case (num, _) => true
        case _ => false
      }

      if (hasCard) {
        grid.addCard(Card.random(), (x, y), team)
      }



      var battles = grid.battlesToResolve()
      var isResolvingBattles = battles.size > 0
      while (isResolvingBattles) {

        println("Battles to resolve: " + battles)

        print("Battle card x: ")
        val x2 = StdIn.readInt()
        print("Battle card y: ")
        val y2 = StdIn.readInt()


        val wonBattle = grid.doBattle((x, y), (x2, y2))

          battles = grid.battlesToResolve()

        isResolvingBattles = wonBattle && battles.size > 0
      }

      playable = grid.playableCells()
      team = (team + 1) % 2
    }

    print(grid.toString)
  }
}
