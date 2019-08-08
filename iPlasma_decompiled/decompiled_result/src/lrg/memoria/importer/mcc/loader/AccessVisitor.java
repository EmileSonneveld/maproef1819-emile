package lrg.memoria.importer.mcc.loader;

public interface AccessVisitor {
  void setId(Integer paramInteger);
  
  void setBodyId(String paramString);
  
  void setVarId(String paramString);
  
  void setCounter(Integer paramInteger);
  
  void addAccess();
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\AccessVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */