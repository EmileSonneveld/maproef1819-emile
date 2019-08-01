/*    */ package classes.lrg.insider.plugins.properties.memoria.packages;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class SAPDistance
/*    */   extends PropertyComputer {
/*  9 */   public SAPDistance() { super("SAP Distance", "SAP Distance", "package", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aPackage) {
/* 13 */     double IF = ((Double)aPackage.getProperty("IF").getValue()).doubleValue();
/* 14 */     double AR = ((Double)aPackage.getProperty("AR").getValue()).doubleValue();
/*    */     
/* 16 */     return new ResultEntity(AR + IF - 1.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\packages\SAPDistance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */