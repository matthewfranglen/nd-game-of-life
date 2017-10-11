package com.matthew.gol

object GameOfLife extends App {
  val cells = Seq(
    WorldCell(LiveCell, PositionNd(Seq(1, 1))),
    WorldCell(LiveCell, PositionNd(Seq(-1, 1))),
    WorldCell(LiveCell, PositionNd(Seq(-1, -1)))
  )
  val world = World(cells)

  println("Zero: ")
  println(world)

  println("One: ")
  println(world.next)
}
