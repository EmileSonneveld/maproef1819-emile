/*    */ package lrg.common.abstractions.plugins.visualization;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.AbstractPlugin;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ 
/*    */ public abstract class AbstractVisualization
/*    */   extends AbstractPlugin
/*    */ {
/* 10 */   public AbstractVisualization(String name, String description, String entity) { super(name, description, entity); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public AbstractVisualization(String name, String description, String[] entities) { super(new Descriptor(name, description, entities)); }
/*    */   
/*    */   public abstract void view(AbstractEntityInterface paramAbstractEntityInterface);
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\visualization\AbstractVisualization.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */