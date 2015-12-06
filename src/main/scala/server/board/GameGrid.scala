package server.board

import scala.util.Random

class GameGrid {
  var grid = Array.fill[Cell](GameGrid.width, GameGrid.height) { ReplaceableCell }
  var curPos = (0, 0)

  val numBlocks = Random.nextInt(GameGrid.maxBlocks)
  for (i <- 0 until numBlocks)
    grid(Random.nextInt(GameGrid.width))(Random.nextInt(GameGrid.height)) = BlockCell


  def playableCells(): List[(Int, Int)] = {
    var playable = List[(Int, Int)]()

    for (i <- 0 until GameGrid.width)
      for (j <- 0 until GameGrid.height)
        playable = (i, j)::playable

    playable = playable.reverse

    playable = playable
      .filter{
        case (i, j) => grid(i)(j) match {
          case ReplaceableCell => true
          case _ => false
        }
      }

    playable
  }


  def addCard(card: Card, pos: (Int, Int), team: Int) = {
    var sucess = false
    var playable = playableCells()

    if (pos._1 >= 0 && pos._1 < GameGrid.width && pos._2 >= 0 && pos._2 < GameGrid.height) {
      grid(pos._1)(pos._2) match {
        case ReplaceableCell =>
          grid(pos._1)(pos._2) = CardCell(Card.random(), (pos._1, pos._2), team)
          curPos = pos
          sucess = true
      }
    }

    sucess
  }


  def battlesToResolve(): List[(Int, Int)] = {
    var battles: List[(Int, Int)] = null
    grid(curPos._1)(curPos._2) match {
      case curCard: CardCell =>
        battles = curCard.connectedCells
          .filter {
            case (i, j) => grid(i)(j) match {
              case card: CardCell => card.isDifferentTeam(curCard) && card.isConnectedTo((curPos._1, curPos._2))
              case _ => false
            }
          }
    }

    battles
  }


  def doBattle(attackerPos: (Int, Int), defenderPos: (Int, Int)): Boolean = {
    val attacker = grid(attackerPos._1)(attackerPos._2).asInstanceOf[CardCell]
    val defender = grid(defenderPos._1)(defenderPos._2).asInstanceOf[CardCell]

    val attackerWon = attacker.attack(defender)

    val winner = attackerWon match { case true => attacker; case false => defender }
    val loser = attackerWon match { case true => defender; case false => attacker }

    loser.team = winner.team
    loser.connectedCells
      .map{ case (i, j) => grid(i)(j) }
      .filter{ case a: CardCell => true; case _ => false }
      .foreach{ case a: CardCell => a.team = winner.team }

    attackerWon
  }


  override def toString: String = {
    var string = ""
    for (j <- 0 until GameGrid.height) {
      for (i <- 0 until GameGrid.width)
        string += grid(i)(j).toString + " "
      string += "\n"
    }
    string
  }
}

object GameGrid {
  val width = 4
  val height = 4
  val maxBlocks = 6
}