/*     */ package lrg.metrics.classes;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import lrg.memoria.core.Access;
/*     */ import lrg.memoria.core.Attribute;
/*     */ import lrg.memoria.core.Call;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.ModelElementList;
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
/*     */ public class UsageRatioImplementation
/*     */   extends TwoClassesMeasure
/*     */ {
/*     */   protected boolean filter(Class cls1, Class cls2) {
/*  49 */     if (cls1.getStatute() == 1 && 
/*  50 */       cls2.getStatute() == 1 && 
/*  51 */       cls1.getAncestorsList().contains(cls2)) return true; 
/*     */     return false;
/*     */   }
/*     */   public Result measure(Class child, Class ancestor) {
/*  55 */     double temp, nd = 0.0D;
/*     */     
/*  57 */     ModelElementList modelElementList = child.getMethodList();
/*  58 */     HashSet ua = new HashSet(), um = new HashSet();
/*     */     
/*     */     int i;
/*     */     
/*  62 */     for (i = 0; i < modelElementList.size(); i++) {
/*  63 */       Method crtm = (Method)modelElementList.get(i);
/*  64 */       if (!crtm.isConstructor()) {
/*  65 */         ModelElementList modelElementList1 = crtm.getBody().getAccessList(); int j;
/*  66 */         for (j = 0; j < modelElementList1.size(); j++) {
/*     */           Attribute crta; try {
/*  68 */             crta = 
/*  69 */               (Attribute)((Access)modelElementList1.get(j)).getVariable();
/*  70 */           } catch (ClassCastException e) {}
/*     */ 
/*     */           
/*     */           try {
/*  74 */             if ((Class)crta.getScope() == ancestor && 
/*  75 */               crta.isProtected()) ua.add(crta); 
/*  76 */           } catch (ClassCastException classCastException) {}
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/*  81 */         ModelElementList modelElementList2 = crtm.getBody().getCallList();
/*  82 */         for (j = 0; j < modelElementList2.size(); j++) {
/*     */           Method crtcm; try {
/*  84 */             crtcm = 
/*  85 */               (Method)((Call)modelElementList2.get(j)).getFunction();
/*  86 */           } catch (ClassCastException e) {}
/*     */ 
/*     */           
/*     */           try {
/*  90 */             if ((Class)crtcm.getScope() == ancestor && 
/*  91 */               crtcm.isProtected() && !crtcm.isConstructor()) um.add(crtcm); 
/*  92 */           } catch (ClassCastException classCastException) {}
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  98 */     double na = (ua.size() + um.size());
/*  99 */     for (i = 0; i < ancestor.getAttributeList().size(); i++) {
/* 100 */       Attribute crta = (Attribute)ancestor.getAttributeList().get(i);
/* 101 */       if (crta.isProtected()) nd++; 
/*     */     } 
/* 103 */     for (i = 0; i < ancestor.getMethodList().size(); i++) {
/* 104 */       Method crtcm = (Method)ancestor.getMethodList().get(i);
/* 105 */       if (crtcm.isProtected()) nd++;
/*     */     
/*     */     } 
/* 108 */     if (na == 0.0D) { temp = 0.0D; }
/* 109 */     else if (nd == 0.0D) { temp = 2.147483647E9D; }
/* 110 */     else { temp = na / nd; }
/*     */     
/* 112 */     return new NumericalResult(child, temp);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\UsageRatioImplementation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */