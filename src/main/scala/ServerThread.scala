import fr.u_strasbg.tetramaster.tetramasterclientandroid._

import java.io._
import java.net._

class ServerThread(socket: Socket) extends Thread("ServerThread")
{
  override def run () : Unit =
  {
    try
    {
      val out = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()))
      val in =  new ObjectInputStream(new DataInputStream(socket.getInputStream()))

      val grid = new GameGrid
      var playable = grid.playableCells()
      var team = 0

      var deck = Array.tabulate[(Int, Card)](5){ i => (1, Card.random()) }


      val msgSetDeckList = new MessageSetDeckList
      msgSetDeckList.deck = deck.map {
        case (id, card) =>
          val newCard = new fr.u_strasbg.tetramaster.tetramasterclientandroid.Card(card.arrows, card.magicalDefense, card.physicalDefense, card.power, card.powerType)
          newCard.setId(id)
          newCard
      }
      out.writeObject(msgSetDeckList)

/*
      in.readObject() match {
        case a: MessagePlaceCard => println("fr.u_strasbg.tetramaster.tetramasterclientandroid.MessagePlaceCard")
        case a: MessageChooseBattle => println("fr.u_strasbg.tetramaster.tetramasterclientandroid.MessageChooseBattle")
        case _ => println("Unknown type")
      }

      in.readObject() match {
        case a: MessagePlaceCard => println("fr.u_strasbg.tetramaster.tetramasterclientandroid.MessagePlaceCard")
        case a: MessageChooseBattle => println("fr.u_strasbg.tetramaster.tetramasterclientandroid.MessageChooseBattle")
        case _ => println("Unknown type")
      }

      in.readObject() match {
        case a: MessageVictory => println("MessageVictory")
        case _ => println("Unknown type")
      }
      */

      out.close()
      in.close()
      socket.close()
    }
    catch
    {
      case e: SocketException => ()              // En cas de Ctrl-C sur le client
      case e: IOException => e.printStackTrace() // Erreur
    }
  }
}
