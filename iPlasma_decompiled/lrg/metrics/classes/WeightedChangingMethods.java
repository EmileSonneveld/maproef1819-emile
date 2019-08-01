/*     */ package lrg.metrics.classes;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import lrg.memoria.core.Access;
/*     */ import lrg.memoria.core.Attribute;
/*     */ import lrg.memoria.core.Call;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.memoria.core.Variable;
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
/*     */ public class WeightedChangingMethods
/*     */   extends ClassMeasure
/*     */ {
/*     */   public Result measure(Class cls) {
/*  49 */     int count = 0;
/*     */     
/*  51 */     ArrayList ml = new ArrayList();
/*  52 */     ModelElementList modelElementList1 = cls.getMethodList(), modelElementList2 = cls.getAttributeList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     HashSet hs = new HashSet();
/*  59 */     HashSet ms = new HashSet();
/*     */     int i;
/*  61 */     for (i = 0; i < modelElementList2.size(); i++) {
/*  62 */       ModelElementList modelElementList = ((Attribute)modelElementList2.get(i)).getAccessList();
/*  63 */       for (int j = 0; j < modelElementList.size(); j++) {
/*     */         Method crtmeth; try {
/*  65 */           crtmeth = 
/*  66 */             (Method)((Access)modelElementList.get(j)).getScope().getScope();
/*  67 */         } catch (ClassCastException e) {}
/*     */ 
/*     */         
/*  70 */         if (!ms.contains(crtmeth)) {
/*  71 */           ms.add(crtmeth);
/*  72 */           ml.add(crtmeth);
/*     */         } 
/*     */       } 
/*     */     } 
/*  76 */     for (i = 0; i < modelElementList1.size(); i++) {
/*  77 */       Method rewmeth = (Method)modelElementList1.get(i);
/*  78 */       ModelElementList modelElementList3 = rewmeth.getCallList(); int j;
/*  79 */       for (j = 0; j < modelElementList3.size(); j++) {
/*     */         Method crtmeth; try {
/*  81 */           crtmeth = 
/*  82 */             (Method)((Call)modelElementList3.get(j)).getScope().getScope();
/*  83 */         } catch (ClassCastException e) {}
/*     */ 
/*     */         
/*  86 */         if (!ms.contains(crtmeth)) {
/*  87 */           ms.add(crtmeth);
/*  88 */           ml.add(crtmeth);
/*     */         } 
/*     */       } 
/*  91 */       ModelElementList modelElementList4 = cls.getDescendants();
/*  92 */       for (j = 0; j < modelElementList4.size(); j++) {
/*     */         Class crtcls; try {
/*  94 */           crtcls = (Class)modelElementList4.get(j);
/*  95 */         } catch (ClassCastException e) {}
/*     */ 
/*     */         
/*  98 */         ModelElementList modelElementList = crtcls.getMethodList();
/*  99 */         for (int k = 0; k < modelElementList.size(); k++) {
/* 100 */           boolean bool; Method crtmeth = (Method)modelElementList.get(k);
/* 101 */           ModelElementList modelElementList5 = crtmeth.getParameterList();
/* 102 */           ModelElementList modelElementList6 = rewmeth.getParameterList();
/* 103 */           if (modelElementList5.size() == modelElementList6.size()) { bool = true; }
/* 104 */           else { bool = false; }
/* 105 */            for (int l = 0; l < modelElementList5.size(); l++) {
/* 106 */             if (((Parameter)modelElementList5.get(l)).getType() != (
/* 107 */               (Parameter)modelElementList6.get(l)).getType())
/* 108 */               bool = false; 
/* 109 */           }  if (crtmeth.getName() == rewmeth.getName() && bool && 
/* 110 */             !ms.contains(crtmeth)) {
/* 111 */             ms.add(crtmeth);
/* 112 */             count++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 118 */     for (i = 0; i < ml.size(); i++) {
/* 119 */       Method crtmeth = (Method)ml.get(i);
/* 120 */       int accs = 0;
/* 121 */       if (!crtmeth.isConstructor()) {
/*     */         Class crtcls; try {
/* 123 */           crtcls = (Class)crtmeth.getScope();
/* 124 */         } catch (ClassCastException e) {}
/*     */ 
/*     */         
/* 127 */         if (!crtcls.isAbstract()) {
/* 128 */           ModelElementList modelElementList4 = crtmeth.getBody().getAccessList();
/* 129 */           hs.clear(); int j;
/* 130 */           for (j = 0; j < modelElementList4.size(); j++) {
/* 131 */             Variable crtvar = ((Access)modelElementList4.get(j)).getVariable();
/* 132 */             if (crtvar.getScope() == cls && !hs.contains(crtvar)) {
/* 133 */               hs.add(crtvar);
/* 134 */               accs++;
/*     */             } 
/*     */           } 
/* 137 */           ModelElementList modelElementList3 = crtmeth.getBody().getCallList();
/* 138 */           hs.clear();
/* 139 */           for (j = 0; j < modelElementList3.size(); j++) {
/* 140 */             Function crtfcn = ((Call)modelElementList3.get(j)).getFunction();
/* 141 */             if (crtfcn.getScope() == cls && !hs.contains(crtfcn)) {
/* 142 */               hs.add(crtfcn);
/* 143 */               accs++;
/*     */             } 
/*     */           } 
/* 146 */           count += accs;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 151 */     return new NumericalResult(cls, count);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\WeightedChangingMethods.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */