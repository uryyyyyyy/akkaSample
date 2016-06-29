name := """akkaSamples"""

version := "1.0"

lazy val akkaVersion = "2.4.7"

lazy val commonSettings = Seq(
  organization := "com.github.uryyyyyyy",
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "org.scalaz" %% "scalaz-core" % "7.2.4",
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" %% "scalatest" % "3.0.0-M15" % "test"
  )
)

lazy val helloWorld = (project in file("helloWorld"))
  .settings(commonSettings: _*)
  .settings(
    name := """akkaHelloWorld"""
  )


lazy val remote_sample = (project in file("remote_sample"))
  .settings(commonSettings: _*)
  .settings(
    name := """akka-cluster_sample""",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-remote" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion
    )
  )

lazy val http_sample = (project in file("http_sample"))
  .settings(commonSettings: _*)
  .settings(
    name := """akka-http_sample""",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
      "com.typesafe.akka" %% "akka-http-testkit" % akkaVersion,
      "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaVersion,
      "com.typesafe.akka" %% "akka-http-jackson-experimental" % akkaVersion
    )
  )

lazy val stream_sample = (project in file("stream_sample"))
  .settings(commonSettings: _*)
  .settings(
    name := """akka-stream_sample""",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion
    )
  )

lazy val persistence_sample = (project in file("persistence_sample"))
  .settings(commonSettings: _*)
  .settings(
    name := """akka-persistence_sample""",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
      "org.iq80.leveldb"            % "leveldb"          % "0.7",
      "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8"
    )
  )

lazy val camel_sample = (project in file("camel_sample"))
  .settings(commonSettings: _*)
  .settings(
    name := """akka-cluster_sample""",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-camel" % akkaVersion,
      "org.apache.camel" % "camel-jetty" % "2.10.3",
      "org.apache.camel" % "camel-quartz" % "2.10.3"
    )
  )


//"com.typesafe.akka" %% "akka-typed-experimental" % "2.4.7"