/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
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
/*    */ public class LAA
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public LAA() { super("LAA", "Locality of Attributes Accesses", "method", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 20 */     double aid = ((Double)anEntity.getProperty("ATFD").getValue()).doubleValue();
/* 21 */     double ald = ((Double)anEntity.getProperty("ALD").getValue()).doubleValue();
/*    */     
/* 23 */     if (aid + ald == 0.0D) return new ResultEntity(1.0D); 
/* 24 */     return new ResultEntity(ald / (aid + ald));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\LAA.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */