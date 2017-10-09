package com.matthew.gol

sealed trait Position[P <: Position[P]] {
  def neighbours: Seq[P]

  def distanceTo(other: P): Int
}

case class Position2d(x: Int, y: Int) extends Position[Position2d] {
  def neighbours = {
    val offsets = Seq(1, 0, -1)

    offsets
      .flatMap(xOffset => offsets.map(yOffset => xOffset -> yOffset))
      .filter({ case (xOffset, yOffset) => xOffset != 0 || yOffset != 0 })
      .map({ case (xOffset, yOffset) => Position2d(x + xOffset, y + yOffset) })
  }

  def distanceTo(other: Position2d): Int =
    Math.max(Math.abs(x - other.x), Math.abs(y - other.y))
}

case class PositionNd(coordinates: Seq[Int]) extends Position[PositionNd] {
  def neighbours = {
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
