import string
import os
import io
import pathlib
import math

import glob

#files = [f for f in glob.glob("**/*.svg", recursive=True)]

# incscape is not conformant to browser SVG. Only this SVG gets converted correctly:
files = ["Standard_deviation_diagram.svg",
"def_method_match.svg"]

for f in files:
    command = "\"C:\\Program Files\\Inkscape\\inkscape.exe\"  " + f + " --export-pdf=" + f.replace(".svg", ".pdf")
    print(command)
    os.system(command)
