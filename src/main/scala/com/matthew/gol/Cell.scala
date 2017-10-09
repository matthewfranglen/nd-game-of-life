package com.matthew.gol

sealed trait Cell {
  def isLive: Boolean

  def isDead: Boolean = ! isLive
}

case object LiveCell extends Cell {
  val isLive = true
}

case object DeadCell extends Cell {
  val isLive = false
}
