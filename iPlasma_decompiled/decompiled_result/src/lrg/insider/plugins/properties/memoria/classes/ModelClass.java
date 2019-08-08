/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelClass
/*    */   extends PropertyComputer
/*    */ {
/* 13 */   public ModelClass() { super("Model Class", "Is class part of the model", "class", "boolean"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 18 */     if (anEntity instanceof lrg.memoria.core.Class) {
/*    */       
/* 20 */       DataAbstraction aClass = (DataAbstraction)anEntity;
/* 21 */       return new ResultEntity((aClass.getStatute() == 1));
/*    */     } 
/*    */     
/* 24 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\ModelClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */