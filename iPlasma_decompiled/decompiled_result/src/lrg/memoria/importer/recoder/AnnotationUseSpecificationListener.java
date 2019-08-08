/*     */ package lrg.memoria.importer.recoder;
/*     */ 
/*     */ import lrg.memoria.core.Annotation;
/*     */ import lrg.memoria.core.AnnotationInstance;
/*     */ import lrg.memoria.core.AnnotationProperty;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.Package;
/*     */ import recoder.java.Expression;
/*     */ import recoder.java.NonTerminalProgramElement;
/*     */ import recoder.java.ProgramElement;
/*     */ import recoder.java.declaration.AnnotationDeclaration;
/*     */ import recoder.java.declaration.AnnotationElementValuePair;
/*     */ import recoder.java.declaration.AnnotationPropertyDeclaration;
/*     */ import recoder.java.declaration.AnnotationUseSpecification;
/*     */ import recoder.java.expression.ElementValueArrayInitializer;
/*     */ import recoder.java.expression.Literal;
/*     */ import recoder.java.reference.VariableReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnnotationUseSpecificationListener
/*     */   implements Listener
/*     */ {
/*     */   private static Listener listener;
/*     */   
/*     */   static  {
/*  32 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.AnnotationUseSpecificationListener", new Factory());
/*     */   }
/*     */   
/*  35 */   static class Factory implements IFactory { public void cleanUp() { listener = 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  40 */         null; }
/*     */ 
/*     */     
/*     */     public Listener getListener() {
/*  44 */       if (listener != null) {
/*  45 */         return listener;
/*     */       }
/*  47 */       listener = new AnnotationUseSpecificationListener(null); return new AnnotationUseSpecificationListener(null);
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   private AnnotationUseSpecificationListener() {}
/*     */ 
/*     */   
/*     */   private String getValueFromExpression(Expression e) {
/*  56 */     String result = "";
/*  57 */     if (e instanceof Literal) {
/*  58 */       Literal l = (Literal)e;
/*  59 */       result = String.valueOf(result) + l.getEquivalentJavaType().toString();
/*     */     }
/*  61 */     else if (e instanceof VariableReference) {
/*  62 */       VariableReference vr = (VariableReference)e;
/*  63 */       result = String.valueOf(result) + vr.getName();
/*     */     }
/*  65 */     else if (e instanceof ElementValueArrayInitializer) {
/*  66 */       ElementValueArrayInitializer ev = (ElementValueArrayInitializer)e;
/*     */       
/*  68 */       for (Expression ex : ev.getElementValues()) {
/*     */         
/*  70 */         String value = getValueFromExpression(ex);
/*  71 */         result = String.valueOf(result) + (isNumber(value) ? value : ("'" + value + "'"));
/*  72 */         result = String.valueOf(result) + " ";
/*     */       } 
/*  74 */       int endIndex = result.length() - 1;
/*  75 */       result = result.substring(0, endIndex);
/*     */     }
/*     */     else {
/*     */       
/*  79 */       result = String.valueOf(result) + "_UNKNOWN";
/*     */     } 
/*  81 */     return result;
/*     */   }
/*     */   private boolean isNumber(String value) {
/*     */     
/*  85 */     try { Double.valueOf(value); } catch (NumberFormatException e) { return false; }
/*  86 */      try { Integer.valueOf(value); } catch (NumberFormatException e) { return false; }
/*     */     
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   public void enterModelComponent(ProgramElement pe) {
/*  92 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*  93 */     AnnotationUseSpecification aus = (AnnotationUseSpecification)pe;
/*     */     
/*  95 */     Annotation a = mr.getAnnotation(ReferenceConverter.getTypeFromTypeReference(aus.getTypeReference()).getFullName());
/*  96 */     if (a == null)
/*     */     {
/*     */       
/*  99 */       a = mr.addAnnotation(aus, ReferenceConverter.getTypeFromTypeReference(aus.getTypeReference()).getFullName());
/*     */     }
/*     */     
/* 102 */     AnnotationInstance ai = new AnnotationInstance(a);
/* 103 */     if (aus.getElementValuePairs() != null) {
/* 104 */       for (int i = 0; i < aus.getElementValuePairs().size(); i++) {
/*     */         String value;
/*     */         try {
/* 107 */           value = getValueFromExpression(((AnnotationElementValuePair)aus.getElementValuePairs().get(i)).getElementValue());
/*     */         }
/* 109 */         catch (Exception e) {
/* 110 */           value = "_UNKNOWN";
/* 111 */           System.out.println("Exception coming from reading annotation property values");
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 117 */         if (a.getAnnotationProperties().size() == 1) {
/* 118 */           AnnotationProperty apr = (AnnotationProperty)a.getAnnotationProperties().get(0);
/* 119 */           ai.addAnotationProperyValuePair(apr, value);
/*     */         } else {
/*     */           
/* 122 */           for (AnnotationProperty apr : a.getAnnotationProperties()) {
/* 123 */             if (apr.getName() == ((AnnotationElementValuePair)aus.getElementValuePairs().get(i)).getElementName()) {
/* 124 */               ai.addAnotationProperyValuePair(apr, value);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 131 */     NonTerminalProgramElement parent = aus.getASTParent();
/*     */     
/* 133 */     if (!(parent instanceof AnnotationDeclaration) && (parent instanceof recoder.java.declaration.ClassDeclaration || parent instanceof recoder.java.declaration.InterfaceDeclaration)) {
/* 134 */       Class clazz = mr.getCurrentClass();
/* 135 */       if (ai != null && clazz != null) {
/* 136 */         clazz.addAnnotationInstance(ai);
/* 137 */         ai.setAnnotatedElement(clazz);
/*     */       }
/*     */     
/*     */     }
/* 141 */     else if (parent instanceof recoder.java.declaration.MethodDeclaration && !(parent instanceof AnnotationPropertyDeclaration)) {
/* 142 */       Method m = mr.getCurrentMethod();
/* 143 */       if (ai != null && m != null) {
/* 144 */         m.addAnnotationInstance(ai);
/* 145 */         ai.setAnnotatedElement(m);
/*     */       }
/*     */     
/* 148 */     } else if (parent instanceof AnnotationDeclaration) {
/* 149 */       Annotation an = mr.getCurrentAnnotation();
/* 150 */       if (ai != null && an != null) {
/* 151 */         an.addAnnotationInstance(ai);
/* 152 */         ai.setAnnotatedElement(an);
/*     */       }
/*     */     
/*     */     }
/* 156 */     else if (!(parent instanceof recoder.java.declaration.EnumConstantDeclaration) && (parent instanceof recoder.java.declaration.FieldDeclaration || parent instanceof recoder.java.declaration.ParameterDeclaration || parent instanceof recoder.java.declaration.LocalVariableDeclaration)) {
/* 157 */       mr.setCurrentAnnotationInstance(ai);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 162 */     else if (parent instanceof AnnotationPropertyDeclaration) {
/* 163 */       AnnotationPropertyDeclaration apd = (AnnotationPropertyDeclaration)parent;
/* 164 */       NonTerminalProgramElement grandparent = parent.getASTParent();
/* 165 */       AnnotationDeclaration ad = (AnnotationDeclaration)grandparent;
/* 166 */       Annotation ano = mr.getAnnotation(ad.getFullName());
/* 167 */       for (AnnotationProperty apr : ano.getAnnotationProperties()) {
/* 168 */         if (apr.getName() == apd.getName() && 
/* 169 */           ai != null) {
/* 170 */           apr.addAnnotationInstance(ai);
/* 171 */           ai.setAnnotatedElement(apr);
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 176 */     } else if (parent instanceof recoder.java.PackageSpecification) {
/* 177 */       Package p = mr.getCurrentPackage();
/* 178 */       if (ai != null && p != null) {
/* 179 */         p.addAnnotationInstance(ai);
/* 180 */         ai.setAnnotatedElement(p);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void leaveModelComponent(ProgramElement pe) {}
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\AnnotationUseSpecificationListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */