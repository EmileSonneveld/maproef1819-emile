package lrg.memoria.importer.mcc.loader;

public interface TypeVisitor {
  void setId(Integer paramInteger);
  
  void setLocation(String paramString1, String paramString2, String paramString3);
  
  void setName(String paramString);
  
  void setKind(String paramString);
  
  void setPackageId(String paramString);
  
  void setNamespaceId(String paramString);
  
  void setIsAbstract(String paramString);
  
  void setIsInterface(String paramString);
  
  void setIsGeneric(String paramString);
  
  void setScopeId(String paramString);
  
  void setDecoratedType(String paramString);
  
  void addType();
  
  void typesEOF();
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\TypeVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */