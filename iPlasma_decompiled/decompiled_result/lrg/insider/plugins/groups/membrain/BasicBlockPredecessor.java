/*    */ package classes.lrg.insider.plugins.groups.membrain;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.membrain.representation.BasicBlock;
/*    */ 
/*    */ 
/*    */ public class BasicBlockPredecessor
/*    */   extends GroupBuilder
/*    */ {
/* 12 */   public BasicBlockPredecessor() { super("predecessor group", "It returns the predecessor basic blocks", "basic block"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 17 */     ArrayList result = new ArrayList();
/*    */     try {
/* 19 */       BasicBlock bb = (BasicBlock)anEntity;
/* 20 */       bb.getClass(); BasicBlock.BasicBlockPredecessorIterator it = new BasicBlock.BasicBlockPredecessorIterator(bb);
/* 21 */       while (it.hasNext()) {
/* 22 */         result.add(it.getNext());
/*    */       }
/* 24 */       return result;
/* 25 */     } catch (Exception e) {
/* 26 */       return result;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\BasicBlockPredecessor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */