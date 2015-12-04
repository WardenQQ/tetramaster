import java.io._
import java.net._

object Server
{
  val PORT = 1024

  def listen ()
  {
    try
    {
      val socket = new ServerSocket(this.PORT)

      // On crée un thread pour chaque nouvelle connexion
      while ( true )
      {
        new ServerThread(socket.accept()).start()
      }
    }
    catch
    {
      case e: IOException =>
        System.err.print("Failed to listen on port ")
        System.exit(1)
    }
  }
}
