package server.io

import fr.u_strasbg.tetramaster.shared._
import server.board.{Card, GameGrid}

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
    println("Game launched!!!")

    val grid = new GameGrid
    val playable = grid.playableCells()
    var team = 0
//
    val decks = Array.tabulate[(Int, Card)](2, 5){ (i, j) => (j, Card.random()) }
//
//
    val msgSendBlockCells = new MessageSendBlockCells
    msgSendBlockCells.blockCells =
      grid.blockCells().map{ case (x, y) => Array(x, y) }.toArray
    client1.sendMessage(msgSendBlockCells)
    client2.sendMessage(msgSendBlockCells)

    val msgSetDeckList = new MessageSetDeckList
    msgSetDeckList.deck = decks(0).map {
      case (id, card) =>
        val newCard = new fr.u_strasbg.tetramaster.shared.Card(card.arrows, card.magicalDefense, card.physicalDefense, card.power, card.powerType)
        newCard.setId(id)
        newCard
    }
    msgSetDeckList.team = 0
    client1.sendMessage(msgSetDeckList)

    msgSetDeckList.deck = decks(1).map {
      case (id, card) =>
        val newCard = new fr.u_strasbg.tetramaster.shared.Card(card.arrows, card.magicalDefense, card.physicalDefense, card.power, card.powerType)
        newCard.setId(id)
        newCard
    }
    msgSetDeckList.team = 1
    client2.sendMessage(msgSetDeckList)


    while (!(decks(0).isEmpty && decks(1).isEmpty)) {
      val client = team match {
        case 0 => client1
        case 1 => client2
      }
      val deck = decks(team)

      val msgSendReplaceableCells = new MessageSendReplaceableCells
      msgSendReplaceableCells.replaceableCells =
        grid.playableCells().map{ case (x, y) => Array(x, y) }.toArray

      client.sendMessage(msgSendReplaceableCells)

      val msg2 = client.receiveMessage().asInstanceOf[MessagePlaceCard]

      val placedCardPosition = (msg2.x, msg2.y)

      val index = deck.indexWhere{ case (i, j) => i == msg2.getIdMap }
      val card = deck(index)._2
      grid.addCard(card, placedCardPosition, team)
      msg2.team = team
      msg2.card = new fr.u_strasbg.tetramaster.shared.Card(
        card.arrows,
        card.magicalDefense,
        card.physicalDefense,
        card.power,
        card.powerType)
      client1.sendMessage(msg2)
      client2.sendMessage(msg2)

      decks(team) = deck.filter{ case (i, j) => i != msg2.getIdMap }
      val msg3 = new MessageSetDeckList
      msg3.deck = decks(team).map {
        case (id, card2) =>
          val newCard = new fr.u_strasbg.tetramaster.shared.Card(card2.arrows, card2.magicalDefense, card2.physicalDefense, card2.power, card2.powerType)
          newCard.setId(id)
          newCard
      }
      msg3.team = team
      client.sendMessage(msg3)

      
//      var battles = grid.battlesToResolve()
//      var isResolvingBattles = battles.size > 0
//      while (isResolvingBattles) {
//        val msg4 = new MessageBattlesToResolve
//        msg4.battles = battles.map{ case (x, y) => Array(x, y) }.toArray
//
//        val msg5 = client.receiveMessage().asInstanceOf[MessageChooseBattle]
//
//        val wonBattle = grid.doBattle(placedCardPosition, (msg5.x, msg5.y))
//
//        battles = grid.battlesToResolve()
//        isResolvingBattles = wonBattle && battles.size > 0
//      }

      team = (team + 1) % 2
    }

    server.endParty(this)
  }
}
