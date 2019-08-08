/*    */ package lrg.metrics.attributes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
/*    */ import lrg.memoria.core.Access;
/*    */ import lrg.memoria.core.Attribute;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FeatureChangingClasses
/*    */   extends AttributeMeasure
/*    */ {
/*    */   public Result measure(Attribute att) {
/* 47 */     int count = 0;
/* 48 */     Class cls = (Class)att.getScope();
/* 49 */     ArrayList ml = new ArrayList();
/*    */ 
/*    */     
/* 52 */     HashSet cl = new HashSet();
/*    */     
/* 54 */     ModelElementList modelElementList = att.getAccessList();
/* 55 */     for (int j = 0; j < modelElementList.size(); j++) {
/*    */       Method crtmeth; try {
/* 57 */         crtmeth = 
/* 58 */           (Method)((Access)modelElementList.get(j)).getScope().getScope();
/* 59 */       } catch (ClassCastException e) {}
/*    */ 
/*    */       
/* 62 */       ml.add(crtmeth);
/*    */     } 
/*    */     
/* 65 */     for (int i = 0; i < ml.size(); i++) {
/* 66 */       if (!((Method)ml.get(i)).isConstructor()) {
/*    */         Class crtcls; try {
/* 68 */           crtcls = (Class)((Method)ml.get(i)).getScope();
/* 69 */         } catch (ClassCastException e) {}
/*    */ 
/*    */         
/* 72 */         if (!cl.contains(crtcls) && !crtcls.isAbstract() && crtcls != cls) {
/* 73 */           cl.add(crtcls);
/* 74 */           count++;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 79 */     return new NumericalResult(cls, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\attributes\FeatureChangingClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */