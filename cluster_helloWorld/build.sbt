name := """akka-cluster_helloWorld"""

version := "1.0"

lazy val akkaVersion = "2.4.1"

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-cluster" % akkaVersion,
	"com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion
)
