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
/*     */ public class ChangingMethods
/*     */   extends ClassMeasure
/*     */ {
/*     */   public Result measure(Class cls) {
/*  49 */     int count = 0;
/*     */     
/*  51 */     ArrayList ml = new ArrayList();
/*  52 */     ModelElementList modelElementList1 = cls.getMethodList(), modelElementList2 = cls.getAttributeList();
/*     */ 
/*     */     
/*  55 */     HashSet cl = new HashSet();
/*     */     
/*     */     int i;
/*  58 */     for (i = 0; i < modelElementList2.size(); i++) {
/*  59 */       ModelElementList modelElementList = ((Attribute)modelElementList2.get(i)).getAccessList();
/*  60 */       for (int j = 0; j < modelElementList.size(); j++) {
/*     */         Method crtmeth; try {
/*  62 */           crtmeth = 
/*  63 */             (Method)((Access)modelElementList.get(j)).getScope().getScope();
/*  64 */         } catch (ClassCastException e) {}
/*     */ 
/*     */         
/*  67 */         ml.add(crtmeth);
/*     */       } 
/*     */     } 
/*  70 */     for (i = 0; i < modelElementList1.size(); i++) {
/*  71 */       Method rewmeth = (Method)modelElementList1.get(i);
/*     */       
/*  73 */       ModelElementList modelElementList3 = rewmeth.getCallList(); int j;
/*  74 */       for (j = 0; j < modelElementList3.size(); j++) {
/*     */         Method crtmeth; try {
/*  76 */           crtmeth = 
/*  77 */             (Method)((Call)modelElementList3.get(j)).getScope().getScope();
/*  78 */         } catch (ClassCastException e) {}
/*     */ 
/*     */         
/*  81 */         ml.add(crtmeth);
/*     */       } 
/*  83 */       ModelElementList modelElementList4 = cls.getDescendants();
/*  84 */       for (j = 0; j < modelElementList4.size(); j++) {
/*     */         Class crtcls; try {
/*  86 */           crtcls = (Class)modelElementList4.get(j);
/*  87 */         } catch (ClassCastException e) {}
/*     */ 
/*     */         
/*  90 */         ModelElementList modelElementList = crtcls.getMethodList();
/*  91 */         for (int k = 0; k < modelElementList.size(); k++) {
/*  92 */           boolean bool; Method crtmeth = (Method)modelElementList.get(k);
/*  93 */           ModelElementList modelElementList5 = crtmeth.getParameterList();
/*  94 */           ModelElementList modelElementList6 = rewmeth.getParameterList();
/*  95 */           if (modelElementList5.size() == modelElementList6.size()) { bool = true; }
/*  96 */           else { bool = false; }
/*  97 */            for (int l = 0; l < modelElementList5.size(); l++) {
/*  98 */             if (((Parameter)modelElementList5.get(l)).getType() != (
/*  99 */               (Parameter)modelElementList6.get(l)).getType())
/* 100 */               bool = false; 
/* 101 */           }  if (crtmeth.getName() == rewmeth.getName() && bool) {
/* 102 */             ml.add(crtmeth);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 107 */     for (i = 0; i < ml.size(); i++) {
/* 108 */       if (!((Method)ml.get(i)).isConstructor()) {
/* 109 */         Class crtcls; Method crtmeth = (Method)ml.get(i);
/*     */         try {
/* 111 */           crtcls = (Class)crtmeth.getScope();
/* 112 */         } catch (ClassCastException e) {}
/*     */ 
/*     */         
/* 115 */         if (!cl.contains(crtmeth) && !crtcls.isAbstract() && crtcls != cls) {
/* 116 */           cl.add(crtmeth);
/* 117 */           count++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     return new NumericalResult(cls, count);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ChangingMethods.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */