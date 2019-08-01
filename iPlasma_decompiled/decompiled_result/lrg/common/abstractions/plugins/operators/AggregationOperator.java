/*    */ package lrg.common.abstractions.plugins.operators;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.AbstractPlugin;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ 
/*    */ public abstract class AggregationOperator
/*    */   extends AbstractPlugin
/*    */ {
/* 11 */   public AggregationOperator(String name) { super(new Descriptor(name, "numerical")); }
/*    */ 
/*    */ 
/*    */   
/* 15 */   public AggregationOperator(String name, String typeName) { super(new Descriptor(name, typeName)); }
/*    */   
/*    */   public abstract ResultEntity aggregate(ArrayList paramArrayList);
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\operators\AggregationOperator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */