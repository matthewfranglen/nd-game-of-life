package com.matthew.gol

import org.scalatest._

class WindowSpec extends FlatSpec with Matchers {
  "The Window class" should "count the number of living cells" in {
    Window(LiveCell, Seq(LiveCell, DeadCell)).livingNeighbours shouldEqual 1
  }

  "The Window class" should "use the born specification to determine creation of live cells" in {
    val spec = Specification(Seq(3), Seq(3, 4))
    val toDie = Window(DeadCell, Seq(LiveCell, DeadCell, DeadCell))
    val toLive = Window(DeadCell, Seq(LiveCell, LiveCell, LiveCell))

    toDie.isLive(spec) shouldEqual false
    toDie.toCell(spec) shouldEqual DeadCell

    toLive.isLive(spec) shouldEqual true
    toLive.toCell(spec) shouldEqual LiveCell
  }

  "The Window class" should "use the live specification to determine persistence of live cells" in {
    val spec = Specification(Seq(3), Seq(3, 4))
    val toDie = Window(LiveCell, Seq(LiveCell, DeadCell, DeadCell, DeadCell))
    val toLive = Window(LiveCell, Seq(LiveCell, LiveCell, LiveCell, LiveCell))

    toDie.isLive(spec) shouldEqual false
    toDie.toCell(spec) shouldEqual DeadCell

    toLive.isLive(spec) shouldEqual true
    toLive.toCell(spec) shouldEqual LiveCell
  }
}
