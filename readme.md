Run everything under java version: `jdk-8u221-windows-x64`

Copy `Semanticdb.scala` to `~/.sbt/1.0/plugins/Semanticdb.scala`. You might need to create the `plugins` folder yourself.

```
cd maproef1819-emile/thesisCommon
sbt publishLocal
```

Open `maproef1819-emile\thesisScalaProject` with IntellIJ

Update this path: `PathsConfig.analyzedProjectPath`

In main.scala `def main` you can choose what project should be analysed. The results should be printed in the console. Otherwise, the path to the project could be passed as the first afgument of this program.
The depandency `thesisCommon` should already have a good .jar commited.

Any questions -> contact@emilesonneveld.be

Slides online: https://emilesonneveld.be/dropbox_proxy/slimmerWorden/2018-2019-Semester2/THESIS/slides_metrics-on-massive-scale-for-scala-3/index.html
