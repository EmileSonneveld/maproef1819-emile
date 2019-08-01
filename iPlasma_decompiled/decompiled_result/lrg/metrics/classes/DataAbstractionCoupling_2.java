/*     */ package lrg.metrics.classes;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import lrg.memoria.core.Attribute;
/*     */ import lrg.memoria.core.Body;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.FunctionBody;
/*     */ import lrg.memoria.core.InitializerBody;
/*     */ import lrg.memoria.core.LocalVariable;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.memoria.core.Type;
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
/*     */ public class DataAbstractionCoupling_2
/*     */   extends ClassMeasure
/*     */ {
/*     */   private HashSet used_class_types;
/*     */   private int count;
/*     */   
/*     */   private void add(Type type) {
/*  41 */     if (type instanceof Class && !this.used_class_types.contains(type)) {
/*  42 */       this.used_class_types.add(type);
/*  43 */       this.count++;
/*     */     } 
/*     */   }
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
/*     */   public Result measure(Class cls) {
/*  61 */     this.count = 0;
/*  62 */     this.used_class_types = new HashSet();
/*     */     
/*  64 */     this.used_class_types.add(cls);
/*     */ 
/*     */     
/*  67 */     addAttributes(cls);
/*     */     
/*  69 */     ModelElementList modelElementList2 = cls.getInitializerList();
/*  70 */     int size = modelElementList2.size(); int i;
/*  71 */     for (i = 0; i < size; i++) {
/*  72 */       InitializerBody act_initBody = (InitializerBody)modelElementList2.get(i);
/*  73 */       addLocalVars(act_initBody);
/*     */     } 
/*     */     
/*  76 */     ModelElementList modelElementList1 = cls.getMethodList();
/*  77 */     size = modelElementList1.size();
/*  78 */     for (i = 0; i < size; i++) {
/*  79 */       Method act_method = (Method)modelElementList1.get(i);
/*  80 */       if (act_method.getScope() == cls) {
/*  81 */         addParameters(act_method);
/*  82 */         FunctionBody act_methodBody = act_method.getBody();
/*  83 */         addLocalVars(act_methodBody);
/*     */       } 
/*     */     } 
/*     */     
/*  87 */     return new NumericalResult(cls, this.count);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addAttributes(Class cls) {
/*  95 */     ModelElementList modelElementList = cls.getAttributeList();
/*  96 */     int size = modelElementList.size();
/*  97 */     for (int i = 0; i < size; i++) {
/*  98 */       Attribute act_attr = (Attribute)modelElementList.get(i);
/*     */       
/* 100 */       add(act_attr.getType());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addParameters(Function act_method) {
/* 109 */     ModelElementList modelElementList = act_method.getParameterList();
/* 110 */     int size = modelElementList.size();
/* 111 */     for (int j = 0; j < size; j++) {
/* 112 */       Type act_type = ((Parameter)modelElementList.get(j)).getType();
/* 113 */       add(act_type);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addLocalVars(Body act_body) {
/* 122 */     if (act_body != null) {
/* 123 */       ModelElementList modelElementList = act_body.getLocalVarList();
/* 124 */       int size = modelElementList.size();
/* 125 */       for (int j = 0; j < size; j++) {
/* 126 */         Type act_type = ((LocalVariable)modelElementList.get(j)).getType();
/* 127 */         add(act_type);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\DataAbstractionCoupling_2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */