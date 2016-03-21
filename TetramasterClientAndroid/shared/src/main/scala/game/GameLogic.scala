package game

import scala.util.Random

/**
  * Created by mmichels on 3/15/2016.
  */
object GameLogic {
  def gameLoop(clients: Array[Client]): Unit = {
    var state: State = StatePlayCard()
    var turn = 0
    val (grid, hands) = gameInit()
    var curPlayed: Option[PlayedCard] = None
    var endTurn = false
    var gameFinished = true

    clients.zip(hands).foreach {
      case (cl, h) =>
        cl.sendMessage(SetBlocks(blockPositions(grid)))
        cl.sendMessage(GiveHand(h))
    }

    clients(turn).sendMessage(StartTurn())

    while (isGameFinished(hands)) {
      val client = clients(turn)
      val hand = hands(turn)
      var toSend: Msg = null

      val received: Msg = client.receiveMessage()

      received match {
        case CmdPlayCard(p, c) =>
          if (validateCmdPlayCard(state, grid, p, c, hand)) {
            doCmdPlayCard(grid, p, c, turn) match {
              case (b, cp, msg) =>
                endTurn = b
                curPlayed = cp
                toSend = msg
            }
          }

        case CmdFightCard(d) =>
          validateCmdFightCard(state, grid, curPlayed, d) match {
            case Some(defender) =>
              curPlayed match {
                case Some(attacker) =>
                  doCmdFightCard(grid, attacker, defender) match {
                    case (b, msg) =>
                      endTurn = b
                      toSend = msg
                  }
                case _ => // Do nothing
              }

            case _ => // Do nothing
          }

        case _ => // Do nothing
      }

      toSend match {
        case ev: RequestBattle => client.sendMessage(ev)
        case ev: EventBattle => clients.foreach { cl => cl.sendMessage(ev) }
        case ev: EventConquest => clients.foreach { cl => cl.sendMessage(ev) }
        case _ => // Do nothing
      }

      if (endTurn) {
        client.sendMessage(EndTurn())
        turn = (turn + 1) % 2
        clients(turn).sendMessage(StartTurn())
        endTurn = false
      }
    }

    clients.foreach { cl => cl.sendMessage(EndGame())}
  }

  def validateCmdPlayCard(s: State, g: Grid, pos: Pos, card: Card, curHand: Hand): Boolean = {
    val rightState = s match {
      case StateFightCard() => true
      case _ => false
    }

    val cellIsEmpty =
      if (isValidPosition(pos)) {
        g(pos.x)(pos.y) match {
          case Empty() => true
          case _ => false
        }
      } else {
        false
      }

    rightState && curHand.contains(card) && cellIsEmpty
  }

  def doCmdPlayCard(g: Grid, pos: Pos, card: Card, curTurn: Int): (Boolean, Option[PlayedCard], Msg) = {
    val pc = PlayedCard(pos, curTurn, card)
    g(pos.x)(pos.y) = pc

    managePotentialBattles(g, pc) match {
      case (b, m) => (b, Some(pc), m)
    }
  }

  def validateCmdFightCard(s: State, g: Grid, attacker: Option[PlayedCard], defenderPos: Pos): Option[PlayedCard] = {
    val rightState = s match {
      case StateFightCard() => true
      case _ => false
    }

    attacker match {
      case Some(atkPc) =>
        if (rightState && isValidPosition(defenderPos)) {
          g(defenderPos.x)(defenderPos.y) match {
            case defPc: PlayedCard =>
              if (verifyIsValidBattleFoe(g, atkPc, defPc))
                Some(defPc)
              else
                None

            case _ => None
          }
        } else {
          None
        }

      case _ =>
        None
    }
  }

  def doCmdFightCard(g: Grid, attacker: PlayedCard, defender: PlayedCard): (Boolean, Msg) = {
    val ev = doBattle(g, attacker, defender)

    if (ev.victor == attacker) {
      managePotentialBattles(g, attacker)
    } else {
      (false, finishConquest(g, defender))
    }
  }

  def doBattle(g: Grid, attacker: PlayedCard, defender: PlayedCard): EventBattle = {
    val a = attacker.card.atk * 16
    val d =
      attacker.card.atkType match {
        case P() => defender.card.pdef * 16
        case M() => defender.card.mdef * 16
        case X() =>
          if (defender.card.pdef < defender.card.mdef)
            defender.card.pdef * 16
          else
            defender.card.mdef * 16
      }

    val a2 = a + Random.nextInt(16)
    val d2 = d + Random.nextInt(16)

    if (a2 > d2) {
      EventBattle(attacker, defender, a2, d2)
    } else {
      EventBattle(defender, attacker, d2, a2)
    }
  }

  def finishConquest(g: Grid, victor: PlayedCard): EventConquest = {
    val l = enemyNeighbours(g, victor)

    l.foreach { loser => g(loser.pos.x)(loser.pos.y) = PlayedCard(loser.pos, victor.team, loser.card) }

    EventConquest(victor, l)
  }

  def isValidPosition(p: Pos): Boolean = {
    p.x >= 0 && p.x < 4 && p.y >= 0 && p.y < 4
  }

  def allNeighbours(g: Grid, pCard: PlayedCard): List[Pos] = {
    pCard.card.arrows
      .map { case Pos(x, y) => Pos(x + pCard.pos.x, y + pCard.pos.y) }
  }

  def enemyNeighbours(g: Grid, pCard: PlayedCard): List[PlayedCard] = {
    allNeighbours(g, pCard)
      .filter { p => isValidPosition(p) }
      .map { p => g(p.x)(p.y) }
      .collect { case x: PlayedCard => x}
      .filter { case foe => foe.team != pCard.team }
  }

  def potentialBattles(g: Grid, pCard: PlayedCard): List[PlayedCard] = {
    enemyNeighbours(g, pCard).filter { case foe => allNeighbours(g, foe).contains(pCard.pos) }
  }

  def verifyIsValidBattleFoe(g: Grid, curCard: PlayedCard, foeCard: PlayedCard): Boolean = {
    allNeighbours(g, curCard).contains(foeCard.pos) &&
    allNeighbours(g, foeCard).contains(curCard.pos) &&
    curCard.team != foeCard.team
  }

  def managePotentialBattles(g: Grid, pc: PlayedCard): (Boolean, Msg) = {
    val l = potentialBattles(g, pc)

    if (l.isEmpty) {
      (true, EventConquest(pc, enemyNeighbours(g, pc)))
    } else {
      (false, RequestBattle())
    }
  }

  def isGameFinished(hands: Array[Hand]): Boolean = {
    hands.forall(h => h.isEmpty)
  }

  def gameInit(): (Grid, Array[Hand]) = {
    val hands = Array.fill(2) { List.fill(5) { randomCard() } }
    val grid: Grid = Array.fill(4, 4) { Empty() }

    val r = new Random()

    for (i <- 0 until 6) {
      grid(r.nextInt(4))(r.nextInt(4)) = Block()
    }

    (grid, hands)
  }

  def randomCard(): Card = {
    val allDir = List(Pos(-1, -1), Pos(-1, 0), Pos(-1, 1), Pos(0, -1), Pos(0, 1), Pos(1, -1), Pos(1, 0), Pos(1, 1))
    val r = new Random()

    val atk = r.nextInt(16)
    val atkType = r.shuffle(List(P(), M(), X())).head
    val pdef = r.nextInt(16)
    val mdef = r.nextInt(16)
    val arrows = r.shuffle(allDir).take(r.nextInt(9))

    Card(atk, atkType, pdef, mdef, arrows)
  }

  def blockPositions(g:Grid) = {
    g.toList.flatMap {
      array => array.toList
    }.zipWithIndex.filter {
      case (Block(), i) => true
      case _ => false
    }.map { case (_, i) => Pos(i / 4, i % 4) }
  }
}
