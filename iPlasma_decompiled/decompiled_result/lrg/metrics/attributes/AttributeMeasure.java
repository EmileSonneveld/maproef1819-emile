/*    */ package lrg.metrics.attributes;
/*    */ 
/*    */ import lrg.memoria.core.Attribute;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.metrics.CollectionResult;
/*    */ import lrg.metrics.Result;
/*    */ import lrg.metrics.classes.ClassMeasure;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AttributeMeasure
/*    */   extends ClassMeasure
/*    */ {
/*    */   public abstract Result measure(Attribute paramAttribute);
/*    */   
/* 23 */   protected boolean filter(Attribute att, Class cls) { return (att.getScope() == cls); }
/*    */ 
/*    */ 
/*    */   
/*    */   public Result measure(Class cls) {
/* 28 */     CollectionResult cr = new CollectionResult();
/* 29 */     ModelElementList modelElementList = cls.getAttributeList();
/*    */     
/* 31 */     int size = modelElementList.size();
/* 32 */     for (int i = 0; i < size; i++) {
/* 33 */       Attribute act_attribute = (Attribute)modelElementList.get(i);
/* 34 */       if (filter(act_attribute, cls))
/* 35 */         cr.add(measure(act_attribute)); 
/*    */     } 
/* 37 */     return cr;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\attributes\AttributeMeasure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */