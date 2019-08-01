/*    */ package classes.lrg.insider.plugins.core.groups.memoria.uses;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.core.groups.memoria.uses.ClassUsedByDescendants;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassUsedByDescendants
/*    */   extends GroupBuilder
/*    */ {
/* 20 */   public ClassUsedByDescendants() { super("all descendants", "", "class"); }
/*    */ 
/*    */ 
/*    */   
/* 24 */   private boolean noCycles(AbstractEntityInterface parent, AbstractEntity child) { return (child.getGroup("derived classes").intersect(parent).size() == 0); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 28 */     HashSet<AbstractEntity> setOfDerivedClasses = new HashSet<AbstractEntity>();
/* 29 */     HashSet<AbstractEntity> tmp = new HashSet<AbstractEntity>();
/*    */     
/* 31 */     if (!(anEntity instanceof lrg.memoria.core.Class)) {
/* 32 */       return new ArrayList();
/*    */     }
/* 34 */     setOfDerivedClasses.addAll(anEntity.getGroup("derived classes").getElements());
/* 35 */     if (setOfDerivedClasses.isEmpty()) return new ArrayList();
/*    */     
/* 37 */     tmp.addAll(setOfDerivedClasses);
/*    */     
/* 39 */     for (AbstractEntity crtDerivedClass : setOfDerivedClasses) {
/* 40 */       if (noCycles(anEntity, crtDerivedClass)) {
/* 41 */         GroupEntity allDescendants = crtDerivedClass.getGroup("all descendants");
/* 42 */         tmp.addAll(allDescendants.getElements()); continue;
/*    */       } 
/* 44 */       System.out.println("CLASS-->DESCENDANT CYCLE: " + anEntity.getName() + " --> " + crtDerivedClass.getName());
/*    */     } 
/* 46 */     return new ArrayList(tmp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memori\\uses\ClassUsedByDescendants.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */