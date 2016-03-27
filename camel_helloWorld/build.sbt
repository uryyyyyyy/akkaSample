name := """akka-cluster_helloWorld"""

version := "1.0"

lazy val akkaVersion = "2.4.1"

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-actor" % akkaVersion,
	"com.typesafe.akka" %% "akka-camel" % akkaVersion,
	"org.apache.camel" % "camel-jetty" % "2.10.3",
	"org.apache.camel" % "camel-quartz" % "2.10.3"
)
