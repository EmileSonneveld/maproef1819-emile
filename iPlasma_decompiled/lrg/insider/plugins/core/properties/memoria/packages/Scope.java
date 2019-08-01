/*    */ package classes.lrg.insider.plugins.core.properties.memoria.packages;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Package;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Scope
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public Scope() { super("scope", "The scope of the package", "package", "entity"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 20 */     if (!(anEntity instanceof Package)) return null;
/*    */     
/* 22 */     return new ResultEntity(((Package)anEntity).getSystem());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\packages\Scope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */