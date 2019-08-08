/*    */ package classes.lrg.insider.plugins.core.operators.aggregation;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.operators.AggregationOperator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SumOperator
/*    */   extends AggregationOperator
/*    */ {
/* 20 */   public SumOperator() { super("sum"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity aggregate(ArrayList resultGroup) {
/* 25 */     double sum = 0.0D;
/*    */     
/* 27 */     Iterator it = resultGroup.iterator();
/* 28 */     while (it.hasNext()) {
/* 29 */       sum += ((Double)((ResultEntity)it.next()).getValue()).doubleValue();
/*    */     }
/* 31 */     return new ResultEntity(sum);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\operators\aggregation\SumOperator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */