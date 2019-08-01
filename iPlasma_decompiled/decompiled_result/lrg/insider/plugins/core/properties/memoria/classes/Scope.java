/*    */ package classes.lrg.insider.plugins.core.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Class;
/*    */ 
/*    */ public class Scope extends PropertyComputer {
/*  9 */   public Scope() { super("scope", "The scope of the class", "class", "entity"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 13 */     if (!(anEntity instanceof Class)) {
/* 14 */       return null;
/*    */     }
/* 16 */     return new ResultEntity(((Class)anEntity).getPackage());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\classes\Scope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */