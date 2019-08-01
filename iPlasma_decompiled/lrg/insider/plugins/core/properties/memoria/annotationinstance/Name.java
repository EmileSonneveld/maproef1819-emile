/*    */ package classes.lrg.insider.plugins.core.properties.memoria.annotationinstance;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Name
/*    */   extends PropertyComputer
/*    */ {
/* 18 */   public Name() { super("Name", "The name of the annotation", "annotation instance", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 23 */     if (!(anEntity instanceof lrg.memoria.core.AnnotationInstance)) return null;
/*    */     
/* 25 */     return new ResultEntity(anEntity.getName());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\annotationinstance\Name.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */