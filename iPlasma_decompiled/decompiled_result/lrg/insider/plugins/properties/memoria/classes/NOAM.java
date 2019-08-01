/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NOAM
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public NOAM() { super("NOAM", "Number of Accessor Methods", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 21 */     if (!(anEntity instanceof lrg.memoria.core.Class)) {
/* 22 */       return null;
/*    */     }
/* 24 */     double noam = anEntity.contains("method group").applyFilter("is accessor").size();
/*    */     
/* 26 */     return new ResultEntity(noam);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NOAM.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */