package lrg.memoria.importer.recoder;

import recoder.java.ProgramElement;

interface Listener {
  void enterModelComponent(ProgramElement paramProgramElement);
  
  void leaveModelComponent(ProgramElement paramProgramElement);
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\Listener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */