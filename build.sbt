name := """akkaSamples"""

version := "1.0"

lazy val akkaVersion = "2.4.1"

lazy val commonSettings = Seq(
	organization := "com.github.uryyyyyyy",
	scalaVersion := "2.11.7",
	libraryDependencies ++= Seq(
		"com.typesafe.akka" %% "akka-actor" % akkaVersion,
		"com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
		"org.scalatest" %% "scalatest" % "3.0.0-M15" % "test"
	)
)

lazy val helloWorld = (project in file("helloWorld")).
		settings(commonSettings: _*)


lazy val remote_helloWorld = (project in file("remote_helloWorld")).
		settings(commonSettings: _*)

lazy val http_helloWorld = (project in file("http_helloWorld")).
		settings(commonSettings: _*)

lazy val stream_helloWorld = (project in file("stream_helloWorld")).
		settings(commonSettings: _*)

lazy val cluster_helloWorld = (project in file("cluster_helloWorld")).
		settings(commonSettings: _*)

lazy val faultTolerance = (project in file("faultTolerance")).
		settings(commonSettings: _*)

lazy val persistence_helloWorld = (project in file("persistence_helloWorld")).
		settings(commonSettings: _*)