ThisBuild / version := "1" // increase in case of breaking changes

ThisBuild / scalaVersion := "2.12.17"

lazy val `dar-interview-consumers` = (project in file("."))

idePackagePrefix := Some("com.paidy.dar.interview")

val sparkVersion = "3.5.1"
ThisBuild / libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion
ThisBuild / libraryDependencies += "org.apache.spark" %% "spark-hive" % sparkVersion

ThisBuild / libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
