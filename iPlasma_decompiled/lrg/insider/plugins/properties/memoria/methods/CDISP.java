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
/*    */ public class CDISP
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public CDISP() { super("CDISP", "", new String[] { "method", "global function" }, "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 20 */     double depExt = ((Double)anEntity.getProperty("CEXT").getValue()).doubleValue();
/* 21 */     double depIntens = ((Double)anEntity.getProperty("CINT").getValue()).doubleValue();
/*    */     
/* 23 */     if (depIntens == 0.0D) return new ResultEntity(0.0D); 
/* 24 */     return new ResultEntity(depExt / depIntens);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\CDISP.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */