package com.matthew.server

import com.matthew.gol._

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import javax.inject.Inject

class GameOfLifeController @Inject()(
  specificationService: SpecificationService
) extends Controller {
  get("/new") { request: Request =>
    ControllerWorld.toControllerWorld(
        World(Seq(
          WorldCell(LiveCell, PositionNd(Seq(0,0)))
        ))
      )
  }

  post("/next") { request: ControllerWorld =>
    ControllerWorld.toControllerWorld(
        request.toWorld
          .next(specificationService.specification)
      )
  }
}

private case class ControllerWorld(cells: Seq[ControllerCell]) {
  def toWorld: World[PositionNd] =
    World(cells.map { _.toCell })
}

private object ControllerWorld {
  def toControllerWorld(world: World[PositionNd]): ControllerWorld =
    ControllerWorld(
      world.cells
        .filter { _.isLive }
        .map { ControllerCell.toControllerCell _ }
    )
}

private case class ControllerCell(coordinates: Seq[Int]) {
  def toCell: WorldCell[PositionNd] =
    WorldCell(LiveCell, PositionNd(coordinates))
}

private object ControllerCell {
  def toControllerCell(cell: WorldCell[PositionNd]): ControllerCell =
    ControllerCell(cell.position.coordinates)
}
