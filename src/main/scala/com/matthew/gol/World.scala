package com.matthew.gol

case class WorldCell[P <: Position[P]](cell: Cell, position: P) {
  val isLive = cell.isLive
}

case class Window(target: Cell, neighbours: Seq[Cell]) {
  def toCell(implicit spec: Specification): Cell =
    if (spec.willBeLive(target, neighbours)) LiveCell else DeadCell
}

case class WorldWindow[P <: Position[P]](window: Window, position: P) {
  def toCell(implicit spec: Specification): WorldCell[P] =
    WorldCell(window.toCell, position)
}

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
      .map(_.toCell)
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
