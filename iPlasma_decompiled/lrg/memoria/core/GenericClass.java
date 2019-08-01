/*    */ package lrg.memoria.core;
/*    */ 
/*    */ 
/*    */ public class GenericClass
/*    */   extends Class
/*    */ {
/*    */   private ModelElementList<TemplateParameterType> templateParameters;
/*    */   private ModelElementList<TemplateInstance> templateInstances;
/*    */   
/*    */   public GenericClass(String name) {
/* 11 */     super(name);
/* 12 */     this.templateParameters = new ModelElementList();
/* 13 */     this.templateInstances = new ModelElementList();
/*    */   }
/*    */   
/*    */   protected GenericClass(GenericClass oldClass) {
/* 17 */     super(oldClass);
/* 18 */     this.templateParameters = oldClass.templateParameters;
/* 19 */     this.templateInstances = new ModelElementList();
/*    */   }
/*    */ 
/*    */   
/* 23 */   public ModelElementList<TemplateParameterType> getTemplateParameters() { return this.templateParameters; }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public void addTemplateParameters(TemplateParameterType tpt) { this.templateParameters.add(tpt); }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public void addTemplateInstance(TemplateInstance ti) { this.templateInstances.add(ti); }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public ModelElementList<TemplateInstance> getTemplateInstances() { return this.templateInstances; }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     StringBuffer myStr = new StringBuffer("\t\t");
/* 41 */     myStr.append("GenericClass: ");
/* 42 */     myStr.append(getFullName());
/* 43 */     myStr.append("\n\t\t - location: ").append(getLocation());
/* 44 */     myStr.append("\n\t\t - scope: ");
/* 45 */     if (getScope() != null)
/* 46 */       myStr.append(getScope().getName()); 
/* 47 */     myStr.append("\n\t\t - supertypes: ");
/* 48 */     int i = 0;
/* 49 */     for (DataAbstraction currentSupertype : getAncestorsList()) {
/* 50 */       myStr.append(currentSupertype.getName());
/* 51 */       myStr.append(",");
/* 52 */       i++;
/*    */     } 
/* 54 */     if (i > 0) {
/* 55 */       int tmp = myStr.length();
/* 56 */       myStr.delete(tmp - 2, tmp).append(".");
/*    */     } 
/* 58 */     myStr.append("\n\t\t - descendants: ");
/* 59 */     i = 0;
/* 60 */     for (DataAbstraction currentDescendant : getDescendants()) {
/* 61 */       myStr.append(currentDescendant.getName()).append(", ");
/* 62 */       i++;
/*    */     } 
/* 64 */     if (i > 0) {
/* 65 */       int tmp = myStr.length();
/* 66 */       myStr.delete(tmp - 2, tmp).append(".");
/*    */     } 
/* 68 */     myStr.append("\n\t\t - attributes:\n");
/* 69 */     for (i = 0; i < this.attributes.size(); i++)
/* 70 */       myStr.append((Attribute)this.attributes.get(i)); 
/* 71 */     myStr.append("\t\t - methods:\n");
/* 72 */     for (Method currentMethod : this.methods)
/* 73 */       myStr.append(currentMethod); 
/* 74 */     return new String(myStr);
/*    */   }
/*    */   
/*    */   boolean restore() {
/* 78 */     if (super.restore()) {
/* 79 */       this.templateInstances.restore();
/* 80 */       this.templateParameters.restore();
/* 81 */       return true;
/*    */     } 
/* 83 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\GenericClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */