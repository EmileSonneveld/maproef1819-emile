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
/*     */ public class FeatureChangingClasses
/*     */   extends MethodMeasure
/*     */ {
/*     */   public Result measure(Method rewmeth) {
/*  46 */     int count = 0;
/*  47 */     Class cls = (Class)rewmeth.getScope();
/*     */     
/*  49 */     ArrayList ml = new ArrayList();
/*     */ 
/*     */     
/*  52 */     HashSet cl = new HashSet();
/*     */ 
/*     */     
/*  55 */     ModelElementList modelElementList1 = rewmeth.getCallList(); int j;
/*  56 */     for (j = 0; j < modelElementList1.size(); j++) {
/*     */       Method crtmeth; try {
/*  58 */         crtmeth = 
/*  59 */           (Method)((Call)modelElementList1.get(j)).getScope().getScope();
/*  60 */       } catch (ClassCastException e) {}
/*     */ 
/*     */       
/*  63 */       ml.add(crtmeth);
/*     */     } 
/*  65 */     ModelElementList modelElementList2 = cls.getDescendants();
/*  66 */     for (j = 0; j < modelElementList2.size(); j++) {
/*     */       Class crtcls; try {
/*  68 */         crtcls = (Class)modelElementList2.get(j);
/*  69 */       } catch (ClassCastException e) {}
/*     */ 
/*     */       
/*  72 */       ModelElementList modelElementList = crtcls.getMethodList();
/*  73 */       for (int k = 0; k < modelElementList.size(); k++) {
/*  74 */         boolean bool; Method crtmeth = (Method)modelElementList.get(k);
/*  75 */         ModelElementList modelElementList3 = crtmeth.getParameterList();
/*  76 */         ModelElementList modelElementList4 = rewmeth.getParameterList();
/*  77 */         if (modelElementList3.size() == modelElementList4.size()) {
/*  78 */           bool = true;
/*     */         } else {
/*  80 */           bool = false;
/*  81 */         }  for (int l = 0; l < modelElementList3.size(); l++) {
/*  82 */           if (((Parameter)modelElementList3.get(l)).getType() != (
/*  83 */             (Parameter)modelElementList4.get(l)).getType())
/*  84 */             bool = false; 
/*  85 */         }  if (crtmeth.getName() == rewmeth.getName() && bool) {
/*  86 */           ml.add(crtmeth);
/*     */         }
/*     */       } 
/*     */     } 
/*  90 */     for (int i = 0; i < ml.size(); i++) {
/*  91 */       if (!((Method)ml.get(i)).isConstructor()) {
/*     */         Class crtcls; try {
/*  93 */           crtcls = (Class)((Method)ml.get(i)).getScope();
/*  94 */         } catch (ClassCastException e) {}
/*     */ 
/*     */         
/*  97 */         if (!cl.contains(crtcls) && !crtcls.isAbstract() && crtcls != cls) {
/*  98 */           cl.add(crtcls);
/*  99 */           count++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 104 */     return new NumericalResult(rewmeth, count);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\FeatureChangingClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */