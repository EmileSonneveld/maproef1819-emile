package be.emilesonneveld

/*
  * The path(s) below must be configured depending on your system
*/

object PathsConfig {
  //1: sbtLaunchJarPath
  //Ensure that `sbtLaunchJarPath` points to your sbt-launch.jar
  //To find it on your system: https://www.jetbrains.com/help/idea/directories-used-by-the-ide-to-store-settings-caches-plugins-and-logs.html#config-directory
  val sbtLaunchJarPath = "\"/Users/turgut/Library/Application Support/JetBrains/IntelliJIdea2022.1/plugins/Scala/launcher/sbt-launch.jar\""

  //2: SHotDraw Project path, only needed if you want metrics for the SHotDraw project
  val SHotDrawProjectPath = "/Users/turgut/Desktop/jobs/Emile/SHotDraw/SHotDraw"
}
