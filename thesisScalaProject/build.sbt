name := "thesisScalaProject"

version := "0.1"

scalaVersion := "2.12.8"

//libraryDependencies += "org.scalameta" %% "scalameta" % "4.1.0"

//resolvers += Resolver.bintrayRepo("zamblauskas", "maven")
//libraryDependencies += "zamblauskas" %% "scala-csv-parser" % "0.11.4"

val scalaMetaVersion = "4.1.0"

libraryDependencies += "org.scalameta" %% "scalameta" % scalaMetaVersion
libraryDependencies += "org.scalameta" %% "symtab" % scalaMetaVersion
libraryDependencies += "org.scalameta" %% "contrib" % scalaMetaVersion
libraryDependencies += "org.scalameta" %% "semanticdb" % scalaMetaVersion
libraryDependencies += "org.scalameta" % "interactive_2.12.7" % scalaMetaVersion

libraryDependencies += "ch.epfl.scala" %% "scalafix-core" % "0.9.1"

libraryDependencies += "commons-io" % "commons-io" % "2.6"