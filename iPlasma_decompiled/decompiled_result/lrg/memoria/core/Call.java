/*     */ package lrg.memoria.core;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*     */ public class Call
/*     */   extends ModelElement
/*     */ {
/*     */   private final Function callee;
/*     */   private final CodeStripe sourceStripe;
/*     */   private Body scope;
/*     */   private int count;
/*     */   private ArrayList<Location> callInstances;
/*     */   
/*     */   public Call(Function what, Body scope) {
/*  26 */     this.callee = what;
/*  27 */     this.scope = scope;
/*  28 */     this.count = 0;
/*  29 */     this.callInstances = new ArrayList();
/*  30 */     this.sourceStripe = null;
/*     */   }
/*     */   
/*     */   public Call(Function what, CodeStripe source) {
/*  34 */     this.callee = what;
/*  35 */     this.sourceStripe = source;
/*  36 */     this.callInstances = new ArrayList();
/*  37 */     this.count = 0;
/*     */   }
/*     */ 
/*     */   
/*  41 */   public int getCount() { return this.count; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public void setCount(int count) { this.count = count; }
/*     */ 
/*     */   
/*     */   public void addInstance(Location loc) {
/*  52 */     this.callInstances.add(loc);
/*  53 */     this.count++;
/*     */   }
/*     */ 
/*     */   
/*  57 */   public ArrayList<Location> getInstanceList() { return this.callInstances; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public Method getMethod() { return (Method)this.callee; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public Function getFunction() { return this.callee; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Body getScope() {
/*  80 */     if (this.sourceStripe == null) return this.scope; 
/*  81 */     return this.sourceStripe.getParentBody();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  86 */   public Body getBody() { return this.sourceStripe.getParentBody(); }
/*     */ 
/*     */ 
/*     */   
/*  90 */   public CodeStripe getStripe() { return this.sourceStripe; }
/*     */ 
/*     */ 
/*     */   
/*  94 */   ArrayList<Location> extractInstancesWithin(Location range) { return Location.extractLocationsWithin(range, this.callInstances); }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public void accept(ModelVisitor mv) { mv.visitCall(this); }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 102 */     StringBuffer myStr = new StringBuffer();
/*     */     
/* 104 */     if (this.callee.getStatute() != 3)
/* 105 */       myStr.append(this.callee.getScope().getFullName()).append("."); 
/* 106 */     myStr.append(this.callee.getName());
/* 107 */     myStr.append("(").append(this.count).append(")");
/*     */     
/* 109 */     return new String(myStr);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Call.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */