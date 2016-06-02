name := """akka-persistence_helloWorld"""

version := "1.0"

lazy val akkaVersion = "2.4.2"

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-persistence" % akkaVersion,
	//I don't know how to use
	"com.typesafe.akka" %% "akka-persistence-query-experimental" % akkaVersion,
	"org.iq80.leveldb"            % "leveldb"          % "0.7",
	"org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8"
)