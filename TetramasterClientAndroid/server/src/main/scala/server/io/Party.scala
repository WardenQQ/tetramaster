package server.io

import game._

/**
 * Une partie de tetramaster
 *
 * @
 * @param client1
 * @param client2 Clients participants à la partie
 */
class Party(server: Server, client1: RemoteClient, client2: RemoteClient)
{
  def launch()
  {
    println("Game launched!!!")

    val clients: Array[Client] = Array(client1, client2)

    GameLogic.gameLoop(clients)

    server.endParty(this)
  }
}
