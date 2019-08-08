/*    */ package classes.lrg.insider.plugins.properties.membrain;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CallGeneralityInBBAvg
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public CallGeneralityInBBAvg() { super("average call generality in basic block", "Average call Generality in Basic Block", "basic block", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 21 */     if (!(anEntity instanceof lrg.membrain.representation.BasicBlock))
/*    */     {
/* 23 */       return new ResultEntity(-2.0D);
/*    */     }
/*    */     
/* 26 */     GroupEntity aGroup = anEntity.getGroup("call group");
/* 27 */     Iterator it = aGroup.getElements().iterator();
/* 28 */     double result = 0.0D, size = 0.0D;
/* 29 */     while (it.hasNext()) {
/* 30 */       double tmp = ((Double)((AbstractEntity)it.next()).getProperty("call generality").getValue()).doubleValue();
/* 31 */       if (tmp >= 0.0D) {
/* 32 */         size++;
/* 33 */         result += tmp;
/*    */       } 
/*    */     } 
/* 36 */     return new ResultEntity((size == 0.0D) ? 1.0D : (result / size));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\CallGeneralityInBBAvg.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */