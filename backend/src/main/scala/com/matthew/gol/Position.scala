package com.matthew.gol

import java.lang.Math.{abs,max}

sealed trait Position[P <: Position[P]] {
  def neighbours: Seq[P]

  def distanceTo(other: P): Int
}

case class Position2d(x: Int, y: Int) extends Position[Position2d] {
  def neighbours: Seq[Position2d] =
    Offset.permute(Seq(x, y))
      .map { case Seq(x, y) => Position2d(x, y) }

  def distanceTo(other: Position2d): Int =
    max(abs(x - other.x), abs(y - other.y))
}

case class PositionNd(coordinates: Seq[Int]) extends Position[PositionNd] {
  def neighbours: Seq[PositionNd] = {
    Offset.permute(coordinates)
      .map { PositionNd(_) }
  }

  def distanceTo(other: PositionNd): Int =
    coordinates
      .zip(other.coordinates)
      .map { case (a, b) => abs(a - b) }
      .max
}

final private object Offset {
  val offsets = Seq(1, 0, -1)

  def permute(dimension: Int): Seq[Int] = offsets.map(_ + dimension)

  def permute(dimensions: Seq[Int]): Seq[Seq[Int]] = {
    val offsetsByDimension = dimensions map { _ => offsets }

    offsetsByDimension.headOption.map(first => {
        val firstOffsets = first map { Seq(_) }
        val permutedOffsets = offsetsByDimension.tail
          .foldLeft(firstOffsets)(combine(_, _))
          .filter { isValid _ }

        apply(permutedOffsets, dimensions)
      })
      .getOrElse(Seq())
  }

  private def combine(offsetsCollection: Seq[Seq[Int]], dimension: Seq[Int]): Seq[Seq[Int]] =
    offsetsCollection flatMap { offset => dimension.map(offset :+ _) }

  private def isValid(offsets: Seq[Int]): Boolean = offsets.exists(_ != 0)

  private def apply(offsetsCollection: Seq[Seq[Int]], dimensions: Seq[Int]): Seq[Seq[Int]] =
    offsetsCollection map {
      offsets => offsets
        .zip(dimensions)
        .map { case (offset, dimension) => offset + dimension }
    }
}
