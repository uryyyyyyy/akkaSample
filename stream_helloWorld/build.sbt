name := """akka-stream_helloWorld"""

version := "1.0"

lazy val akkaVersion = "2.4.2"

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-stream" % akkaVersion
)
