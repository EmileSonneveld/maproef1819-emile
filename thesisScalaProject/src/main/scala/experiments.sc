import java.io.File
import java.text.DecimalFormat

val df = new DecimalFormat("000.")

df.format(0)
df.format(3)
df.format(10)
df.format(13)
df.format(134)
df.format(1348)


df.format(2.0)
df.format(2.66666666666)
df.format(2.8888)


val base = new File("C:\\Users\\emill\\dev\\scalafixtemplate\\src\\main")
val path = new File("C:\\Users\\emill\\dev\\ScalafixTemplate\\src\\main\\scala\\TestCFG.scala")

val relative = base.toPath.relativize(path.toPath)

path.toPath.getParent