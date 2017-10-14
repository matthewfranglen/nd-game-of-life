import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.maul",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "NdGameOfLife",
    libraryDependencies += "com.twitter" %% "finatra-http" % "2.13.0",
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3",
    libraryDependencies += scalaTest % Test
  )

mergeStrategy in assembly := {
  case "BUILD" => MergeStrategy.discard
  case "META-INF/io.netty.versions.properties" => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
