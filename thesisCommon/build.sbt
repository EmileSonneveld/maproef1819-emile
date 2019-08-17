name := "thesisCommon"

version := "0.1"

scalaVersion := "2.12.8"


//libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.1"
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.1",
  "org.slf4j" % "slf4j-nop" % "1.7.26",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.1"
)
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.3.1"


//libraryDependencies += "com.h2database" % "h2" % "1.4.199"
unmanagedJars in Compile += file("C:\\Users\\emill\\dev\\maproef1819-emile\\jars\\sqlite-jdbc-3.27.2.1.jar")
