/*     */ package lrg.metrics.methods;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import lrg.memoria.core.Call;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.metrics.NumericalResult;
/*     */ import lrg.metrics.Result;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FeatureChangingMethods
/*     */   extends MethodMeasure
/*     */ {
/*     */   public Result measure(Method rewmeth) {
/*  46 */     int count = 0;
/*     */     
/*  48 */     ArrayList ml = new ArrayList();
/*     */ 
/*     */     
/*  51 */     HashSet cl = new HashSet();
/*     */ 
/*     */     
/*  54 */     Class cls = (Class)rewmeth.getScope();
/*     */     
/*  56 */     ModelElementList modelElementList1 = rewmeth.getCallList(); int j;
/*  57 */     for (j = 0; j < modelElementList1.size(); j++) {
/*     */       Method crtmeth; try {
/*  59 */         crtmeth = 
/*  60 */           (Method)((Call)modelElementList1.get(j)).getScope().getScope();
/*  61 */       } catch (ClassCastException e) {}
/*     */ 
/*     */       
/*  64 */       ml.add(crtmeth);
/*     */     } 
/*  66 */     ModelElementList modelElementList2 = cls.getDescendants();
/*  67 */     for (j = 0; j < modelElementList2.size(); j++) {
/*     */       Class crtcls; try {
/*  69 */         crtcls = (Class)modelElementList2.get(j);
/*  70 */       } catch (ClassCastException e) {}
/*     */ 
/*     */       
/*  73 */       ModelElementList modelElementList = crtcls.getMethodList();
/*  74 */       for (int k = 0; k < modelElementList.size(); k++) {
/*  75 */         boolean bool; Method crtmeth = (Method)modelElementList.get(k);
/*  76 */         ModelElementList modelElementList3 = crtmeth.getParameterList();
/*  77 */         ModelElementList modelElementList4 = rewmeth.getParameterList();
/*  78 */         if (modelElementList3.size() == modelElementList4.size()) {
/*  79 */           bool = true;
/*     */         } else {
/*  81 */           bool = false;
/*  82 */         }  for (int l = 0; l < modelElementList3.size(); l++) {
/*  83 */           if (((Parameter)modelElementList3.get(l)).getType() != (
/*  84 */             (Parameter)modelElementList4.get(l)).getType())
/*  85 */             bool = false; 
/*  86 */         }  if (crtmeth.getName() == rewmeth.getName() && bool) {
/*  87 */           ml.add(crtmeth);
/*     */         }
/*     */       } 
/*     */     } 
/*  91 */     for (int i = 0; i < ml.size(); i++) {
/*  92 */       if (!((Method)ml.get(i)).isConstructor()) {
/*  93 */         Method crtmeth = (Method)ml.get(i);
/*  94 */         Class crtcls = (Class)crtmeth.getScope();
/*  95 */         if (!cl.contains(crtmeth) && !crtcls.isAbstract()) {
/*  96 */           cl.add(crtmeth);
/*  97 */           count++;
/*     */         } 
/*     */       } 
/*     */     } 
/* 101 */     return new NumericalResult(rewmeth, count);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\FeatureChangingMethods.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */