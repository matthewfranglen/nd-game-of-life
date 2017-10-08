package com.matthew.gol

sealed trait Cell {
  def isLive: Boolean
  def isDead: Boolean = ! isLive
}

object LiveCell extends Cell {
  val isLive = true
}
object DeadCell extends Cell {
  val isLive = false
}

case class Specification(born: Seq[Int], live: Seq[Int]) {
  require(! born.contains(0), "This implementation does not permit cell birth with zero live neighbours")

  def get(target: Cell): Seq[Int] =
    if (target.isLive)
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
    neighbours.filter(_.isLive).length
}

sealed trait Position[P <: Position[P]] {
  def distanceTo(other: P): Int
}

case class Position2d(x: Int, y: Int) extends Position[Position2d] {
  def distanceTo(other: Position2d): Int =
    Math.max(Math.abs(x - other.x), Math.abs(y - other.y))
}

case class PositionNd(coordinates: Seq[Int]) extends Position[PositionNd] {
  def distanceTo(other: PositionNd): Int =
    coordinates
      .zip(other.coordinates)
      .map({ case (a, b) => Math.abs(a - b) })
      .max
}

case class WorldCell[P <: Position[P]](cell: Cell, position: P)

case class WorldWindow[P <: Position[P]](window: Window, position: P)

case class World[P <: Position[P]](cells: Seq[WorldCell[P]]) {
  require(! cells.exists(_.cell.isDead), "This implementation creates sparse worlds that contain only live cells")
}
