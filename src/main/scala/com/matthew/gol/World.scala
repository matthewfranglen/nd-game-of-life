package com.matthew.gol

case class Window(target: Cell, neighbours: Seq[Cell]) {
  val livingNeighbours: Int =
    neighbours.filter(_.isLive).length

  val isLive = target.isLive
}

case class Specification(born: Seq[Int], live: Seq[Int]) {
  require(! born.contains(0), "This implementation does not permit cell birth with zero live neighbours")

  def toCell(window: Window): Cell =
    if (isLive(window)) LiveCell else DeadCell

  private def isLive(window: Window): Boolean =
    getRules(window) contains window.livingNeighbours

  private def getRules(window: Window): Seq[Int] =
    if (window.isLive) live else born
}

case class WorldCell[P <: Position[P]](cell: Cell, position: P) {
  val isLive = cell.isLive
}

case class WorldWindow[P <: Position[P]](window: Window, position: P)

case class World[P <: Position[P]](cells: Seq[WorldCell[P]]) {
  require(cells.forall(_.isLive), "This implementation handles sparse worlds that contain only live cells")

  val cellsByPosition = cells.map(worldCell => worldCell.position -> worldCell).toMap

  def next(implicit spec: Specification): World[P] = {
    val liveWindows = cells.map(toWindow _)
    val deadWindows = cells.flatMap(_.position.neighbours)
      .distinct
      .filterNot(cellsByPosition contains _)
      .map(WorldCell(DeadCell, _))
      .map(toWindow _)

    val worldCells = Seq(liveWindows, deadWindows)
      .flatten
      .map(worldWindow => WorldCell(spec.toCell(worldWindow.window), worldWindow.position))
      .filter(_.isLive)

    World(worldCells)
  }

  def toWindow(worldCell: WorldCell[P]): WorldWindow[P] = {
    val neighbours = worldCell.position.neighbours
      .map(cellsByPosition get _)
      .filter(_.isDefined)
      .map(_.get.cell)
    val window = Window(worldCell.cell, neighbours)
    WorldWindow(window, worldCell.position)
  }
}
