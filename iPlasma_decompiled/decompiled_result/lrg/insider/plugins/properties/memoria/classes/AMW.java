/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class AMW
/*    */   extends PropertyComputer {
/*  9 */   public AMW() { super("AMW", "Average Method Weight (complexity)", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface measuredClass) {
/* 13 */     double nom = ((Double)measuredClass.getProperty("NOM").getValue()).doubleValue();
/* 14 */     double wmc = ((Double)measuredClass.getProperty("WMC").getValue()).doubleValue();
/*    */     
/* 16 */     if (nom == 0.0D) return new ResultEntity(0.0D); 
/* 17 */     return new ResultEntity(wmc / nom);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\AMW.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */