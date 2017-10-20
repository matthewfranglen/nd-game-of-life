package com.matthew.gol

import java.lang.Math.{abs,max}

sealed trait Position[P <: Position[P]] {
  def neighbours: Seq[P]

  def distanceTo(other: P): Int
}

sealed trait DimensionList[S <: Position[S], N <: DimensionList[N, _]] extends Position[S] {

  def add(other: S): S

  def toOffsets: Seq[S]

  def atOrigin: Boolean

}

sealed case class Dimension[N <: DimensionList[N, _]](value: Int, next: N) extends DimensionList[Dimension[N], N] {

  def neighbours: Seq[Dimension[N]] =
    for {
      offsets <- toOffsets
      if ! offsets.atOrigin
    } yield add(offsets)

  def distanceTo(other: Dimension[N]): Int =
    max(abs(value - other.value), next.distanceTo(other.next))

  def add(other: Dimension[N]): Dimension[N] =
    Dimension(value + other.value, next.add(other.next))

  def toOffsets: Seq[Dimension[N]] =
    for {
      offsets <- next.toOffsets
      offset <- Offset.offsets
    } yield Dimension(offset, offsets)

  def atOrigin: Boolean =
    value match {
      case 0 => next.atOrigin
      case _ => false
    }

}

sealed case class End() extends DimensionList[End, End] {

  def neighbours: Seq[End] = Seq()

  def distanceTo(other: End): Int = 0

  def add(other: End): End = End

  def toOffsets: Seq[End] = Seq(End)

  def atOrigin: Boolean = true

}
object End extends End






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

        applyOffsets(permutedOffsets, dimensions)
      })
      .getOrElse(Seq())
  }

  private def combine(offsetsCollection: Seq[Seq[Int]], dimension: Seq[Int]): Seq[Seq[Int]] =
    offsetsCollection flatMap { offset => dimension.map(offset :+ _) }

  private def isValid(offsets: Seq[Int]): Boolean = offsets.exists(_ != 0)

  private def applyOffsets(offsetsCollection: Seq[Seq[Int]], dimensions: Seq[Int]): Seq[Seq[Int]] =
    offsetsCollection map {
      offsets => offsets
        .zip(dimensions)
        .map { case (offset, dimension) => offset + dimension }
    }
}
