import sbt._
import sbt.Build
import sbt.Keys._

object SparkSpecBuild extends Build {
  val buildSettings = Seq(
    name := "spark-spec",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.10.4",
    crossScalaVersions := Seq("2.11.4", "2.10.4"),
    organization := "com.github.leoromanovsky",
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    homepage := Some(url("https://github.com/leoromanovsky/spark-spec")),
    resolvers ++= Seq(
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
    ),
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "1.1.1",
      "com.typesafe" % "config" % "1.2.1",
      "org.scalatest" %% "scalatest" % "2.2.1"
    )
  )
  
  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = Seq(
      name := "spark-spec",
      version := "0.0.1-SNAPSHOT",
      scalaVersion := "2.10.4",
      crossScalaVersions := Seq("2.11.4", "2.10.4"),
      organization := "com.github.leoromanovsky",
      homepage := Some(url("https://github.com/leoromanovsky/spark-spec")),
      resolvers ++= Seq(
        "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
      ),
      libraryDependencies ++= Seq(
        "org.apache.spark" %% "spark-core" % "1.1.1",
        "com.typesafe" % "config" % "1.2.1",
        "org.scalatest" %% "scalatest" % "2.2.1"
      ),
      pomIncludeRepository := {
        _ => false
      },
      publishTo <<= version { (v: String) =>
        val nexus = "https://oss.sonatype.org/"
        if (v.trim.endsWith("SNAPSHOT"))
          Some("snapshots" at nexus + "content/repositories/snapshots")
        else
          Some("releases"  at nexus + "service/local/staging/deploy/maven2")
      },
      publishArtifact in Test := false,
      publishMavenStyle := true
    )
  )

  val _pomExtra =
    <url>http://github.com/leoromanovsky/spark-spec</url>
      <licenses>
        <license>
          <name>Apache License, Version 2.0</name>
          <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <scm>
        <url>git@github.com:leoromanovsky/spark-spec.git</url>
        <connection>scm:git:git@github.com:leoromanovsky/spark-spec.git</connection>
      </scm>
      <developers>
        <developer>
          <id>leoromanovsky</id>
          <name>leo Romanovsky</name>
          <url>http://leoromanovsky.github.com</url>
        </developer>
      </developers>
}
