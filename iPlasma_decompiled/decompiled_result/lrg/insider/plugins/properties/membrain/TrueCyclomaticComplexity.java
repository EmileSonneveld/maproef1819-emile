/*    */ package classes.lrg.insider.plugins.properties.membrain;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.groups.membrain.BasicBlockSuccessor;
/*    */ import lrg.insider.plugins.properties.membrain.TrueCyclomaticComplexity;
/*    */ import lrg.membrain.representation.BasicBlock;
/*    */ import lrg.membrain.representation.ControlFlowGraph;
/*    */ import lrg.memoria.core.Function;
/*    */ import lrg.memoria.core.XFunctionBody;
/*    */ 
/*    */ public class TrueCyclomaticComplexity extends PropertyComputer {
/*    */   public TrueCyclomaticComplexity() {
/* 15 */     super("CFG Cyclo", "CFG cyclomatic complexity", "method", "numerical");
/* 16 */     basedOnGroup(new BasicBlockSuccessor());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aMethod) {
/*    */     try {
/* 23 */       XFunctionBody fBody = (XFunctionBody)((Function)aMethod).getBody();
/* 24 */       ControlFlowGraph cfg = fBody.getControlFlowGraph();
/* 25 */       if (cfg == null) return new ResultEntity(0.0D); 
/* 26 */       BasicBlock[] list = cfg.getBasicBlocks();
/* 27 */       int E = 0;
/* 28 */       for (int i = 0; i < list.length; i++) {
/* 29 */         E += sizeOf("successor group", list[i]);
/*    */       }
/* 31 */       return new ResultEntity((E - list.length + 2));
/* 32 */     } catch (Exception e) {
/* 33 */       return new ResultEntity(0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\TrueCyclomaticComplexity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */