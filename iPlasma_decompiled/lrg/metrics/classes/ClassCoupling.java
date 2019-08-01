/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.metrics.NumericalResult;
/*    */ import lrg.metrics.Result;
/*    */ import lrg.metrics.methods.ClassTypeLocalVars;
/*    */ import lrg.metrics.methods.ClassTypeParameters;
/*    */ import lrg.metrics.methods.NumberOfStaticCalls;
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
/*    */ public class ClassCoupling
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/* 55 */     double count = 0.0D;
/* 56 */     ExtendedClasses cm_ex = new ExtendedClasses();
/* 57 */     ClassTypeAttributes cm_cta = new ClassTypeAttributes();
/* 58 */     ClassTypeParameters mm_ctp = new ClassTypeParameters();
/* 59 */     NumberOfStaticCalls mm_nsc = new NumberOfStaticCalls();
/* 60 */     ClassTypeLocalVars mm_ctlv = new ClassTypeLocalVars();
/*    */     
/* 62 */     count += ((NumericalResult)cm_ex.measure(c)).getValue();
/*    */     
/* 64 */     count += ((NumericalResult)cm_cta.measure(c)).getValue();
/*    */ 
/*    */     
/* 67 */     ModelElementList modelElementList = c.getMethodList();
/* 68 */     int size = modelElementList.size();
/* 69 */     for (int i = 0; i < size; i++) {
/* 70 */       Method actMethod = (Method)modelElementList.get(i);
/* 71 */       if (actMethod.getScope() == c) {
/*    */         
/* 73 */         count += 2.0D * ((NumericalResult)mm_ctp.measure(actMethod)).getValue();
/*    */         
/* 75 */         count += 2.0D * ((NumericalResult)mm_nsc.measure(actMethod)).getValue();
/*    */         
/* 77 */         count += 3.0D * ((NumericalResult)mm_ctlv.measure(actMethod)).getValue();
/*    */       } 
/*    */     } 
/* 80 */     return new NumericalResult(c, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ClassCoupling.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */