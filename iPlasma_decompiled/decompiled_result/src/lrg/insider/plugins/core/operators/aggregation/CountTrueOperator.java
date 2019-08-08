/*    */ package classes.lrg.insider.plugins.core.operators.aggregation;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.operators.AggregationOperator;
/*    */ 
/*    */ 
/*    */ public class CountTrueOperator
/*    */   extends AggregationOperator
/*    */ {
/* 12 */   public CountTrueOperator() { super("nrTrue", "boolean"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity aggregate(ArrayList resultGroup) {
/* 17 */     int size = resultGroup.size();
/* 18 */     if (size == 0) return new ResultEntity(0.0D); 
/* 19 */     double countTrue = 0.0D;
/*    */     
/* 21 */     Iterator it = resultGroup.iterator();
/*    */     
/* 23 */     while (it.hasNext()) {
/* 24 */       if (((Boolean)((ResultEntity)it.next()).getValue()).booleanValue()) countTrue++; 
/*    */     } 
/* 26 */     return new ResultEntity(countTrue);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\operators\aggregation\CountTrueOperator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */