/*     */ package lrg.metrics.packages;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import lrg.memoria.core.Access;
/*     */ import lrg.memoria.core.Attribute;
/*     */ import lrg.memoria.core.Call;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.Package;
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
/*     */ public class PackageInterfaceSize
/*     */   extends PackageMeasure
/*     */ {
/*     */   public Result measure(Package pck) {
/*  44 */     ModelElementList modelElementList = pck.getScopedElements();
/*     */     
/*  46 */     HashSet hs = new HashSet();
/*     */     
/*  48 */     for (int f = 0; f < modelElementList.size(); f++) {
/*     */       Class crtcl; try {
/*  50 */         crtcl = (Class)modelElementList.get(f);
/*  51 */       } catch (ClassCastException e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  56 */       ArrayList ml = new ArrayList();
/*  57 */       ModelElementList modelElementList1 = crtcl.getMethodList(), modelElementList2 = crtcl.getAttributeList();
/*     */ 
/*     */ 
/*     */       
/*  61 */       HashSet cl = new HashSet();
/*     */       
/*     */       int i;
/*  64 */       for (i = 0; i < modelElementList2.size(); i++) {
/*  65 */         ModelElementList modelElementList3 = ((Attribute)modelElementList2.get(i)).getAccessList();
/*  66 */         for (int j = 0; j < modelElementList3.size(); j++) {
/*     */           Method crtmeth; try {
/*  68 */             crtmeth = 
/*  69 */               (Method)((Access)modelElementList3.get(j)).getScope().getScope();
/*  70 */           } catch (ClassCastException e) {}
/*     */ 
/*     */           
/*  73 */           ml.add(crtmeth);
/*     */         } 
/*     */       } 
/*  76 */       for (i = 0; i < modelElementList1.size(); i++) {
/*  77 */         Method rewmeth = (Method)modelElementList1.get(i);
/*  78 */         ModelElementList modelElementList3 = rewmeth.getCallList(); int j;
/*  79 */         for (j = 0; j < modelElementList3.size(); j++) {
/*     */           Method crtmeth; try {
/*  81 */             crtmeth = 
/*  82 */               (Method)((Call)modelElementList3.get(j)).getScope().getScope();
/*  83 */           } catch (ClassCastException e) {}
/*     */ 
/*     */           
/*  86 */           ml.add(crtmeth);
/*     */         } 
/*  88 */         ModelElementList modelElementList4 = crtcl.getDescendants();
/*  89 */         for (j = 0; j < modelElementList4.size(); j++) {
/*     */           Class crtcls; try {
/*  91 */             crtcls = (Class)modelElementList4.get(j);
/*  92 */           } catch (ClassCastException e) {}
/*     */ 
/*     */           
/*  95 */           ModelElementList modelElementList5 = crtcls.getMethodList();
/*  96 */           for (int k = 0; k < modelElementList5.size(); k++) {
/*  97 */             boolean bool; Method crtmeth = (Method)modelElementList5.get(k);
/*  98 */             ModelElementList modelElementList6 = crtmeth.getParameterList();
/*  99 */             ModelElementList modelElementList7 = rewmeth.getParameterList();
/* 100 */             if (modelElementList6.size() == modelElementList7.size()) {
/* 101 */               bool = true;
/*     */             } else {
/* 103 */               bool = false;
/* 104 */             }  for (int l = 0; l < modelElementList6.size(); l++) {
/* 105 */               if (((Parameter)modelElementList6.get(i)).getType() != (
/* 106 */                 (Parameter)modelElementList7.get(i)).getType())
/* 107 */                 bool = false; 
/* 108 */             }  if (crtmeth.getName() == rewmeth.getName() && bool) {
/* 109 */               ml.add(crtmeth);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 114 */       for (i = 0; i < ml.size(); i++) {
/* 115 */         if (!((Method)ml.get(i)).isConstructor()) {
/*     */           Class crtcls; try {
/* 117 */             crtcls = (Class)((Method)ml.get(i)).getScope();
/* 118 */           } catch (ClassCastException e) {}
/*     */ 
/*     */           
/* 121 */           if (!cl.contains(crtcls) && !crtcls.isAbstract()) {
/* 122 */             cl.add(crtcls);
/* 123 */             Package crtpack = crtcls.getPackage();
/* 124 */             if (crtpack != pck) {
/* 125 */               hs.add(crtcl);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 131 */     for (int i = 0; i < modelElementList.size(); i++) {
/*     */       Class crtcl; try {
/* 133 */         crtcl = (Class)modelElementList.get(i);
/* 134 */       } catch (ClassCastException e) {}
/*     */ 
/*     */       
/* 137 */       ModelElementList modelElementList1 = crtcl.getDescendants();
/* 138 */       for (int j = 0; j < modelElementList1.size(); j++) {
/*     */         try {
/* 140 */           if (((Class)modelElementList1.get(j)).getScope() != pck)
/* 141 */             hs.add(crtcl); 
/* 142 */         } catch (ClassCastException classCastException) {}
/*     */       } 
/*     */     } 
/*     */     
/* 146 */     return new NumericalResult(pck, hs.size());
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\packages\PackageInterfaceSize.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */