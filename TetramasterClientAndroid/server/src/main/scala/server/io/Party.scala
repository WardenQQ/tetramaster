package server.io

import server.board.{Card, GameGrid}

import scala.io.StdIn

/**
 * Une partie de tetramaster
 *
 * @
 * @param client1
 * @param client2 Clients participants à la partie
 */
class Party(server: Server, client1: Client, client2: Client)
{
  def launch()
  {
//    val grid = new GameGrid
//    var playable = grid.playableCells()
//    var team = 0
//
//    var decks = Array.tabulate[(Int, Card)](2, 5){ (i, j) => (1, Card.random()) }

//    var deck = Array.tabulate[(Int, Card)](5){ i => (1, Card.random()) }
//
//
//    val msgSetDeckList = new MessageSetDeckList
//    msgSetDeckList.deck = deck.map {
//      case (id, card) =>
//        val newCard = new fr.u_strasbg.tetramaster.tetramasterclientandroid.Card(card.arrows, card.magicalDefense, card.physicalDefense, card.power, card.powerType)
//        newCard.setId(id)
//        newCard
//    }


//    while (!(decks(0).isEmpty && decks(1).isEmpty)) {
//      var deck = decks(team)
//
//      println(grid.toString)
//
//      println("Replaceable cells: "+ playable)
//
//      print("Place card number?: ")
//      val num = StdIn.readInt()
//      print("Place card x: ")
//      val x = StdIn.readInt()
//      print("Place card y: ")
//      val y = StdIn.readInt()
//
//      val hasCard = deck.exists{
//        case (num, _) => true
//        case _ => false
//      }
//
//      if (hasCard) {
//        grid.addCard(Card.random(), (x, y), team)
//      }
//
//
//
//      var battles = grid.battlesToResolve()
//      var isResolvingBattles = battles.size > 0
//      while (isResolvingBattles) {
//
//        println("Battles to resolve: " + battles)
//
//        print("Battle card x: ")
//        val x2 = StdIn.readInt()
//        print("Battle card y: ")
//        val y2 = StdIn.readInt()
//
//
//        val wonBattle = grid.doBattle((x, y), (x2, y2))
//
//        battles = grid.battlesToResolve()
//
//        isResolvingBattles = wonBattle && battles.size > 0
//      }
//
//      playable = grid.playableCells()
//      team = (team + 1) % 2
//    }
//
//    print(grid.toString)
    server.endParty(this)
  }
}
