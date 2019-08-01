/*    */ package classes.lrg.insider.plugins.groups.membrain.Inheritance;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.core.properties.memoria.inheritance.Scope;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.InheritanceRelation;
/*    */ 
/*    */ 
/*    */ public class AllInheritanceRelations
/*    */   extends GroupBuilder
/*    */ {
/* 15 */   public AllInheritanceRelations() { super("all inheritance relations", "All inheritance relation in the system", "system"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface entity) {
/* 19 */     ArrayList res = new ArrayList();
/* 20 */     Iterator it = Scope.getElements().iterator();
/* 21 */     while (it.hasNext()) {
/* 22 */       InheritanceRelation inh = (InheritanceRelation)it.next();
/* 23 */       Class sub = inh.getSubClass();
/* 24 */       Class sup = inh.getSuperClass();
/* 25 */       if (sub instanceof Class && sub.getStatute() == 1 && 
/* 26 */         sup instanceof Class && sup.getStatute() == 1) {
/* 27 */         res.add(inh);
/*    */       }
/*    */     } 
/*    */     
/* 31 */     return res;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\Inheritance\AllInheritanceRelations.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */