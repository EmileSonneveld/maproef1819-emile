/*    */ package lrg.common.abstractions.plugins.operators;
/*    */ 
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ 
/*    */ 
/*    */ public abstract class FilteringOperatorWithThresholds
/*    */   extends FilteringOperator
/*    */ {
/*  9 */   public FilteringOperatorWithThresholds(String name, String entityTypeName) { super(name, entityTypeName); }
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract boolean apply(ResultEntity paramResultEntity, Object paramObject);
/*    */ 
/*    */   
/* 16 */   public boolean apply(ResultEntity theResult) { return false; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\operators\FilteringOperatorWithThresholds.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */