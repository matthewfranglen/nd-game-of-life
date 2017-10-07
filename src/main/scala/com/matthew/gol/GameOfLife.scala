package com.matthew.gol

sealed trait Cell

object LiveCell extends Cell
object DeadCell extends Cell

case class Specification(born: Seq[Int], live: Seq[Int]) {
  def get(target: Cell): Seq[Int] =
    if (target eq LiveCell)
      return live
    else
      return born
}

case class Window(target: Cell, neighbours: Seq[Cell]) {
  def toCell(implicit spec: Specification): Cell =
    if (isLive(spec))
      return LiveCell
    else
      return DeadCell

  def isLive(implicit spec: Specification): Boolean =
    spec.get(target) contains livingNeighbours

  val livingNeighbours: Int =
    neighbours.filter(_ eq LiveCell).length
}
