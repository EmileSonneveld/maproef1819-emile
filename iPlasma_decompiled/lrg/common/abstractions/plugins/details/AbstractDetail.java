/*    */ package lrg.common.abstractions.plugins.details;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.AbstractPlugin;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ 
/*    */ public abstract class AbstractDetail
/*    */   extends AbstractPlugin {
/* 10 */   public AbstractDetail(String name, String longName, String entity) { super(name, longName, entity); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public AbstractDetail(String name, String longName, String[] entityTypes) { super(new Descriptor(name, longName, entityTypes)); }
/*    */   
/*    */   public abstract ResultEntity compute(AbstractEntityInterface paramAbstractEntityInterface);
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\details\AbstractDetail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */