package server.io

import java.io._
import java.net.Socket

/**
 * Permet d'envoyer des messages où d'en recevoir du client
 * @param socket Socket permettant de communiquer avec le client
 */
class Client(socket: Socket)
{
  def receiveMessage(): Message =
  {
    val in =  new ObjectInputStream(new DataInputStream(socket.getInputStream()))
    val message = in.readObject().asInstanceOf[Message]
    return message
  }

  def sendMessage(message: Message)
  {
    val out = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()))
    out.writeObject(message)
  }
}
