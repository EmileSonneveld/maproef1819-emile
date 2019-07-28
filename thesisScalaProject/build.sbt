name := "thesisScalaProject"

version := "0.1"

scalaVersion := "2.12.8"

//libraryDependencies += "org.scalameta" %% "scalameta" % "4.1.0"

//resolvers += Resolver.bintrayRepo("zamblauskas", "maven")
//libraryDependencies += "zamblauskas" %% "scala-csv-parser" % "0.11.4"

val scalaMetaVersion = "4.1.0"

libraryDependencies += "org.scalameta" %% "scalameta" % scalaMetaVersion withSources()
libraryDependencies += "org.scalameta" %% "symtab" % scalaMetaVersion withSources()
libraryDependencies += "org.scalameta" %% "contrib" % scalaMetaVersion withSources()
libraryDependencies += "org.scalameta" %% "semanticdb" % scalaMetaVersion withSources()
libraryDependencies += "org.scalameta" % "interactive_2.12.7" % scalaMetaVersion withSources()

libraryDependencies += "ch.epfl.scala" %% "scalafix-core" % "0.9.1"

libraryDependencies += "commons-io" % "commons-io" % "2.6"

// Too much work to copy the whole selenium source code, so we only import theinteresting private parts,
// and take the rest of the public parts as dependancy.
// Could perhaps also acces the private parts with reflections
// https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "3.141.59"



libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

// https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.1"

//libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.1"
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.1",
  "org.slf4j" % "slf4j-nop" % "1.7.26",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.1"
)
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.3.1"

//libraryDependencies += "com.h2database" % "h2" % "1.4.199"
unmanagedJars in Compile += file(Path.userHome+"C:\\Users\\emill\\dev\\maproef1819-emile\\jars\\sqlite-jdbc-3.27.2.1.jar")
