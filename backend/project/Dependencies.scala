import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"
  lazy val finatra = "com.twitter" %% "finatra-http" % "2.13.0"
  lazy val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
}
