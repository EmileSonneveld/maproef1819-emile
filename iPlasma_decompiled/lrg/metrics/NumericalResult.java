/*     */ package lrg.metrics;
/*     */ import java.util.TreeSet;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.DataAbstraction;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.Package;
/*     */ import lrg.memoria.core.Parameter;
/*     */ 
/*     */ public class NumericalResult extends Result {
/*  11 */   private double m_value = 0.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String m_what;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericalResult(Method meth, double v) {
/*  22 */     DataAbstraction cls = (DataAbstraction)meth.getScope();
/*  23 */     Package pack = cls.getPackage();
/*  24 */     StringBuffer temp_name = new StringBuffer();
/*     */ 
/*     */     
/*  27 */     temp_name.append(cls.getName());
/*  28 */     temp_name.append("\t");
/*     */     
/*  30 */     temp_name.append(meth.getName());
/*  31 */     temp_name.append("(");
/*  32 */     ModelElementList modelElementList = meth.getParameterList();
/*     */     
/*  34 */     int size = modelElementList.size();
/*  35 */     for (int i = 0; i < size; i++) {
/*  36 */       Parameter param = (Parameter)modelElementList.get(i);
/*  37 */       temp_name.append(param.getType().getName());
/*  38 */       temp_name.append(", ");
/*     */     } 
/*  40 */     if (size > 0) {
/*  41 */       int len = temp_name.length();
/*  42 */       temp_name.delete(len - 2, len);
/*     */     } 
/*  44 */     temp_name.append(")");
/*  45 */     temp_name.append("\t");
/*     */     
/*  47 */     String pack_name = pack.getName();
/*  48 */     if (pack_name.equals("")) {
/*  49 */       temp_name.append("unnamed_package");
/*     */     } else {
/*  51 */       temp_name.append(pack_name);
/*  52 */     }  temp_name.append("\t");
/*  53 */     this.m_what = temp_name.toString();
/*     */     
/*  55 */     this.m_value = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericalResult(Class cls, double v) {
/*  64 */     Package pack = cls.getPackage();
/*  65 */     StringBuffer temp_name = new StringBuffer();
/*     */ 
/*     */     
/*  68 */     temp_name.append(cls.getName());
/*  69 */     temp_name.append("\t");
/*     */     
/*  71 */     String pack_name = pack.getName();
/*  72 */     if (pack_name.equals("")) {
/*  73 */       temp_name.append("unnamed_package");
/*     */     } else {
/*  75 */       temp_name.append(pack_name);
/*  76 */     }  temp_name.append("\t");
/*  77 */     this.m_what = temp_name.toString();
/*     */     
/*  79 */     this.m_value = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericalResult(Package pack, double v) {
/*  89 */     this.m_what = pack.getName();
/*  90 */     if (this.m_what.equals(""))
/*  91 */       this.m_what = "unnamed_package"; 
/*  92 */     this.m_what = String.valueOf(this.m_what) + "\t";
/*     */     
/*  94 */     this.m_value = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public double getValue() { return this.m_value; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SortedSet getResultsSet() {
/* 108 */     TreeSet ts = new TreeSet();
/* 109 */     ts.add(String.valueOf(this.m_what) + this.m_value + "\n");
/* 110 */     return ts;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\NumericalResult.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */