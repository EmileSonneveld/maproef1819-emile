package be.emilesonneveld

import java.io.File

object PathsConfig {
  //1: sbtLaunchJarPath
  //Ensure that `sbtLaunchJarPath` points to your sbt-launch.jar
  //To find it on your system: https://www.jetbrains.com/help/idea/directories-used-by-the-ide-to-store-settings-caches-plugins-and-logs.html#config-directory
  val sbtLaunchJarPath: File = new File(getClass.getResource("sbt-launch.jar").toURI)

  //2: Path to the project for which you want metrics:
  val analyzedProjectPath: String = "C:\\Users\\emill\\openeo\\SHotDraw\\SHotDraw"
}
