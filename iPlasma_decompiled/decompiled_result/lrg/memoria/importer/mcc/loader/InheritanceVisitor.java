package lrg.memoria.importer.mcc.loader;

public interface InheritanceVisitor {
  void setId(Integer paramInteger);
  
  void setDescendentId(String paramString);
  
  void setParentId(String paramString);
  
  void setAttribute(String paramString);
  
  void setDepth(Integer paramInteger);
  
  void addInh();
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\InheritanceVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */