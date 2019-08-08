/*    */ package lrg.common.abstractions.plugins.tools;
/*    */ 
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.AbstractPlugin;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractGroupTool
/*    */   extends AbstractPlugin
/*    */ {
/* 18 */   public AbstractGroupTool(String name, String description, String entity) { super(name, description, entity); }
/*    */ 
/*    */ 
/*    */   
/* 22 */   public AbstractGroupTool(String name, String description, String[] entities) { super(new Descriptor(name, description, entities)); }
/*    */   
/*    */   public abstract void run(GroupEntity paramGroupEntity, Object paramObject);
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\tools\AbstractGroupTool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */