/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
/*    */ import lrg.memoria.core.Access;
/*    */ import lrg.memoria.core.Call;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Package;
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
/*    */ public class ClassLocality
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class cls) {
/* 34 */     double temp, den = 0.0D, nom = 0.0D;
/*    */     
/* 36 */     ArrayList cl = new ArrayList();
/* 37 */     ModelElementList modelElementList = cls.getMethodList();
/*    */     
/* 39 */     Package pck = cls.getPackage();
/*    */     
/* 41 */     HashSet pl = new HashSet();
/*    */     int i;
/* 43 */     for (i = 0; i < modelElementList.size(); i++) {
/* 44 */       Method cm = (Method)modelElementList.get(i);
/*    */       
/* 46 */       ModelElementList modelElementList2 = cm.getBody().getAccessList(); int j;
/* 47 */       for (j = 0; j < modelElementList2.size(); j++) {
/*    */         Class crtcls; try {
/* 49 */           crtcls = 
/* 50 */             (Class)((Access)modelElementList2.get(j)).getVariable().getScope();
/* 51 */         } catch (ClassCastException e) {}
/*    */ 
/*    */         
/* 54 */         cl.add(crtcls);
/*    */       } 
/*    */       
/* 57 */       ModelElementList modelElementList1 = cm.getBody().getCallList();
/* 58 */       for (j = 0; j < modelElementList1.size(); j++) {
/*    */         Method crtmeth; try {
/* 60 */           crtmeth = (Method)((Call)modelElementList1.get(j)).getFunction();
/* 61 */         } catch (ClassCastException e) {}
/*    */ 
/*    */ 
/*    */         
/* 65 */         if (!crtmeth.isConstructor()) {
/*    */           Class crtcls;
/*    */           try {
/* 68 */             crtcls = (Class)crtmeth.getScope();
/* 69 */           } catch (ClassCastException e) {}
/*    */ 
/*    */           
/* 72 */           cl.add(crtcls);
/*    */         } 
/*    */       } 
/*    */     } 
/* 76 */     for (i = 0; i < cl.size(); i++) {
/* 77 */       Class crtcls = (Class)cl.get(i);
/* 78 */       Package crtpck = crtcls.getPackage();
/* 79 */       if (!pl.contains(crtpck) && !crtcls.isAbstract() && crtcls != cls) {
/* 80 */         if (crtpck == pck)
/* 81 */           den++; 
/* 82 */         pl.add(crtpck);
/* 83 */         nom++;
/*    */       } 
/*    */     } 
/*    */     
/* 87 */     if (den == 0.0D) {
/* 88 */       temp = 0.0D;
/* 89 */     } else if (nom == 0.0D) {
/* 90 */       temp = 2.147483647E9D;
/*    */     } else {
/* 92 */       temp = den / nom;
/*    */     } 
/* 94 */     return new NumericalResult(cls, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ClassLocality.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */