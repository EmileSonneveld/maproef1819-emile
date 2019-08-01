/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import lrg.memoria.core.Attribute;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Type;
/*    */ import lrg.metrics.NumericalResult;
/*    */ import lrg.metrics.Result;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataAbstractionCoupling
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class act_class) {
/* 37 */     ModelElementList modelElementList = act_class.getAttributeList();
/* 38 */     HashSet used_class_types = new HashSet();
/* 39 */     int size = modelElementList.size();
/* 40 */     int count = 0;
/* 41 */     for (int i = 0; i < size; i++) {
/* 42 */       Attribute act_attr = (Attribute)modelElementList.get(i);
/* 43 */       if (act_attr.getScope() == act_class) {
/* 44 */         Type act_type = act_attr.getType();
/* 45 */         if (act_type instanceof Class && !used_class_types.contains(act_type)) {
/* 46 */           used_class_types.add(act_type);
/* 47 */           count++;
/*    */         } 
/*    */       } 
/*    */     } 
/* 51 */     return new NumericalResult(act_class, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\DataAbstractionCoupling.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */