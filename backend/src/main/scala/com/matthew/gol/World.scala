package com.matthew.gol

case class World[P <: Position[P]](cells: Seq[WorldCell[P]]) {
  private val cellsByPosition: Map[P, WorldCell[P]] =
    cells.map { c => c.position -> c }
      .toMap

  def next(implicit spec: Specification): World[P] = {
    val liveCells = cells.filter { _.isLive }
    val deadCells = {
      val neighbours = cells.flatMap { _.position.neighbours }
        .distinct
      val deadNeighbours = neighbours filter { isDead _ }

      deadNeighbours.map { WorldCell(DeadCell, _) }
    }
    val nextLiveCells = {
      val allCells = Seq(liveCells, deadCells).flatten
      val cellWindows = allCells map { WorldWindow.toWindow(_, cellsByPosition) }
      val nextCells = cellWindows map { _.toCell }

      nextCells filter { _.isLive }
    }

    World(nextLiveCells)
  }

  private def isDead(position: P): Boolean =
    cellsByPosition.get(position)
      .map { _.isDead }
      .getOrElse(true)
}

case class WorldCell[P <: Position[P]](cell: Cell, position: P) {
  val isLive = cell.isLive

  val isDead = cell.isDead
}

private case class Window(target: Cell, neighbours: Seq[Cell]) {
  def toCell(implicit spec: Specification): Cell =
    if (spec.willBeLive(target, neighbours)) LiveCell else DeadCell
}

private case class WorldWindow[P <: Position[P]](window: Window, position: P) {
  def toCell(implicit spec: Specification): WorldCell[P] =
    WorldCell(window.toCell, position)
}

private object WorldWindow {
  def toWindow[P <: Position[P]](cell: WorldCell[P], cells: Map[P, WorldCell[P]]): WorldWindow[P] =
    toWindow(cell, getNeighbours(cell, cells))

  private def toWindow[P <: Position[P]](cell: WorldCell[P], neighbours: Seq[Cell]): WorldWindow[P] =
    WorldWindow(Window(cell.cell, neighbours), cell.position)

  private def getNeighbours[P <: Position[P]](cell: WorldCell[P], cells: Map[P, WorldCell[P]]): Seq[Cell] =
    cell.position.neighbours
      .map { cells get _ }
      .map { _ match {
        case Some(worldCell) => worldCell.cell
        case _ => DeadCell
      } }
}
