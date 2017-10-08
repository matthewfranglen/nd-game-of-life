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

  def toCell(window: Window): Cell =
    if (isLive(window))
      return LiveCell
    else
      return DeadCell

  private def isLive(window: Window): Boolean =
    getRules(window) contains window.livingNeighbours

  private def getRules(window: Window): Seq[Int] =
    if (window.isLive)
      return live
    else
      return born
}

case class Window(target: Cell, neighbours: Seq[Cell]) {
  val livingNeighbours: Int =
    neighbours.filter(_.isLive).length

  val isLive = target.isLive
}

sealed trait Position[P <: Position[P]] {
  def neighbours: Seq[Position[P]]
  def distanceTo(other: P): Int
}

case class Position2d(x: Int, y: Int) extends Position[Position2d] {
  val neighbours = Seq(1,0,-1).zip(Seq(1,0,-1))
    .filter({ case (xOffset, yOffset) => xOffset != 0 && yOffset != 0 })
    .map({ case (xOffset, yOffset) => Position2d(x + xOffset, y + yOffset) })

  def distanceTo(other: Position2d): Int =
    Math.max(Math.abs(x - other.x), Math.abs(y - other.y))
}

case class PositionNd(coordinates: Seq[Int]) extends Position[PositionNd] {
  val neighbours = {
    val offsets = coordinates.map(_ => Seq(-1, 0, 1))

    // This creates the list of permutations of offsets,
    // filters the single one which is no offset (all dimension offsets are zero),
    // and then applies them to the coordinates to produce the neighbouring coordinates.
    // Once that has been done they can be converted into positions.
    offsets.tail
      .foldLeft(offsets.head.map(Seq(_)))(
        (existingOffsets, dimensionOffsets) => existingOffsets.flatMap(offset => dimensionOffsets.map(offset :+ _))
      )
      .filter(_.exists(_ != 0))
      .map(coordinates.zip(_).map({ case (coordinate, offset) => coordinate + offset }))
      .map(PositionNd(_))
  }

  def distanceTo(other: PositionNd): Int =
    coordinates
      .zip(other.coordinates)
      .map({ case (a, b) => Math.abs(a - b) })
      .max
}

case class WorldCell[P <: Position[P]](cell: Cell, position: P)

case class WorldWindow[P <: Position[P]](window: Window, position: P)

case class World[P <: Position[P]](cells: Seq[WorldCell[P]]) {
  require(! cells.exists(_.cell.isDead), "This implementation handles sparse worlds that contain only live cells")
}
