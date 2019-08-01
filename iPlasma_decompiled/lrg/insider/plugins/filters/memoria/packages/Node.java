/*    */ package classes.lrg.insider.plugins.filters.memoria.packages;
/*    */ 
/*    */ import graph.AbstractNode;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.plugins.filters.memoria.packages.Node;
/*    */ 
/*    */ public class Node
/*    */   extends AbstractNode
/*    */ {
/*    */   final AbstractEntityInterface entity;
/*    */   
/* 12 */   public Node(AbstractEntityInterface wrapper) { this.entity = wrapper; }
/*    */ 
/*    */   
/* 15 */   public AbstractEntityInterface getWrapper() { return this.entity; }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public int hashCode() { return this.entity.hashCode(); }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 23 */     if (o == null) return false; 
/* 24 */     if (o instanceof Node)
/* 25 */       return this.entity.getName().equals(((Node)o).entity.getName()); 
/* 26 */     return false;
/*    */   }
/*    */ 
/*    */   
/* 30 */   public String toString() { return this.entity.getName(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\packages\Node.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */