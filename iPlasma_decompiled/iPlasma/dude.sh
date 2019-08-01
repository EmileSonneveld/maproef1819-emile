LOCALCLASSPATH=./jre1502/lib/tools.jar:./jre1502/lib/rt.jar:dude.jar:liquidlnf.jar:intellij.jar:memoria.jar:common.jar:java2html.jar:recoder.jar
echo "Starting Dude..."
java -Xms256m -Xmx1024m -classpath ${LOCALCLASSPATH} lrg.dude.gui.GUIStarter
