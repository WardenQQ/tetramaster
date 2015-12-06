package server.io

import scala.collection.mutable

import server.Parameters

/**
 * Le serveur traite les connexions des clients, les ajoute au matchmaking, lance et termine les parties
 *
 * @todo Utiliser collection immutable
 */
class Server()
{
  val listener   = new Listener(this, Parameters.PORT)
  val matchmaker = new Matchmaker(this)
  val partySet: mutable.Set[Party] = mutable.Set() // Parties en cours

  // Traiter les connexions des clients
  def listen(): Unit =
  {
    listener.listen()
  }

  // Ajouter les clients au matchmaking
  def sendClientToMatchmaking(client: Client): Unit =
  {
    matchmaker.enqueue(client)
  }

  // Lancer une partie avec deux clients
  def startParty(client1: Client, client2: Client): Unit =
  {
    val party = new Party(this, client1, client2)
    partySet += party // Référencement pour empêcher garbage collection
    party.launch()    // Démarrer le jeu
  }

  def endParty(party: Party): Unit =
  {
    partySet -= party // Déréférencement pour permettre garbage collection
  }
}
