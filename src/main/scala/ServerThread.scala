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

      out.writeChars("1 2 3 TEST")

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
