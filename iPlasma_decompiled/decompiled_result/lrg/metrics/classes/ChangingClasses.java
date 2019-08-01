/*     */ package lrg.metrics.classes;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import lrg.memoria.core.Access;
/*     */ import lrg.memoria.core.Attribute;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChangingClasses
/*     */   extends ClassMeasure
/*     */ {
/*     */   public Result measure(Class cls) {
/*  51 */     int count = 0;
/*     */     
/*  53 */     ArrayList ml = new ArrayList();
/*  54 */     ModelElementList modelElementList1 = cls.getMethodList(), modelElementList2 = cls.getAttributeList();
/*     */ 
/*     */     
/*  57 */     HashSet cl = new HashSet();
/*     */     
/*     */     int i;
/*  60 */     for (i = 0; i < modelElementList2.size(); i++) {
/*  61 */       ModelElementList modelElementList = ((Attribute)modelElementList2.get(i)).getAccessList();
/*  62 */       for (int j = 0; j < modelElementList.size(); j++) {
/*     */         Method crtmeth; try {
/*  64 */           crtmeth = 
/*  65 */             (Method)((Access)modelElementList.get(j)).getScope().getScope();
/*  66 */         } catch (ClassCastException e) {}
/*     */ 
/*     */         
/*  69 */         ml.add(crtmeth);
/*     */       } 
/*     */     } 
/*  72 */     for (i = 0; i < modelElementList1.size(); i++) {
/*  73 */       Method rewmeth = (Method)modelElementList1.get(i);
/*  74 */       ModelElementList modelElementList3 = rewmeth.getCallList(); int j;
/*  75 */       for (j = 0; j < modelElementList3.size(); j++) {
/*     */         Method crtmeth; try {
/*  77 */           crtmeth = 
/*  78 */             (Method)((Call)modelElementList3.get(j)).getScope().getScope();
/*  79 */         } catch (ClassCastException e) {}
/*     */ 
/*     */         
/*  82 */         ml.add(crtmeth);
/*     */       } 
/*  84 */       ModelElementList modelElementList4 = cls.getDescendants();
/*  85 */       for (j = 0; j < modelElementList4.size(); j++) {
/*     */         Class crtcls; try {
/*  87 */           crtcls = (Class)modelElementList4.get(j);
/*  88 */         } catch (ClassCastException e) {}
/*     */ 
/*     */         
/*  91 */         ModelElementList modelElementList = crtcls.getMethodList();
/*  92 */         for (int k = 0; k < modelElementList.size(); k++) {
/*  93 */           boolean bool; Method crtmeth = (Method)modelElementList.get(k);
/*  94 */           ModelElementList modelElementList5 = crtmeth.getParameterList();
/*  95 */           ModelElementList modelElementList6 = rewmeth.getParameterList();
/*  96 */           if (modelElementList5.size() == modelElementList6.size()) { bool = true; }
/*  97 */           else { bool = false; }
/*  98 */            for (int l = 0; l < modelElementList5.size(); l++) {
/*  99 */             if (((Parameter)modelElementList5.get(l)).getType() != (
/* 100 */               (Parameter)modelElementList6.get(l)).getType())
/* 101 */               bool = false; 
/* 102 */           }  if (crtmeth.getName() == rewmeth.getName() && bool) {
/* 103 */             ml.add(crtmeth);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 108 */     for (i = 0; i < ml.size(); i++) {
/* 109 */       if (!((Method)ml.get(i)).isConstructor()) {
/*     */         Class crtcls; try {
/* 111 */           crtcls = (Class)((Method)ml.get(i)).getScope();
/* 112 */         } catch (ClassCastException e) {}
/*     */ 
/*     */         
/* 115 */         if (!cl.contains(crtcls) && !crtcls.isAbstract() && crtcls != cls) {
/* 116 */           cl.add(crtcls);
/* 117 */           count++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     return new NumericalResult(cls, count);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ChangingClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */