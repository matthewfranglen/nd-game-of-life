package com.matthew.gol

import org.scalatest._

class SpecificationSpec extends FlatSpec with Matchers {
  "The Specification class" should "create live cells from dead cells when there are enough living neighbours" in {
    val spec = Specification(Seq(3), Seq(2, 3))

    spec.willBeLive(DeadCell, Seq(LiveCell, LiveCell, LiveCell)) shouldEqual true
  }

  "The Specification class" should "maintain dead cells when there are not enough living neighbours" in {
    val spec = Specification(Seq(3), Seq(2, 3))

    spec.willBeLive(DeadCell, Seq(LiveCell, DeadCell, DeadCell)) shouldEqual false
  }

  "The Specification class" should "maintain live cells when there are enough living neighbours" in {
    val spec = Specification(Seq(3), Seq(2, 3))

    spec.willBeLive(LiveCell, Seq(LiveCell, LiveCell)) shouldEqual true
  }

  "The Specification class" should "kill live cells when there are not enough living neighbours" in {
    val spec = Specification(Seq(3), Seq(2, 3))

    spec.willBeLive(LiveCell, Seq(LiveCell, DeadCell)) shouldEqual false
  }
}

