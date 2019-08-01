/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ public class CodeReuseIntention
/*    */   extends PropertyComputer
/*    */ {
/* 11 */   public CodeReuseIntention() { super("CodeReuseIntention", "Code Reuse Intention", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aClass) {
/* 16 */     DataAbstraction theClass = (DataAbstraction)aClass;
/* 17 */     if (((Double)theClass.getProperty("PCR").getValue()).doubleValue() < 0.0D || ((Double)theClass.getProperty("SCR").getValue()).doubleValue() < 0.0D) {
/* 18 */       return new ResultEntity(-1.0D);
/*    */     }
/* 20 */     return new ResultEntity(((Double)aClass.getProperty("PCR").getValue()).doubleValue() + ((Double)aClass.getProperty("SCR").getValue()).doubleValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\CodeReuseIntention.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */