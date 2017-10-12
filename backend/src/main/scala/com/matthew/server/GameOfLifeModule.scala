package com.matthew.server

import com.twitter.inject.TwitterModule

object GameOfLifeModule extends TwitterModule {
  val born = flag("born", "3", "Dead Cells become Live when they have this many Live neighbours")
  val survives = flag("survives", "2,3", "Live Cells survive when they have this many Live neighbours")
}
