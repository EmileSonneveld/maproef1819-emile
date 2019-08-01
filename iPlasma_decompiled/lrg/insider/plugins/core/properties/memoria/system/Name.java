/*    */ package classes.lrg.insider.plugins.core.properties.memoria.system;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.System;
/*    */ 
/*    */ public class Name extends PropertyComputer {
/*  9 */   public Name() { super("Name", "The name of the entity", "system", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 13 */     if (!(anEntity instanceof System)) {
/* 14 */       return null;
/*    */     }
/* 16 */     String fullname = ((System)anEntity).getName();
/* 17 */     return new ResultEntity(fullname.substring(fullname.lastIndexOf("\\") + 1));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\system\Name.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */