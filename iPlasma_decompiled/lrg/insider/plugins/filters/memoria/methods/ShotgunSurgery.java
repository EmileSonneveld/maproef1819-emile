/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.insider.plugins.core.filters.memoria.ModelFunctionFilter;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsConstructor;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsStatic;
/*    */ import lrg.insider.plugins.filters.memoria.methods.ShotgunSurgery;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShotgunSurgery
/*    */   extends FilteringRule
/*    */ {
/* 18 */   public ShotgunSurgery() { super(new Descriptor("Shotgun Surgery", "method")); }
/*    */ 
/*    */   
/*    */   private boolean preconditionsFulfilled(AbstractEntityInterface aMethod) {
/* 22 */     if ((new ModelFunctionFilter()).applyFilter(aMethod) && 
/* 23 */       !(new IsConstructor()).applyFilter(aMethod) && 
/* 24 */       !(new IsStatic()).applyFilter(aMethod)) return true; 
/*    */     return false;
/*    */   }
/*    */   public boolean applyFilter(AbstractEntityInterface aMethod) {
/* 28 */     FilteringRule highCM = new FilteringRule("CM", ">", "method", 7.0D);
/* 29 */     FilteringRule highCC = new FilteringRule("CC", ">=", "method", 3.5D);
/*    */     
/* 31 */     if (preconditionsFulfilled(aMethod) && 
/* 32 */       highCM.applyFilter(aMethod) && 
/* 33 */       highCC.applyFilter(aMethod)) return true; 
/*    */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\ShotgunSurgery.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */