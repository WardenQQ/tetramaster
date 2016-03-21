package server.io

import java.net._

/**
 * Le listener traite les connexions des clients
 *
 * @param server Le serveur auquel est lié le listener
 * @param port   Le port sur lequel on attend les connexions au serveur
 */
class Listener(server: Server, port: Int)
{
  def listen (): Unit =
  {
    val serverSocket = new ServerSocket(port)

    while ( true )
    {
      // Dès qu'un client se connecte, il est mis dans la queue pour le matchmaking
      val client = new RemoteClient(serverSocket.accept())
      server.sendClientToMatchmaking(client)
    }
  }
}
