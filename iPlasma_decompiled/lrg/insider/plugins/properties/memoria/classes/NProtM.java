/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class NProtM
/*    */   extends PropertyComputer {
/*  9 */   public NProtM() { super("NProtM", "Number of Protected Methods", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 14 */     if (!(anEntity instanceof lrg.memoria.core.Class))
/* 15 */       return null; 
/* 16 */     return new ResultEntity(anEntity.getGroup("method group").applyFilter("is protected").size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NProtM.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */