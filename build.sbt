name := """akkaSamples"""

version := "1.0"

lazy val akkaVersion = "2.4.6"

lazy val commonSettings = Seq(
  organization := "com.github.uryyyyyyy",
  scalaVersion := "2.11.7",
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" %% "scalatest" % "3.0.0-M15" % "test"
  )
)

lazy val helloWorld = (project in file("helloWorld"))
  .settings(commonSettings: _*)
  .settings(
    name := """akkaHelloWorld"""
  )


lazy val remote_helloWorld = (project in file("remote_helloWorld"))
  .settings(commonSettings: _*)
  .settings(
    name := """akka-cluster_helloWorld""",
    libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-remote" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion
)
  )

lazy val http_helloWorld = (project in file("http_helloWorld"))
  .settings(commonSettings: _*)
  .settings(
    name := """akka-http_helloWorld""",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion
    )
  )

lazy val stream_helloWorld = (project in file("stream_helloWorld"))
  .settings(commonSettings: _*)
  .settings(
    name := """akka-stream_helloWorld""",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % akkaVersion
    )
  )

lazy val persistence_helloWorld = (project in file("persistence_helloWorld"))
  .settings(commonSettings: _*)
  .settings(
    name := """akka-persistence_helloWorld""",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
      "org.iq80.leveldb"            % "leveldb"          % "0.7",
      "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8"
    )
  )

lazy val camel_helloWorld = (project in file("camel_helloWorld"))
  .settings(commonSettings: _*)
  .settings(
    name := """akka-cluster_helloWorld""",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-camel" % akkaVersion,
      "org.apache.camel" % "camel-jetty" % "2.10.3",
      "org.apache.camel" % "camel-quartz" % "2.10.3"
    )
  )