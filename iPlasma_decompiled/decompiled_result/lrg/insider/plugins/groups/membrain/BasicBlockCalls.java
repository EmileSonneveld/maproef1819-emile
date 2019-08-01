/*    */ package classes.lrg.insider.plugins.groups.membrain;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.membrain.representation.BasicBlock;
/*    */ import lrg.membrain.representation.instructionSet.Abstractions.Instruction;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BasicBlockCalls
/*    */   extends GroupBuilder
/*    */ {
/* 14 */   public BasicBlockCalls() { super("call group", "It returns the calls of a basic blocks", "basic block"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 19 */     ArrayList result = new ArrayList();
/*    */     try {
/* 21 */       BasicBlock bb = (BasicBlock)anEntity;
/* 22 */       BasicBlock.InstructionsIterator it = bb.getForwardInstructionsIterator();
/* 23 */       while (it.hasNext()) {
/* 24 */         Instruction instr = it.getNext();
/* 25 */         if (instr instanceof lrg.membrain.representation.instructionSet.Abstractions.MCall)
/* 26 */           result.add(instr); 
/*    */       } 
/* 28 */       return result;
/* 29 */     } catch (Exception e) {
/* 30 */       return result;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\BasicBlockCalls.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */