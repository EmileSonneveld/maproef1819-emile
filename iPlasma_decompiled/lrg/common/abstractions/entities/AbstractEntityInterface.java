package lrg.common.abstractions.entities;

import lrg.common.abstractions.plugins.tools.AbstractEntityTool;

public interface AbstractEntityInterface {
  String getName();
  
  EntityType getEntityType();
  
  void setEntityType(EntityType paramEntityType);
  
  ResultEntity getProperty(String paramString);
  
  void addProperty(String paramString, ResultEntity paramResultEntity);
  
  GroupEntity getGroup(String paramString);
  
  void addGroup(String paramString, GroupEntity paramGroupEntity);
  
  AbstractEntityTool getTool(String paramString);
  
  GroupEntity uses(String paramString);
  
  GroupEntity isUsed(String paramString);
  
  GroupEntity contains(String paramString);
  
  AbstractEntity belongsTo(String paramString);
  
  void putAnnotation(String paramString, Object paramObject);
  
  Object getAnnotation(String paramString);
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\entities\AbstractEntityInterface.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */