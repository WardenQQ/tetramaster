package server.io

import scala.collection.mutable

/**
 * Le matchmaker lance une partie dès que 2 joueurs sont disponibles
 *
 * @param server Le serveur auquel est lié le matchmaker
 *
 * @todo Utiliser collection immutable
 */
class Matchmaker(server: Server)
{
  val clientQueue = mutable.Queue[Client]()

  def enqueue(client: Client): Unit =
  {
    this.clientQueue.enqueue(client)

    // Dès qu'il ya plus de 2 joueurs en queue, on lance une partie
    if (this.clientQueue.length == 2)
    {
      server.startParty(clientQueue.dequeue(), clientQueue.dequeue())
    }
  }
}
