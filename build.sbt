organization in ThisBuild := "com.lightbend.lagom"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

lazy val `lagom-testbed-java-sbt` = (project in file("."))
  .aggregate(
    `test-001-monoservice`,
    `test-002-multiservice`
  )


lazy val `test-001-monoservice` = (project in file("test-001-monoservice"))
  .enablePlugins(LagomJava).settings(common: _*)

lazy val `test-002-multiservice` = (project in file("test-002-multiservice"))
  .enablePlugins(LagomJava).settings(common: _*)


def common = Seq(
  javacOptions in compile += "-parameters"
)

