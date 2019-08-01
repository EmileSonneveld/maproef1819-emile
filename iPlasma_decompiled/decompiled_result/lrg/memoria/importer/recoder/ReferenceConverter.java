/*     */ package lrg.memoria.importer.recoder;
/*     */ import lrg.memoria.core.Class;
/*     */ import recoder.abstraction.ClassType;
/*     */ import recoder.abstraction.Method;
/*     */ import recoder.abstraction.Package;
/*     */ import recoder.abstraction.Type;
/*     */ import recoder.java.declaration.VariableSpecification;
/*     */ import recoder.java.expression.operator.New;
/*     */ import recoder.java.reference.MethodReference;
/*     */ import recoder.java.reference.TypeReference;
/*     */ import recoder.java.reference.VariableReference;
/*     */ import recoder.parser.TokenMgrError;
/*     */ 
/*     */ public class ReferenceConverter {
/*  15 */   private static SourceInfo srcInfo = null;
/*  16 */   private static ModelRepository mr = null;
/*     */   
/*     */   static void cleanUp() {
/*  19 */     setSrcInfo(null);
/*  20 */     setMr(null);
/*     */   }
/*     */   public static Package getPackage(PackageReference pr) {
/*     */     String packageName;
/*  24 */     Package pack = getSrcInfo().getPackage(pr);
/*     */     
/*  26 */     if (pack == null) {
/*     */       
/*  28 */       packageName = pr.getName();
/*     */     } else {
/*     */       
/*  31 */       packageName = pack.getFullName();
/*     */     } 
/*  33 */     return getMr().addPackage(pack, packageName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Class getClassType(TypeReference tr) {
/*     */     Class mmmc;
/*  39 */     ClassType ct = null;
/*     */     try {
/*  41 */       ct = (ClassType)getSrcInfo().getType(tr);
/*  42 */     } catch (Exception e) {
/*  43 */       System.out.println(e);
/*  44 */       e.printStackTrace();
/*  45 */       System.out.println("Failed Dependency Error; Exception occured while getting the ClassType\"" + tr.getName() + "\" from TypeReference");
/*  46 */     } catch (TokenMgrError e) {
/*  47 */       System.out.println(e);
/*  48 */       e.printStackTrace();
/*     */     } 
/*  50 */     if (ct == null) {
/*     */       
/*  52 */       mmmc = getMr().addClass(tr, tr.getName());
/*     */     } else {
/*  54 */       mmmc = getMemoriaClass(ct);
/*  55 */     }  return mmmc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Class getMemoriaClass(ClassType ct) {
/*  63 */     ClassType scope = ct.getContainingClassType();
/*  64 */     mmmc = getMr().getClass(ct);
/*     */     
/*  66 */     if (scope != null && scope.getName().compareTo("<unknownClassType>") != 0 && mmmc == null) {
/*  67 */       Class clazz = getMemoriaClass(scope);
/*  68 */       mmmc = getMr().addClass(ct, ct.getName());
/*  69 */       mmmc.setScope(clazz);
/*  70 */       clazz.addInnerType(mmmc);
/*  71 */       return mmmc;
/*     */     } 
/*  73 */     return getMr().addClass(ct, ct.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public static Type getTypeFromTypeReference(TypeReference tr) {
/*  78 */     Type ct = null;
/*     */     try {
/*  80 */       ct = getSrcInfo().getType(tr);
/*  81 */     } catch (Exception e) {
/*  82 */       System.out.println(e);
/*  83 */       e.printStackTrace();
/*  84 */       System.out.println("Failed Dependency Error; Exception occured while getting the type\"" + tr.getName() + "\" from TypeReference");
/*  85 */     } catch (TokenMgrError e) {
/*  86 */       System.out.println(e);
/*  87 */       e.printStackTrace();
/*     */     } 
/*  89 */     if (ct == null) {
/*     */       
/*  91 */       if (tr.getName().equals("void")) {
/*  92 */         return getMr().addPrimitiveType(tr.getName(), tr.getName());
/*     */       }
/*  94 */       return getMr().addClass(tr, tr.getName());
/*     */     } 
/*     */     
/*  97 */     return getType(ct);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Type getType(Type ct) {
/* 102 */     while (ct instanceof ArrayType) {
/* 103 */       ct = ((ArrayType)ct).getBaseType();
/*     */     }
/* 105 */     if (ct instanceof PrimitiveType)
/* 106 */       return getMr().addPrimitiveType(((PrimitiveType)ct).getFullName(), ((PrimitiveType)ct).getName()); 
/* 107 */     if (ct == null) {
/* 108 */       return getMr().addPrimitiveType("void", "void");
/*     */     }
/* 110 */     return getMemoriaClass((ClassType)ct);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getArrayDimension(TypeReference tr) {
/* 115 */     Type ct = null;
/*     */     try {
/* 117 */       ct = getSrcInfo().getType(tr);
/* 118 */     } catch (Exception e) {
/* 119 */       System.out.println(e);
/* 120 */       e.printStackTrace();
/* 121 */       System.out.println("Failed Dependency ERROR: Exception while testing if \"" + tr.getName() + "\" is an array !");
/* 122 */     } catch (TokenMgrError e) {
/* 123 */       System.out.println(e);
/* 124 */       e.printStackTrace();
/*     */     } 
/* 126 */     return getArrayDimension(ct);
/*     */   }
/*     */   
/*     */   public static int getArrayDimension(Type ct) {
/* 130 */     int dimensions = 0;
/* 131 */     while (ct instanceof ArrayType) {
/* 132 */       ct = ((ArrayType)ct).getBaseType();
/* 133 */       dimensions++;
/*     */     } 
/* 135 */     return dimensions;
/*     */   }
/*     */   
/*     */   public static Attribute getField(FieldReference fr) {
/* 139 */     Field fld = getSrcInfo().getField(fr);
/* 140 */     if (fld == null) {
/* 141 */       return getMr().addAttribute(fr, fr.getName());
/*     */     }
/* 143 */     return getMr().addAttribute(fld, fld.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public static Variable getVariable(VariableReference vr) {
/*     */     LocalVariable localVariable;
/* 149 */     VariableSpecification var = (VariableSpecification)getSrcInfo().getVariable(vr);
/* 150 */     if (var == null) {
/* 151 */       return getMr().addLocalVar(vr, vr.getName(), getMr().getCurrentStripe());
/*     */     }
/* 153 */     if (var.getParent() instanceof recoder.java.declaration.ParameterDeclaration && 
/* 154 */       !(var.getParent().getASTParent() instanceof recoder.java.statement.Catch)) {
/* 155 */       localVariable = getMr().addParameter(var, var.getName());
/*     */     } else {
/* 157 */       localVariable = getMr().addLocalVar(var, var.getName(), null);
/*     */     } 
/*     */     
/* 160 */     return localVariable;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Method getMethod(MethodReference metr) {
/* 165 */     Method met = null;
/*     */     try {
/* 167 */       met = getSrcInfo().getMethod(metr);
/* 168 */     } catch (Exception e) {
/*     */ 
/*     */       
/* 171 */       System.out.println("Failed Dependency Error in the \"MethodReference\": " + metr.getName() + "from position: " + metr.getStartPosition());
/* 172 */     } catch (TokenMgrError tokenMgrError) {}
/*     */ 
/*     */ 
/*     */     
/* 176 */     if (met == null) {
/* 177 */       return getMr().addMethod(metr, metr.getName());
/*     */     }
/*     */     
/* 180 */     return getMr().addMethod(met, met.getName());
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
/*     */   public static Method getConstructor(New cr) {
/* 194 */     Method met = null;
/*     */     try {
/* 196 */       Constructor constructor = getSrcInfo().getConstructor(cr);
/* 197 */     } catch (Exception e) {
/* 198 */       System.out.println(e);
/* 199 */       System.out.println("Failed Dependency Error in the \"New\" from position: " + cr.getStartPosition());
/* 200 */       met = null;
/* 201 */     } catch (TokenMgrError e) {
/* 202 */       System.out.println(e);
/*     */     } 
/* 204 */     if (met == null) {
/* 205 */       return getMr().addMethod(cr, cr.getTypeReference().getName());
/*     */     }
/* 207 */     return getMr().addMethod(met, met.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static SourceInfo getSrcInfo() {
/* 213 */     if (srcInfo == null)
/* 214 */       srcInfo = JavaModelLoader.getSourceInfo(); 
/* 215 */     return srcInfo;
/*     */   }
/*     */ 
/*     */   
/* 219 */   public static void setSrcInfo(SourceInfo srcInfo) { ReferenceConverter.srcInfo = srcInfo; }
/*     */ 
/*     */   
/*     */   public static ModelRepository getMr() {
/* 223 */     if (mr == null)
/* 224 */       mr = DefaultModelRepository.getModelRepository(null); 
/* 225 */     return mr;
/*     */   }
/*     */ 
/*     */   
/* 229 */   public static void setMr(ModelRepository mr) { ReferenceConverter.mr = mr; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\ReferenceConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */