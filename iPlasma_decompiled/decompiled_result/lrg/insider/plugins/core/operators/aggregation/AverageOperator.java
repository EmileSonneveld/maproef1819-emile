/*    */ package classes.lrg.insider.plugins.core.operators.aggregation;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.operators.AggregationOperator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AverageOperator
/*    */   extends AggregationOperator
/*    */ {
/* 13 */   public AverageOperator() { super("avg"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity aggregate(ArrayList resultGroup) {
/* 18 */     int size = resultGroup.size();
/* 19 */     if (size == 0) return new ResultEntity(0.0D); 
/* 20 */     double sum = 0.0D;
/*    */ 
/*    */     
/* 23 */     Iterator it = resultGroup.iterator();
/*    */     
/* 25 */     while (it.hasNext()) {
/* 26 */       sum += ((Double)((ResultEntity)it.next()).getValue()).doubleValue();
/*    */     }
/* 28 */     double res = sum / size;
/*    */     
/* 30 */     return new ResultEntity(((float)Math.round(res * 100.0D) / 100.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\operators\aggregation\AverageOperator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */