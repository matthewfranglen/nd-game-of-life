package com.matthew.gol

import org.scalatest._

class WindowSpec extends FlatSpec with Matchers {
  "The Window object" should "count the number of living cells" in {
    new Window(LiveCell, Seq(LiveCell, DeadCell)).livingNeighbours shouldEqual 1
  }
}
