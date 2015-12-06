package server

import server.io.Server

/**
 * Instancie un serveur et le lance
 */
object Main
{
  def main (args: Array[String]): Unit =
  {
    val server = new Server()
    server.listen()
  }
}
