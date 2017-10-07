package com.matthew.gol

sealed trait Cell

object LiveCell extends Cell
object DeadCell extends Cell

case class Specification(born: Seq[Int], live: Seq[Int])

class Window(target: Cell, neighbours: Seq[Cell]) {
  def toCell(implicit spec: Specification): Cell =
    if (isLive(spec))
      return LiveCell
    else
      return DeadCell

  def isLive(implicit spec: Specification): Boolean =
    if (target eq LiveCell)
      spec.live contains livingNeighbours
    else
      spec.born contains livingNeighbours

  val livingNeighbours: Int =
    neighbours.filter(_ eq LiveCell).length
}
