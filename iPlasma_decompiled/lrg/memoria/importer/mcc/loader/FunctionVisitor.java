package lrg.memoria.importer.mcc.loader;

public interface FunctionVisitor {
  void setId(Integer paramInteger);
  
  void setName(String paramString);
  
  void setKind(String paramString);
  
  void setSignature(String paramString);
  
  void setReturnType(String paramString);
  
  void setScopeId(String paramString);
  
  void setNamespaceId(String paramString);
  
  void setAccess(String paramString);
  
  void setIsStatic(String paramString);
  
  void setIsVirtual(String paramString);
  
  void setBodyId(String paramString);
  
  void addFunction();
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\FunctionVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */