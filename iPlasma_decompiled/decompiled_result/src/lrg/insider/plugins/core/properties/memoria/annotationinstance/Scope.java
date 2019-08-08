/*    */ package classes.lrg.insider.plugins.core.properties.memoria.annotationinstance;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.AnnotationInstance;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Scope
/*    */   extends PropertyComputer
/*    */ {
/* 17 */   public Scope() { super("scope", "The scope of the annotation", "annotation instance", "entity"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 21 */     if (!(anEntity instanceof AnnotationInstance)) {
/* 22 */       return null;
/*    */     }
/* 24 */     AnnotationInstance annotationInstance = (AnnotationInstance)anEntity;
/*    */     
/* 26 */     return new ResultEntity(annotationInstance.getAnnotatedElement());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\annotationinstance\Scope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */