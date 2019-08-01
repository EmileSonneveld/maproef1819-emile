/*    */ package classes.lrg.insider.plugins.core.operators.aggregation;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.operators.AggregationOperator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaximumValueOperator
/*    */   extends AggregationOperator
/*    */ {
/* 13 */   public MaximumValueOperator() { super("max"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity aggregate(ArrayList resultGroup) {
/* 18 */     int size = resultGroup.size();
/* 19 */     if (size == 0) return new ResultEntity(0.0D); 
/* 20 */     double max = -999.0D;
/*    */ 
/*    */     
/* 23 */     Iterator it = resultGroup.iterator();
/*    */     
/* 25 */     while (it.hasNext()) {
/* 26 */       double currentValue = ((Double)((ResultEntity)it.next()).getValue()).doubleValue();
/* 27 */       if (currentValue > max) max = currentValue;
/*    */     
/*    */     } 
/* 30 */     return new ResultEntity(max);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\operators\aggregation\MaximumValueOperator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */