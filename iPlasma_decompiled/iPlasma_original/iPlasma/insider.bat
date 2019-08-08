@echo off
echo Insider started ...
@echo off

::set JAVA_HOME=.\jre1502

@echo off
set LOCALCLASSPATH="%JAVA_HOME%\lib\tools.jar";"%JAVA_HOME%\lib\rt.jar";./classes;liquidlnf.jar;intellij.jar;memoria.jar;metrics.jar;common.jar;dude.jar;java2html.jar;recoder.jar;jmondrian.jar
set INSIDER_PARAMETERS=-DSAIL_PATH="./res/SAILMetrics" -DFOLDER_LIST=";" -DFOLDER_SEPARATOR="\\" -DPROJECT_TYPE="Java" -Xms256m -Xmx1536m
"%JAVA_HOME%\bin\java" %INSIDER_PARAMETERS% -classpath %LOCALCLASSPATH% -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 lrg.insider.gui.InsiderGUIMain %1;%2;%3;%4;%5;%6;%7;%8;%9


