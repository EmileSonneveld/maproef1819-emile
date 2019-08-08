/*    */ package lrg.memoria.importer.mcc.loader;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import lrg.memoria.core.InheritanceRelation;
/*    */ import lrg.memoria.core.Type;
/*    */ import lrg.memoria.core.TypedefDecorator;
/*    */ 
/*    */ public class DefaultInheritanceVisitor implements InheritanceVisitor {
/*    */   private DataAbstraction ancestor;
/*    */   private DataAbstraction descendant;
/*    */   private String inhAttribute;
/*    */   private int depth;
/*    */   
/*    */   public void setId(Integer id) {}
/*    */   
/*    */   public void setDescendentId(String descendentId) {
/*    */     try {
/* 19 */       int dID = Integer.parseInt(descendentId);
/* 20 */       this.descendant = (DataAbstraction)Loader.getInstance().getType(new Integer(dID));
/*    */     }
/* 22 */     catch (NumberFormatException e) {
/* 23 */       this.descendant = Class.getUnknownClass();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setParentId(String parentId) {
/*    */     try {
/* 29 */       int pID = Integer.parseInt(parentId);
/* 30 */       Type tmp = Loader.getInstance().getType(new Integer(pID));
/*    */       
/* 32 */       if (tmp instanceof TypedefDecorator) {
/* 33 */         while (!(tmp instanceof Class)) {
/* 34 */           tmp = ((TypedefDecorator)tmp).getDecoratedType();
/*    */         }
/*    */       }
/* 37 */       this.ancestor = (DataAbstraction)tmp;
/* 38 */       if (this.ancestor == null) {
/* 39 */         this.ancestor = Class.getUnknownClass();
/*    */       }
/* 41 */       if (tmp != null && tmp instanceof Class) {
/* 42 */         this.ancestor = (DataAbstraction)tmp;
/*    */       } else {
/* 44 */         this.ancestor = Class.getUnknownClass();
/*    */       } 
/* 46 */     } catch (NumberFormatException e) {
/* 47 */       this.ancestor = Class.getUnknownClass();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public void setAttribute(String attribute) { this.inhAttribute = attribute; }
/*    */ 
/*    */ 
/*    */   
/* 57 */   public void setDepth(Integer d) { this.depth = d.intValue(); }
/*    */ 
/*    */   
/*    */   public void addInh() {
/* 61 */     if (this.depth == 1) {
/* 62 */       byte type; this.descendant.addAncestor(this.ancestor);
/* 63 */       this.ancestor.addDescendant(this.descendant);
/*    */       
/* 65 */       if (this.inhAttribute.equals("public")) { type = 0; }
/* 66 */       else if (this.inhAttribute.equals("protected")) { type = 2; }
/* 67 */       else { type = 1; }
/* 68 */        InheritanceRelation rel = new InheritanceRelation((Class)this.descendant, (Class)this.ancestor, type);
/* 69 */       this.ancestor.addRelationAsAncestor(rel);
/* 70 */       this.descendant.addRelationAsDescendent(rel);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\DefaultInheritanceVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */