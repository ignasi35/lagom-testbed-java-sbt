organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

lazy val `lagom-test-bed-java-sbt` = (project in file("."))
  .aggregate(
    `test-001-monoservice`
  )


lazy val `test-001-monoservice` = (project in file("test-001-monoservice"))
  .enablePlugins(LagomJava)
  .settings(common: _*)


def common = Seq(
  javacOptions in compile += "-parameters"
)

