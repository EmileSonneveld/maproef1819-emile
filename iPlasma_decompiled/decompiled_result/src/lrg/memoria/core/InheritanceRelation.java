/*    */ package lrg.memoria.core;public class InheritanceRelation extends ModelElement {
/*    */   private ModelElementList<Class> entities;
/*    */   
/*    */   public InheritanceRelation(Class subClass, Class superClass, byte type) {
/*  5 */     this.entities = new ModelElementList();
/*    */ 
/*    */ 
/*    */     
/*  9 */     this.entities.add(superClass);
/* 10 */     this.entities.add(subClass);
/* 11 */     this.type = type;
/*    */   }
/*    */   private byte type;
/*    */   
/* 15 */   public void accept(ModelVisitor v) { v.visitInheritanceRelation(this); }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public Class getSuperClass() { return (Class)this.entities.get(0); }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public Class getSubClass() { return (Class)this.entities.get(1); }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public boolean isPublic() { return (this.type == 0); }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public boolean isPrivate() { return (this.type == 1); }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public boolean isProtected() { return (this.type == 2); }
/*    */ 
/*    */   
/*    */   boolean restore() {
/* 39 */     if (super.restore()) {
/* 40 */       this.entities.restore();
/* 41 */       return true;
/*    */     } 
/* 43 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\InheritanceRelation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */