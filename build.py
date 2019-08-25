import os

os.chdir("thesisCommon")
os.system("sbt compile")
os.system("sbt run")
os.system("sbt package")
