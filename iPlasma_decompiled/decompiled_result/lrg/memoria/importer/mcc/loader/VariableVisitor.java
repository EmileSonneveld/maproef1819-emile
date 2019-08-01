package lrg.memoria.importer.mcc.loader;

public interface VariableVisitor {
  void setId(Integer paramInteger);
  
  void setFileName(String paramString);
  
  void setDeclarationLine(Integer paramInteger);
  
  void setVariableName(String paramString);
  
  void setKindOf(String paramString);
  
  void setTypeId(String paramString);
  
  void setAccess(String paramString);
  
  void setClassId(String paramString);
  
  void setBodyId(String paramString);
  
  void setPackageId(String paramString);
  
  void setNamespaceId(String paramString);
  
  void setIsStatic(Integer paramInteger);
  
  void setIsConst(Integer paramInteger);
  
  void setRefersTo(String paramString);
  
  void addVariable();
  
  void variablesEOF();
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\VariableVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */