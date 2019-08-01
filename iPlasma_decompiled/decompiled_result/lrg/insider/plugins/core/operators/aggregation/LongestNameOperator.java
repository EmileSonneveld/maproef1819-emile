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
/*    */ public class LongestNameOperator
/*    */   extends AggregationOperator
/*    */ {
/* 19 */   public LongestNameOperator() { super("maxlen", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity aggregate(ArrayList resultGroup) {
/* 24 */     int size = resultGroup.size();
/* 25 */     if (size == 0) return new ResultEntity(0.0D); 
/* 26 */     double maxlen = -1.0D;
/*    */     
/* 28 */     Iterator it = resultGroup.iterator();
/*    */     
/* 30 */     while (it.hasNext()) {
/* 31 */       int len = ((String)((ResultEntity)it.next()).getValue()).length();
/*    */       
/* 33 */       if (len > maxlen) maxlen = len; 
/*    */     } 
/* 35 */     return new ResultEntity(maxlen);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\operators\aggregation\LongestNameOperator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */