package lrg.memoria.core;

import lrg.common.abstractions.entities.AbstractEntityInterface;

public interface Scope extends Scopable, AbstractEntityInterface {
  String getName();
  
  String getFullName();
  
  void addScopedElement(Scopable paramScopable);
  
  ModelElementList getScopedElements();
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Scope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */