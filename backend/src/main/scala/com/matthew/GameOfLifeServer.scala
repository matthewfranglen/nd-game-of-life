package com.matthew

import com.matthew.server.{GameOfLifeController, GameOfLifeModule}

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{CommonFilters, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter

object GameOfLifeServerMain extends GameOfLifeServer

class GameOfLifeServer extends HttpServer {

  override val modules = Seq(
    GameOfLifeModule)

  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[GameOfLifeController]
  }
}
