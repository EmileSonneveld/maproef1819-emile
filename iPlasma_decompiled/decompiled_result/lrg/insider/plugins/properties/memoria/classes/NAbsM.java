/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class NAbsM
/*    */   extends PropertyComputer {
/*  9 */   public NAbsM() { super("NAbsM", "Number of Abstract Methods", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 14 */     if (!(anEntity instanceof lrg.memoria.core.Class)) {
/* 15 */       return new ResultEntity(0.0D);
/*    */     }
/* 17 */     return new ResultEntity(anEntity.contains("method group").applyFilter("is abstract").size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NAbsM.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */