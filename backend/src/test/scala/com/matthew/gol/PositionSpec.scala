package com.matthew.gol

import org.scalatest._

class Position2dSpec extends FlatSpec with Matchers {
  "The 2d Position class" should "measure distances orthogonally" in {
    val one = Position2d(0, 0)
    val two = Position2d(1, 2)

    one distanceTo two shouldEqual 2
  }

  "The 2d Position class" should "have 8 distinct neighbours" in {
    val neighbours = Position2d(0, 0).neighbours

    neighbours.length shouldEqual 8
    neighbours.distinct.length shouldEqual 8
  }
}

class PositionNdSpec extends FlatSpec with Matchers {
  "The Nd Position class" should "measure distances orthogonally" in {
    val one = PositionNd(Seq(0, 0))
    val two = PositionNd(Seq(1, 2))

    one distanceTo two shouldEqual 2
  }

  "The Nd Position class" should "have 0 distinct neighbours for 0 dimensional positions" in {
    val neighbours = PositionNd(Seq()).neighbours

    neighbours.length shouldEqual 0
    neighbours.distinct.length shouldEqual 0
  }

  "The Nd Position class" should "have 2 distinct neighbours for 1 dimensional positions" in {
    val neighbours = PositionNd(Seq(0)).neighbours

    neighbours.length shouldEqual 2
    neighbours.distinct.length shouldEqual 2
  }

  "The Nd Position class" should "have 8 distinct neighbours for 2 dimensional positions" in {
    val neighbours = PositionNd(Seq(0, 0)).neighbours

    neighbours.length shouldEqual 8
    neighbours.distinct.length shouldEqual 8
  }

  "The Nd Position class" should "have 26 distinct neighbours for 3 dimensional positions" in {
    val neighbours = PositionNd(Seq(0, 0, 0)).neighbours

    neighbours.length shouldEqual 26
    neighbours.distinct.length shouldEqual 26
  }
}

class DimensionListSpec extends FlatSpec with Matchers {
  "The Dimension List class" should "measure distances orthogonally" in {
    val one = Dimension(0, Dimension(0, End()))
    val two = Dimension(1, Dimension(2, End()))

    one distanceTo two shouldEqual 2
  }

  "The Dimension List class" should "have 0 distinct neighbours for 0 dimensional positions" in {
    val neighbours = End.neighbours

    neighbours.length shouldEqual 0
    neighbours.distinct.length shouldEqual 0
  }

  "The Dimension List class" should "have 2 distinct neighbours for 1 dimensional positions" in {
    val neighbours = Dimension(0, End()).neighbours

    neighbours.length shouldEqual 2
    neighbours.distinct.length shouldEqual 2
  }

  "The Dimension List class" should "have 8 distinct neighbours for 2 dimensional positions" in {
    val neighbours = Dimension(0, Dimension(0, End())).neighbours

    neighbours.length shouldEqual 8
    neighbours.distinct.length shouldEqual 8
  }

  "The Dimension List class" should "have 26 distinct neighbours for 3 dimensional positions" in {
    val neighbours = Dimension(0, Dimension(0, Dimension(0, End()))).neighbours

    neighbours.length shouldEqual 26
    neighbours.distinct.length shouldEqual 26
  }
}
