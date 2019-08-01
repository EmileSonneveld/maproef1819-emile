/*   */ package lrg.common.abstractions.plugins.operators;
/*   */ 
/*   */ import lrg.common.abstractions.entities.ResultEntity;
/*   */ import lrg.common.abstractions.plugins.AbstractPlugin;
/*   */ import lrg.common.abstractions.plugins.Descriptor;
/*   */ 
/*   */ public abstract class FilteringOperator
/*   */   extends AbstractPlugin {
/* 9 */   public FilteringOperator(String name, String entityTypeName) { super(new Descriptor(name, entityTypeName)); }
/*   */   
/*   */   public abstract boolean apply(ResultEntity paramResultEntity);
/*   */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\operators\FilteringOperator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */