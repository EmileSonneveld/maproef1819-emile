/*    */ package classes.lrg.insider.plugins.properties.memoria.packages;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Namespace;
/*    */ 
/*    */ 
/*    */ public class ModelPackage
/*    */   extends PropertyComputer
/*    */ {
/* 12 */   public ModelPackage() { super("Model Package", "Is package part of the model", "package", "boolean"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof Namespace))
/* 18 */       return new ResultEntity(false); 
/* 19 */     Namespace aPackage = (Namespace)anEntity;
/* 20 */     return new ResultEntity((aPackage.getStatute() == 1));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\packages\ModelPackage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */