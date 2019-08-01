LOCALCLASSPATH=./jre1502/lib/tools.jar:./jre1502/lib/rt.jar:./classes:liquidlnf.jar:intellij.jar:memoria.jar:metrics.jar:common.jar:dude.jar:java2html.jar:recoder.jar:jmondrian.jar
echo "Starting Insider..."
java -Xms256m -Xmx1536m -classpath ${LOCALCLASSPATH} lrg.insider.gui.InsiderGUIMain $1 $2 $3