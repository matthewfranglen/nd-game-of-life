package com.matthew.gol

import org.scalatest._

class WorldPosition2dSpec extends FlatSpec with Matchers {
  "The World class" should "create the next barren world" in {
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

class WorldPositionNdSpec extends FlatSpec with Matchers {
  "The World class" should "create the next barren world" in {
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

class WorldDimensionListSpec extends FlatSpec with Matchers {
  "The World class" should "create the next barren world" in {
    val cell = WorldCell(LiveCell, Dimension(0, Dimension(0, End())))
    val world = World(Seq(cell))

    world.next shouldEqual World(Seq())
  }

  "The World class" should "create the next living world" in {
    implicit val spec = Specification(Seq(3), Seq(0, 2, 3))
    val cell = WorldCell(LiveCell, Dimension(0, Dimension(0, End())))
    val world = World(Seq(cell))

    world.next shouldEqual world
  }

  "The World class" should "create the next sustaining world" in {
    val cells = Seq(
      WorldCell(LiveCell, Dimension(1, Dimension(0, End()))),
      WorldCell(LiveCell, Dimension(0, Dimension(1, End()))),
      WorldCell(LiveCell, Dimension(-1, Dimension(0, End()))),
      WorldCell(LiveCell, Dimension(0, Dimension(-1, End())))
    )
    val world = World(cells)

    world.next shouldEqual world
  }

  "The World class" should "create the next thriving world" in {
    val cells = Seq(
      WorldCell(LiveCell, Dimension(1, Dimension(1, End()))),
      WorldCell(LiveCell, Dimension(-1, Dimension(1, End()))),
      WorldCell(LiveCell, Dimension(-1, Dimension(-1, End())))
    )
    val world = World(cells)

    val cell = WorldCell(LiveCell, Dimension(0, Dimension(0, End())))
    val expected = World(Seq(cell))

    world.next shouldEqual expected
  }
}
