/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ public class InterfaceReuseIntention
/*    */   extends PropertyComputer
/*    */ {
/* 11 */   public InterfaceReuseIntention() { super("InterfaceReuseIntention", "Interface Reuse Intention", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aClass) {
/* 16 */     DataAbstraction theClass = (DataAbstraction)aClass;
/* 17 */     if (((Double)theClass.getProperty("#AVG_TotalUniform#").getValue()).doubleValue() < 0.0D || ((Double)theClass.getProperty("#AVG_PartialUniform#").getValue()).doubleValue() < 0.0D) {
/* 18 */       return new ResultEntity(-1.0D);
/*    */     }
/* 20 */     return new ResultEntity(((Double)aClass.getProperty("#AVG_TotalUniform#").getValue()).doubleValue() + 0.5D * ((Double)aClass.getProperty("#AVG_PartialUniform#").getValue()).doubleValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\InterfaceReuseIntention.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */