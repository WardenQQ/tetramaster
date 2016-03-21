package game

sealed case class Pos(val x: Int, val y: Int)

sealed abstract class AtkType
case class P() extends AtkType
case class M() extends AtkType
case class X() extends AtkType

sealed case class Card(
                        val atk: Int,
                        val atkType: AtkType,
                        val pdef: Int,
                        val mdef: Int,
                        val arrows: List[Pos]
                      )

sealed abstract class Cell
case class Empty() extends Cell
case class Block() extends Cell
case class PlayedCard(val pos: Pos, val team: Int, val card: Card) extends Cell

sealed abstract class Msg
case class StartTurn() extends Msg
case class RequestBattle() extends Msg
case class EndTurn() extends Msg
case class EndGame() extends Msg
case class SetBlocks(val blocks: List[Pos]) extends Msg
case class GiveHand(val hand: Hand) extends Msg
case class EventConquest(val victor: PlayedCard, val losers: List[PlayedCard]) extends Msg
case class EventBattle(val victor: PlayedCard, val loser: PlayedCard, val attack: Int, val defense: Int) extends Msg
case class CmdPlayCard(pos: Pos, c: Card) extends Msg
case class CmdFightCard(pos: Pos) extends Msg

sealed abstract class State
case class StatePlayCard() extends State
case class StateFightCard() extends State