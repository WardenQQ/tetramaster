package game

trait Client {
  def sendMessage(msg: Msg): Unit
  def receiveMessage(): Msg
}
