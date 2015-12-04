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

      val message = new MessageInit()
      message.setGrid(Array.tabulate[Int](4, 4) { (i, j) => i })

      out.writeObject(new MessageInit)
      in.readObject() match {
        case a: MessagePlaceCard => println("MessagePlaceCard")
        case a: MessageChooseBattle => println("MessageChooseBattle")
        case _ => println("Unknown type")
      }

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
