package server.io

import fr.u_strasbg.tetramaster.shared.Message
import game._
import java.io._
import java.net.Socket

/**
 * Permet d'envoyer des messages où d'en recevoir du client
 * @param socket Socket permettant de communiquer avec le client
 */
class RemoteClient(val socket: Socket) extends Client
{
  val in = new ObjectInputStream(new DataInputStream(socket.getInputStream()))
  val out = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()))

  override def receiveMessage(): Msg = {
    in.readObject().asInstanceOf[Msg]
  }

  override def sendMessage(msg: Msg): Unit = {
    out.writeObject(msg)
  }
}
