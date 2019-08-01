package lrg.memoria.core;

public interface TypeDecorator extends Type {
  Type getRootType();
  
  Type getDecoratedType();
  
  Scope getScope();
  
  boolean isPointer();
  
  boolean isArray();
  
  boolean isTypedefAlias();
  
  boolean isReference();
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\TypeDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */