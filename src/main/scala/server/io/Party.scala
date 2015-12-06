package server.io

/**
 * Une partie de tetramaster
 *
 * @
 * @param client1
 * @param client2 Clients participants à la partie
 */
class Party(server: Server, client1: Client, client2: Client)
{
  def launch()
  {
    // Ici logique du jeu
    // Utiliser client.sendMessage et client.receiveMessage pour communiquer avec le client

    // À la fin on termine la partie
    server.endParty(this)
  }
}
