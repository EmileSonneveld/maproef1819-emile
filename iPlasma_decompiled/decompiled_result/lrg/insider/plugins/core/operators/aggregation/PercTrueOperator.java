/*    */ package classes.lrg.insider.plugins.core.operators.aggregation;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.operators.AggregationOperator;
/*    */ 
/*    */ 
/*    */ public class PercTrueOperator
/*    */   extends AggregationOperator
/*    */ {
/* 12 */   public PercTrueOperator() { super("percTrue", "boolean"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity aggregate(ArrayList resultGroup) {
/* 17 */     int size = resultGroup.size();
/* 18 */     if (size == 0) return new ResultEntity(0.0D); 
/* 19 */     double countTrue = 0.0D;
/* 20 */     Iterator it = resultGroup.iterator();
/*    */     
/* 22 */     while (it.hasNext()) {
/* 23 */       if (((Boolean)((ResultEntity)it.next()).getValue()).booleanValue()) countTrue++; 
/*    */     } 
/* 25 */     double res = countTrue / size;
/* 26 */     return new ResultEntity(((float)Math.round(res * 100.0D) / 100.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\operators\aggregation\PercTrueOperator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */