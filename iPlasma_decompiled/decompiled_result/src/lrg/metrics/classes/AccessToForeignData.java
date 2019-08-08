/*     */ package lrg.metrics.classes;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import lrg.memoria.core.Access;
/*     */ import lrg.memoria.core.Attribute;
/*     */ import lrg.memoria.core.Call;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.InitializerBody;
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
/*     */ public class AccessToForeignData
/*     */   extends ClassMeasure
/*     */ {
/*     */   public Result measure(Class cls) {
/*  43 */     int count = 0;
/*  44 */     ModelElementList modelElementList1 = cls.getMethodList();
/*     */     
/*  46 */     ArrayList accesses = new ArrayList();
/*     */ 
/*     */ 
/*     */     
/*  50 */     HashSet classes = new HashSet();
/*     */     
/*     */     int i;
/*     */     
/*  54 */     for (i = 0; i < modelElementList1.size(); i++) {
/*  55 */       Method crtMethod = (Method)modelElementList1.get(i);
/*  56 */       if (!crtMethod.isConstructor()) {
/*  57 */         ModelElementList modelElementList3 = crtMethod.getBody().getAccessList();
/*  58 */         ModelElementList modelElementList4 = crtMethod.getBody().getCallList();
/*     */         
/*  60 */         for (int k = 0; k < modelElementList4.size(); k++) {
/*  61 */           Method accMethod = (Method)((Call)modelElementList4.get(k)).getFunction();
/*  62 */           if (accMethod.isAccessor()) {
/*  63 */             ModelElementList modelElementList = accMethod.getBody().getAccessList();
/*  64 */             modelElementList3.addAll(modelElementList);
/*     */           } 
/*     */         } 
/*  67 */         accesses.addAll(modelElementList3);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  73 */     ModelElementList modelElementList2 = cls.getInitializerList();
/*     */     
/*  75 */     for (i = 0; i < modelElementList2.size(); i++) {
/*  76 */       ModelElementList modelElementList3 = ((InitializerBody)modelElementList2.get(i)).getAccessList();
/*  77 */       ModelElementList modelElementList4 = ((InitializerBody)modelElementList2.get(i)).getCallList();
/*     */       
/*  79 */       for (int k = 0; k < modelElementList4.size(); k++) {
/*  80 */         Method accMethod = (Method)((Call)modelElementList4.get(k)).getFunction();
/*  81 */         if (accMethod.isAccessor()) {
/*  82 */           ModelElementList modelElementList = accMethod.getBody().getAccessList();
/*  83 */           modelElementList3.addAll(modelElementList);
/*     */         } 
/*     */       } 
/*  86 */       accesses.addAll(modelElementList3);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  91 */     for (int j = 0; j < accesses.size(); j++) {
/*  92 */       Attribute crtAttribute; Access crtAccess = (Access)accesses.get(j);
/*     */       try {
/*  94 */         crtAttribute = (Attribute)crtAccess.getVariable();
/*  95 */       } catch (ClassCastException e) {}
/*     */ 
/*     */       
/*  98 */       Class crtAttClass = (Class)crtAttribute.getScope();
/*     */       
/* 100 */       if (!classes.contains(crtAttClass) && 
/* 101 */         !cls.getAncestorsList().contains(crtAttClass) && 
/* 102 */         crtAttClass != cls) {
/* 103 */         classes.add(crtAttClass);
/* 104 */         count++;
/*     */       } 
/*     */     } 
/*     */     
/* 108 */     return new NumericalResult(cls, count);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\AccessToForeignData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */