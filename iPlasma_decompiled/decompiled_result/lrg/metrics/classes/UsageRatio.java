/*     */ package lrg.metrics.classes;
/*     */ 
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
/*     */ 
/*     */ public class UsageRatio
/*     */   extends TwoClassesMeasure
/*     */ {
/*     */   protected boolean filter(Class cls1, Class cls2) {
/*  51 */     if (cls1.getStatute() == 1 && 
/*  52 */       cls2.getStatute() == 1 && 
/*  53 */       cls1.getAncestorsList().contains(cls2)) return true; 
/*     */     return false;
/*     */   }
/*     */   public Result measure(Class child, Class ancestor) {
/*  57 */     double temp, nd = 0.0D;
/*     */ 
/*     */     
/*  60 */     ModelElementList modelElementList1 = child.getMethodList();
/*  61 */     ModelElementList modelElementList2 = ancestor.getMethodList();
/*  62 */     HashSet ua = new HashSet(), um = new HashSet();
/*     */     
/*     */     int i;
/*     */     
/*  66 */     for (i = 0; i < modelElementList1.size(); i++) {
/*  67 */       Method crtm = (Method)modelElementList1.get(i);
/*  68 */       if (!crtm.isConstructor()) {
/*     */         int j;
/*  70 */         for (j = 0; j < modelElementList2.size(); j++) {
/*  71 */           Method rewmeth = (Method)modelElementList2.get(j);
/*  72 */           if (rewmeth.getName() == crtm.getName() && rewmeth.isProtected()) {
/*  73 */             boolean bool; ModelElementList modelElementList5 = crtm.getParameterList();
/*  74 */             ModelElementList modelElementList6 = rewmeth.getParameterList();
/*  75 */             if (modelElementList5.size() == modelElementList6.size()) { bool = true; }
/*  76 */             else { bool = false; }
/*  77 */              for (int l = 0; l < modelElementList5.size(); l++) {
/*  78 */               if (((Parameter)modelElementList5.get(l)).getType() != (
/*  79 */                 (Parameter)modelElementList6.get(l)).getType())
/*  80 */                 bool = false; 
/*  81 */             }  if (bool) um.add(crtm);
/*     */           
/*     */           } 
/*     */         } 
/*  85 */         ModelElementList modelElementList3 = crtm.getBody().getAccessList();
/*  86 */         for (j = 0; j < modelElementList3.size(); j++) {
/*     */           Attribute crta; try {
/*  88 */             crta = 
/*  89 */               (Attribute)((Access)modelElementList3.get(j)).getVariable();
/*  90 */           } catch (ClassCastException e) {}
/*     */ 
/*     */           
/*     */           try {
/*  94 */             if ((Class)crta.getScope() == ancestor && 
/*  95 */               crta.isProtected()) ua.add(crta); 
/*  96 */           } catch (ClassCastException classCastException) {}
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 101 */         ModelElementList modelElementList4 = crtm.getBody().getCallList();
/* 102 */         for (j = 0; j < modelElementList4.size(); j++) {
/*     */           Method crtcm; try {
/* 104 */             crtcm = 
/* 105 */               (Method)((Call)modelElementList4.get(j)).getFunction();
/* 106 */           } catch (ClassCastException e) {}
/*     */ 
/*     */           
/*     */           try {
/* 110 */             if ((Class)crtcm.getScope() == ancestor && 
/* 111 */               crtcm.isProtected() && !crtcm.isConstructor()) um.add(crtcm); 
/* 112 */           } catch (ClassCastException classCastException) {}
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 118 */     double na = (ua.size() + um.size());
/* 119 */     for (i = 0; i < ancestor.getAttributeList().size(); i++) {
/* 120 */       Attribute crta = (Attribute)ancestor.getAttributeList().get(i);
/* 121 */       if (crta.isProtected()) nd++; 
/*     */     } 
/* 123 */     for (i = 0; i < ancestor.getMethodList().size(); i++) {
/* 124 */       Method crtcm = (Method)ancestor.getMethodList().get(i);
/* 125 */       if (crtcm.isProtected()) nd++;
/*     */     
/*     */     } 
/* 128 */     if (na == 0.0D) { temp = 0.0D; }
/* 129 */     else if (nd == 0.0D) { temp = 2.147483647E9D; }
/* 130 */     else { temp = na / nd; }
/*     */     
/* 132 */     return new NumericalResult(child, temp);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\UsageRatio.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */