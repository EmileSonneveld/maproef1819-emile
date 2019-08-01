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
/*    */ 
/*    */ public class DependencyDispersion
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class cls) {
/* 35 */     int count = 0;
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
/* 64 */         if (!crtmeth.isConstructor()) {
/*    */           Class crtcls; try {
/* 66 */             crtcls = (Class)crtmeth.getScope();
/* 67 */           } catch (ClassCastException e) {}
/*    */ 
/*    */           
/* 70 */           cl.add(crtcls);
/*    */         } 
/*    */       } 
/*    */     } 
/* 74 */     for (i = 0; i < cl.size(); i++) {
/* 75 */       Class crtcls = (Class)cl.get(i);
/* 76 */       Package crtpck = crtcls.getPackage();
/* 77 */       if (!pl.contains(crtpck) && !crtcls.isAbstract() && crtpck != pck) {
/* 78 */         pl.add(crtpck);
/* 79 */         count++;
/*    */       } 
/*    */     } 
/*    */     
/* 83 */     return new NumericalResult(cls, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\DependencyDispersion.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */