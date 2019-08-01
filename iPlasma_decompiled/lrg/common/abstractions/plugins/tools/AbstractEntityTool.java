/*    */ package lrg.common.abstractions.plugins.tools;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.AbstractPlugin;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ 
/*    */ public abstract class AbstractEntityTool
/*    */   extends AbstractPlugin
/*    */ {
/* 11 */   public AbstractEntityTool(String name, String description, String entity) { super(name, description, entity); }
/*    */ 
/*    */ 
/*    */   
/* 15 */   public AbstractEntityTool(String name, String description, String[] entities) { super(new Descriptor(name, description, entities)); }
/*    */ 
/*    */   
/*    */   public abstract void run(AbstractEntityInterface paramAbstractEntityInterface, Object paramObject);
/*    */ 
/*    */   
/*    */   public abstract String getToolName();
/*    */   
/* 23 */   public ArrayList<String> getParameterList() { return new ArrayList(); }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public ArrayList<String> getParameterExplanations() { return new ArrayList(); }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public ArrayList<String> getParameterInitialValue() { return new ArrayList(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\tools\AbstractEntityTool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */