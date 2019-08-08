/*    */ package lrg.common.abstractions.plugins.groups;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ 
/*    */ 
/*    */ public abstract class GroupEntityBuilder
/*    */   extends GroupBuilder
/*    */ {
/* 11 */   public GroupEntityBuilder(String name, String description, String entityType) { super(name, description, entityType); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 16 */   public GroupEntityBuilder(String name, String description, String[] entityTypes) { super(name, description, entityTypes); }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public ArrayList buildGroup(AbstractEntityInterface anEntity) { return buildGroupEntity(anEntity).getElements(); }
/*    */   
/*    */   public abstract GroupEntity buildGroupEntity(AbstractEntityInterface paramAbstractEntityInterface);
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\groups\GroupEntityBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */