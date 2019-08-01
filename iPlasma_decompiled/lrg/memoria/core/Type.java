package lrg.memoria.core;

import lrg.common.abstractions.entities.AbstractEntityInterface;

public interface Type extends Scopable, ModelElementRoot, AbstractEntityInterface {
  String getName();
  
  String getFullName();
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Type.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */