/*    */ package lrg.common.abstractions.plugins.groups;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.AbstractPlugin;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ 
/*    */ public abstract class GroupBuilder
/*    */   extends AbstractPlugin
/*    */ {
/* 12 */   public GroupBuilder(String name, String description, String entityType) { super(name, description, entityType); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public GroupBuilder(String name, String description, String[] entityTypes) { super(new Descriptor(name, description, entityTypes)); }
/*    */ 
/*    */   
/*    */   public abstract ArrayList buildGroup(AbstractEntityInterface paramAbstractEntityInterface);
/*    */   
/*    */   public GroupEntity buildGroupEntity(AbstractEntityInterface anEntity) {
/* 23 */     String aName = String.valueOf(getDescriptorObject().getName()) + " for " + anEntity.getName();
/* 24 */     return new GroupEntity(aName, buildGroup(anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\groups\GroupBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */