package server.io

import fr.u_strasbg.tetramaster.shared.Message
import java.io._
import java.net.Socket

/**
 * Permet d'envoyer des messages où d'en recevoir du client
 * @param socket Socket permettant de communiquer avec le client
 */
class Client(socket: Socket)
{
  val in = new ObjectInputStream(new DataInputStream(socket.getInputStream()))
  val out = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()))

  def receiveMessage(): Message =
  {
    val message = in.readObject().asInstanceOf[Message]
    return message
  }

  def sendMessage(message: Message)
  {
    out.writeObject(message)
  }
}
