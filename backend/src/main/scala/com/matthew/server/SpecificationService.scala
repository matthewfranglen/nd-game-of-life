package com.matthew.server

import com.matthew.gol.Specification

import com.twitter.inject.annotations.Flag
import javax.inject.Inject


class SpecificationService @Inject()(
  @Flag("born") born: String,
  @Flag("survives") survives: String
) {
  val specification: Specification = {
    Specification(toInts(born), toInts(survives))
  }

  private def toInts(value: String): Seq[Int] =
    value split "," map { Integer.parseInt _ }
}

