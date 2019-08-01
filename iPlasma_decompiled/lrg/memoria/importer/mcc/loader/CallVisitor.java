package lrg.memoria.importer.mcc.loader;

public interface CallVisitor {
  void setId(Integer paramInteger);
  
  void setBodyId(String paramString);
  
  void setFuncId(String paramString);
  
  void setCounter(Integer paramInteger);
  
  void addCall();
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\CallVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */