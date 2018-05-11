crossScalaVersions := Seq("2.13.0-M4-pre-20d3c21", "2.12.6")

scalaVersion := crossScalaVersions.value.head

// Build https://github.com/scala/scala-collection-compat/ locally first
libraryDependencies += "org.scala-lang" %% "scala-collection-compat" % "0.1-SNAPSHOT"

// Adds a `src/main/scala-2.13+` source directory for Scala 2.13 and newer
// and a `src/main/scala-2.12-` source directory for Scala version older than 2.13
unmanagedSourceDirectories in Compile += {
  val sourceDir = (sourceDirectory in Compile).value
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, n)) if n >= 13 => sourceDir / "scala-2.13+"
    case _                       => sourceDir / "scala-2.12-"
  }
}

scalacOptions in Compile += "-deprecation"

/*
scalacOptions ++= (CrossVersion.partialVersion(scalaVersion.value) match {
  case Some((2, n)) if n >= 13 => Seq("-Xsource:2.14")
  case _                       => Seq("-Yno-adapted-args")
})
*/
