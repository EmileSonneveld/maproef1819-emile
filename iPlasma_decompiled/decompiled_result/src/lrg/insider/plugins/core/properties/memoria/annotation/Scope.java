/*    */ package classes.lrg.insider.plugins.core.properties.memoria.annotation;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Annotation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Scope
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public Scope() { super("scope", "The scope of the annotation", "annotation", "entity"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 20 */     if (!(anEntity instanceof Annotation)) {
/* 21 */       return null;
/*    */     }
/* 23 */     return new ResultEntity(((Annotation)anEntity).getPackage());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\annotation\Scope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */