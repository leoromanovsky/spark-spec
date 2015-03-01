import bintray.Plugin._
import sbt._
import sbt.Build
import sbt.Keys._
import bintray.Keys._

object BuildSettings {
  val buildSettings = Seq(
    name := "spark-spec",
    organization := "com.leoromanovsky",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.10.4",
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    publishMavenStyle := true,
    repository in bintray := "maven",
    packageLabels in bintray := Seq("spark"),
    bintrayOrganization in bintray := None,
    resolvers ++= Seq(
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
    ),
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "1.1.1",
      "com.typesafe" % "config" % "1.2.1",
      "org.scalatest" %% "scalatest" % "2.2.1" % "test"
    )
  )
}

object SparkSpecBuild extends Build {
  lazy val core = Project(
    "core", 
    file("core"),
    settings = BuildSettings.buildSettings ++ bintraySettings)
  
  lazy val examples = Project(
    "examples", 
    file("examples"),
    settings = BuildSettings.buildSettings ++ bintraySettings ++ Seq(
      libraryDependencies ++= Seq(
        "mysql" % "mysql-connector-java" % "5.1.34",
        "com.typesafe.slick" %% "slick" % "2.1.0"
      ),
      publish := (),
      publishLocal := (),
      packagedArtifacts := Map.empty
    )
  )
}
