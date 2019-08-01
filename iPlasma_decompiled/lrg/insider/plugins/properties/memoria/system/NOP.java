/*    */ package classes.lrg.insider.plugins.properties.memoria.system;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.System;
/*    */ 
/*    */ public class NOP
/*    */   extends PropertyComputer
/*    */ {
/* 11 */   public NOP() { super("NOP", "Nr of packages", "system", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 16 */     if (anEntity instanceof System) {
/* 17 */       return new ResultEntity(((System)anEntity).getPackages().size());
/*    */     }
/* 19 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\system\NOP.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */