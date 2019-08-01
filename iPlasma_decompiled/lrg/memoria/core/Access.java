/*     */ package lrg.memoria.core;
/*     */ 
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
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
/*     */ public class Access
/*     */   extends ModelElement
/*     */ {
/*     */   private final Variable accessedVariable;
/*     */   private final CodeStripe accessingStripe;
/*     */   private Body scope;
/*     */   private int numberOfAccesses;
/*     */   private ArrayList<Location> readInstances;
/*     */   private ArrayList<Location> writeInstances;
/*     */   public static final int READ = 1;
/*     */   public static final int WRITE = 2;
/*     */   
/*     */   public Access(Variable what, Body scope) {
/*  43 */     this.accessedVariable = what;
/*  44 */     this.scope = scope;
/*  45 */     this.numberOfAccesses = 0;
/*  46 */     this.readInstances = new ArrayList();
/*  47 */     this.writeInstances = new ArrayList();
/*  48 */     this.accessingStripe = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Access(Variable what, CodeStripe fromWhere) {
/*  59 */     this.accessedVariable = what;
/*  60 */     this.numberOfAccesses = 0;
/*  61 */     this.readInstances = new ArrayList();
/*  62 */     this.writeInstances = new ArrayList();
/*  63 */     this.accessingStripe = fromWhere;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/*  72 */     if (this.writeInstances.size() > 0) {
/*  73 */       return 2;
/*     */     }
/*  75 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*  79 */   public int getCount() { return this.numberOfAccesses; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public void setCount(int count) { this.numberOfAccesses = count; }
/*     */ 
/*     */   
/*     */   public void addInstance(Location loc, int type) {
/*  90 */     if (type == 1) {
/*  91 */       this.readInstances.add(loc);
/*     */     } else {
/*  93 */       this.writeInstances.add(loc);
/*  94 */     }  this.numberOfAccesses++;
/*     */   }
/*     */   
/*     */   public ArrayList<Location> getInstanceList() {
/*  98 */     ArrayList<Location> tmp = new ArrayList<Location>(this.readInstances);
/*  99 */     tmp.addAll(this.writeInstances);
/* 100 */     return tmp;
/*     */   }
/*     */ 
/*     */   
/* 104 */   public ArrayList<Location> getReadInstanceList() { return this.readInstances; }
/*     */ 
/*     */ 
/*     */   
/* 108 */   public ArrayList<Location> getWriteInstanceList() { return this.writeInstances; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public Variable getVariable() { return this.accessedVariable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Body getScope() {
/* 123 */     if (this.accessingStripe == null) return this.scope; 
/* 124 */     return this.accessingStripe.getParentBody();
/*     */   }
/*     */ 
/*     */   
/* 128 */   public Body getBody() { return this.accessingStripe.getParentBody(); }
/*     */ 
/*     */ 
/*     */   
/* 132 */   public CodeStripe getStripe() { return this.accessingStripe; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   ArrayList<Location> extractReadInstancesWithin(Location range) { return Location.extractLocationsWithin(range, this.readInstances); }
/*     */ 
/*     */ 
/*     */   
/* 141 */   ArrayList<Location> extractWriteInstancesWithin(Location range) { return Location.extractLocationsWithin(range, this.writeInstances); }
/*     */ 
/*     */ 
/*     */   
/* 145 */   public void accept(ModelVisitor v) { v.visitAccess(this); }
/*     */ 
/*     */   
/*     */   public void writeXML(FileWriter output) throws IOException {}
/*     */ 
/*     */   
/*     */   public String toString() {
/* 152 */     StringBuffer myStr = new StringBuffer();
/*     */     
/* 154 */     if (this.accessedVariable instanceof Attribute && this.accessedVariable.getStatute() != 3)
/* 155 */       myStr.append(((Attribute)this.accessedVariable).getScope().getFullName()).append("."); 
/* 156 */     myStr.append(this.accessedVariable.getName());
/* 157 */     myStr.append("(").append(this.numberOfAccesses).append(",");
/* 158 */     if (this.writeInstances.size() > 0) {
/* 159 */       myStr.append("WRITE");
/*     */     } else {
/* 161 */       myStr.append("READ");
/* 162 */     }  myStr.append(")");
/* 163 */     return new String(myStr);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Access.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */