/*     */ package lrg.metrics.classes;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import lrg.memoria.core.Access;
/*     */ import lrg.memoria.core.Call;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.FunctionBody;
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
/*     */ public class CouplingBetweenObjects
/*     */   extends ClassMeasure
/*     */ {
/*     */   private HashSet used_classes;
/*     */   
/*     */   private int para_retTypesCount(Function act_method) {
/*  38 */     int types_count = 0;
/*     */ 
/*     */ 
/*     */     
/*  42 */     ModelElementList modelElementList = act_method.getParameterList();
/*  43 */     int size = modelElementList.size();
/*  44 */     for (int j = 0; j < size; j++) {
/*  45 */       Parameter act_param = (Parameter)modelElementList.get(j);
/*  46 */       Type act_type = act_param.getType();
/*  47 */       if (act_type instanceof Class && !this.used_classes.contains(act_type) && 
/*  48 */         act_param.getAccessList().contains(act_method.getScope())) {
/*  49 */         this.used_classes.add(act_type);
/*  50 */         types_count++;
/*     */       } 
/*     */     } 
/*  53 */     Type act_type = act_method.getReturnType();
/*  54 */     if (act_type instanceof Class && !this.used_classes.contains(act_type)) {
/*  55 */       this.used_classes.add(act_type);
/*  56 */       types_count++;
/*     */     } 
/*  58 */     return types_count;
/*     */   }
/*     */ 
/*     */   
/*     */   private int accessTypesCount(FunctionBody act_body) {
/*  63 */     int types_count = 0;
/*  64 */     ModelElementList modelElementList = act_body.getAccessList();
/*     */ 
/*     */     
/*  67 */     int size = modelElementList.size();
/*  68 */     for (int j = 0; j < size; j++) {
/*  69 */       Access act_access = (Access)modelElementList.get(j);
/*  70 */       Type act_type = act_access.getVariable().getType();
/*  71 */       if (act_type instanceof Class && !this.used_classes.contains(act_type)) {
/*  72 */         this.used_classes.add(act_type);
/*  73 */         types_count++;
/*     */       } 
/*     */     } 
/*  76 */     return types_count;
/*     */   }
/*     */ 
/*     */   
/*     */   private int callTypesCount(FunctionBody act_body) {
/*  81 */     int types_count = 0;
/*  82 */     ModelElementList modelElementList = act_body.getCallList();
/*     */ 
/*     */     
/*  85 */     int size = modelElementList.size();
/*  86 */     for (int j = 0; j < size; j++) {
/*  87 */       Call act_call = (Call)modelElementList.get(j);
/*  88 */       Method method = act_call.getMethod();
/*  89 */       types_count += para_retTypesCount(method);
/*     */     } 
/*  91 */     return types_count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result measure(Class act_class) {
/*  98 */     int count = 0;
/*     */ 
/*     */ 
/*     */     
/* 102 */     this.used_classes = new HashSet();
/* 103 */     ModelElementList modelElementList = act_class.getMethodList();
/* 104 */     int size = modelElementList.size();
/* 105 */     for (int i = 0; i < size; i++) {
/* 106 */       Method act_method = (Method)modelElementList.get(i);
/* 107 */       if (act_method.getScope() == act_class) {
/* 108 */         count += para_retTypesCount(act_method);
/* 109 */         FunctionBody act_body = act_method.getBody();
/* 110 */         if (act_body != null) {
/* 111 */           count += accessTypesCount(act_body);
/* 112 */           count += callTypesCount(act_body);
/*     */         } 
/*     */       } 
/*     */     } 
/* 116 */     return new NumericalResult(act_class, count);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\CouplingBetweenObjects.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */