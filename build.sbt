import com.lightbend.lagom.sbt.LagomImport.*

ThisBuild / organization := "com.example"
ThisBuild / version := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
ThisBuild / scalaVersion := "2.13.8"

// Workaround for scala-java8-compat issue affecting Lagom dev-mode
// https://github.com/lagom/lagom/issues/3344
ThisBuild / libraryDependencySchemes +=
  "org.scala-lang.modules" %% "scala-java8-compat" % VersionScheme.Always

val macwire = "com.softwaremill.macwire" %% "macros" % "2.5.9" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.2.19" % Test

lazy val `project-alex` = (project in file("."))
  .aggregate(`project-alex-api`, `project-alex-impl`)

lazy val `project-alex-api` = (project in file("project-alex-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `project-alex-impl` = (project in file("project-alex-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`project-alex-api`)


