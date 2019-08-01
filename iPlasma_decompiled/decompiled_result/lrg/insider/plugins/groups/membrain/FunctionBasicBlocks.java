/*    */ package classes.lrg.insider.plugins.groups.membrain;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.membrain.representation.BasicBlock;
/*    */ import lrg.membrain.representation.ControlFlowGraph;
/*    */ import lrg.memoria.core.Function;
/*    */ import lrg.memoria.core.XFunctionBody;
/*    */ 
/*    */ 
/*    */ public class FunctionBasicBlocks
/*    */   extends GroupBuilder
/*    */ {
/* 15 */   public FunctionBasicBlocks() { super("basic block group", "It returns the basic blocks of the function", new String[] { "method", "global function" }); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 20 */     ArrayList result = new ArrayList();
/*    */     try {
/* 22 */       XFunctionBody fBody = (XFunctionBody)((Function)anEntity).getBody();
/* 23 */       ControlFlowGraph cfg = fBody.getControlFlowGraph();
/* 24 */       if (cfg == null) return result; 
/* 25 */       BasicBlock[] list = cfg.getBasicBlocks();
/* 26 */       for (int i = 0; i < list.length; i++) {
/* 27 */         result.add(list[i]);
/*    */       }
/* 29 */       return result;
/* 30 */     } catch (Exception e) {
/* 31 */       return result;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\FunctionBasicBlocks.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */