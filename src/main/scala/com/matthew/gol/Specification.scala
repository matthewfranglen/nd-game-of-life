package com.matthew.gol

case class Specification(born: Seq[Int], live: Seq[Int]) {
  require(! born.contains(0), "This implementation does not permit cell birth with zero live neighbours")

  def willBeLive(target: Cell, neighbours: Seq[Cell]): Boolean =
    getRules(target) contains neighbours.filter(_.isLive).length

  private def getRules(target: Cell): Seq[Int] =
    if (target.isLive) live else born
}

