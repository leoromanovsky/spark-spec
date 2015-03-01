import bintray.Keys._

name := "spark-spec"

version := "1.0"

scalaVersion := "2.11.5"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

seq(bintraySettings:_*)

publishMavenStyle := true

repository in bintray := "maven"

packageLabels in bintray := Seq("spark")

bintrayOrganization in bintray := None
