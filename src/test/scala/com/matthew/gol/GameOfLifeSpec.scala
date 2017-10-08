package com.matthew.gol

import org.scalatest._

class WindowSpec extends FlatSpec with Matchers {
  "The Window class" should "count the number of living cells" in {
    Window(LiveCell, Seq(LiveCell, DeadCell)).livingNeighbours shouldEqual 1
  }
}

class SpecificationSpec extends FlatSpec with Matchers {
  "The Specification class" should "create live cells from dead cells when there are enough living neighbours" in {
    val spec = Specification(Seq(3), Seq(2, 3))
    val window = Window(DeadCell, Seq(LiveCell, LiveCell, LiveCell))

    spec.toCell(window) shouldEqual LiveCell
  }

  "The Specification class" should "maintain dead cells when there are not enough living neighbours" in {
    val spec = Specification(Seq(3), Seq(2, 3))
    val window = Window(DeadCell, Seq(LiveCell, DeadCell, DeadCell))

    spec.toCell(window) shouldEqual DeadCell
  }

  "The Specification class" should "maintain live cells when there are enough living neighbours" in {
    val spec = Specification(Seq(3), Seq(2, 3))
    val window = Window(LiveCell, Seq(LiveCell, LiveCell))

    spec.toCell(window) shouldEqual LiveCell
  }

  "The Specification class" should "kill live cells when there are not enough living neighbours" in {
    val spec = Specification(Seq(3), Seq(2, 3))
    val window = Window(LiveCell, Seq(LiveCell, DeadCell))

    spec.toCell(window) shouldEqual DeadCell
  }
}

class Position2dSpec extends FlatSpec with Matchers {
  "The World class" should "create the next barren world" in {
    implicit val spec = Specification(Seq(3), Seq(2, 3))
    val cell = WorldCell(LiveCell, Position2d(0, 0))
    val world = World(Seq(cell))

    world.next shouldEqual World(Seq())
  }

  "The World class" should "create the next living world" in {
    implicit val spec = Specification(Seq(3), Seq(0, 2, 3))
    val cell = WorldCell(LiveCell, Position2d(0, 0))
    val world = World(Seq(cell))

    world.next shouldEqual world
  }

  "The World class" should "create the next sustaining world" in {
    implicit val spec = Specification(Seq(3), Seq(2, 3))
    val cells = Seq(
      WorldCell(LiveCell, Position2d(1, 0)),
      WorldCell(LiveCell, Position2d(0, 1)),
      WorldCell(LiveCell, Position2d(-1, 0)),
      WorldCell(LiveCell, Position2d(0, -1))
    )
    val world = World(cells)

    world.next shouldEqual world
  }

  "The World class" should "create the next thriving world" in {
    implicit val spec = Specification(Seq(3), Seq(2, 3))
    val cells = Seq(
      WorldCell(LiveCell, Position2d(1, 1)),
      WorldCell(LiveCell, Position2d(-1, 1)),
      WorldCell(LiveCell, Position2d(-1, -1))
    )
    val world = World(cells)

    val cell = WorldCell(LiveCell, Position2d(0, 0))
    val expected = World(Seq(cell))

    world.next shouldEqual expected
  }
}

class PositionNdSpec extends FlatSpec with Matchers {
  "The World class" should "create the next barren world" in {
    implicit val spec = Specification(Seq(3), Seq(2, 3))
    val cell = WorldCell(LiveCell, PositionNd(Seq(0, 0)))
    val world = World(Seq(cell))

    world.next shouldEqual World(Seq())
  }

  "The World class" should "create the next living world" in {
    implicit val spec = Specification(Seq(3), Seq(0, 2, 3))
    val cell = WorldCell(LiveCell, PositionNd(Seq(0, 0)))
    val world = World(Seq(cell))

    world.next shouldEqual world
  }

  "The World class" should "create the next sustaining world" in {
    implicit val spec = Specification(Seq(3), Seq(2, 3))
    val cells = Seq(
      WorldCell(LiveCell, PositionNd(Seq(1, 0))),
      WorldCell(LiveCell, PositionNd(Seq(0, 1))),
      WorldCell(LiveCell, PositionNd(Seq(-1, 0))),
      WorldCell(LiveCell, PositionNd(Seq(0, -1)))
    )
    val world = World(cells)

    world.next shouldEqual world
  }

  "The World class" should "create the next thriving world" in {
    implicit val spec = Specification(Seq(3), Seq(2, 3))
    val cells = Seq(
      WorldCell(LiveCell, PositionNd(Seq(1, 1))),
      WorldCell(LiveCell, PositionNd(Seq(-1, 1))),
      WorldCell(LiveCell, PositionNd(Seq(-1, -1)))
    )
    val world = World(cells)

    val cell = WorldCell(LiveCell, PositionNd(Seq(0, 0)))
    val expected = World(Seq(cell))

    world.next shouldEqual expected
  }
}
