package com.matthew.gol

import org.scalatest._

class WindowSpec extends FlatSpec with Matchers {
  "The Window class" should "count the number of living cells" in {
    Window(LiveCell, Seq(LiveCell, DeadCell)).livingNeighbours shouldEqual 1
  }

  "The Specification class" should "create live cells from dead cells when there are enough living neighbours" in {
    val spec = Specification(Seq(3), Seq(3, 4))
    val window = Window(DeadCell, Seq(LiveCell, LiveCell, LiveCell))

    spec.toCell(window) shouldEqual LiveCell
  }

  "The Specification class" should "maintain dead cells when there are not enough living neighbours" in {
    val spec = Specification(Seq(3), Seq(3, 4))
    val window = Window(DeadCell, Seq(LiveCell, DeadCell, DeadCell))

    spec.toCell(window) shouldEqual DeadCell
  }

  "The Specification class" should "maintain live cells when there are enough living neighbours" in {
    val spec = Specification(Seq(3), Seq(3, 4))
    val window = Window(LiveCell, Seq(LiveCell, LiveCell, LiveCell, LiveCell))

    spec.toCell(window) shouldEqual LiveCell
  }

  "The Specification class" should "kill live cells when there are not enough living neighbours" in {
    val spec = Specification(Seq(3), Seq(3, 4))
    val window = Window(LiveCell, Seq(LiveCell, DeadCell, DeadCell, DeadCell))

    spec.toCell(window) shouldEqual DeadCell
  }
}
