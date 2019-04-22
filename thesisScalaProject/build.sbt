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

// https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "3.141.59"
// https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-support
libraryDependencies += "org.seleniumhq.selenium" % "selenium-support" % "3.141.59"

// https://mvnrepository.com/artifact/org.seleniumhq.selenium.client-drivers/selenium-java-client-driver
libraryDependencies += "org.seleniumhq.selenium.client-drivers" % "selenium-java-client-driver" % "1.0.2" % Test


// https://mvnrepository.com/artifact/org.hamcrest/hamcrest-all
libraryDependencies += "org.hamcrest" % "hamcrest-all" % "1.1" % Test

// https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.8"
// https://mvnrepository.com/artifact/org.jboss.netty/netty
libraryDependencies += "org.jboss.netty" % "netty" % "3.2.10.Final"
