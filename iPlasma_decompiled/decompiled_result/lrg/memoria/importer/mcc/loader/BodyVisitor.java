package lrg.memoria.importer.mcc.loader;

public interface BodyVisitor {
  void setId(Integer paramInteger);
  
  void setLocation(String paramString, Integer paramInteger1, Integer paramInteger2);
  
  void setPackageId(Integer paramInteger);
  
  void setNoDecisions(Integer paramInteger);
  
  void setNoAnd(Integer paramInteger);
  
  void setNoOr(Integer paramInteger);
  
  void setNoReturn(Integer paramInteger);
  
  void setNoCatch(Integer paramInteger);
  
  void setNoLoops(Integer paramInteger);
  
  void setMaxNesting(Integer paramInteger);
  
  void setNoStatements(Integer paramInteger);
  
  void setNoCodeLine(Integer paramInteger);
  
  void setCyclomaticNumber(Integer paramInteger);
  
  void addBody();
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\BodyVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */