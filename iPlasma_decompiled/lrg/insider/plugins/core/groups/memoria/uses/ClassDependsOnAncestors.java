/*    */ package classes.lrg.insider.plugins.core.groups.memoria.uses;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.core.groups.memoria.uses.ClassDependsOnAncestors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassDependsOnAncestors
/*    */   extends GroupBuilder
/*    */ {
/* 21 */   public ClassDependsOnAncestors() { super("all ancestors", "", "class"); }
/*    */ 
/*    */ 
/*    */   
/* 25 */   private boolean noCycles(AbstractEntityInterface child, AbstractEntity parent) { return (parent.getGroup("base classes").intersect(child).size() == 0); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 29 */     HashSet<AbstractEntity> setOfBaseClasses = new HashSet<AbstractEntity>();
/* 30 */     HashSet<AbstractEntity> tmp = new HashSet<AbstractEntity>();
/*    */     
/* 32 */     if (!(anEntity instanceof lrg.memoria.core.Class)) {
/* 33 */       return new ArrayList();
/*    */     }
/* 35 */     if (anEntity.getName().compareTo("<unknownClassType>") == 0)
/* 36 */       return new ArrayList(); 
/* 37 */     setOfBaseClasses.addAll(anEntity.getGroup("base classes").getElements());
/* 38 */     if (setOfBaseClasses.isEmpty()) return new ArrayList();
/*    */     
/* 40 */     tmp.addAll(setOfBaseClasses);
/* 41 */     for (AbstractEntity crtClass : setOfBaseClasses) {
/* 42 */       if (noCycles(anEntity, crtClass)) {
/* 43 */         GroupEntity allAncestors = crtClass.getGroup("all ancestors");
/* 44 */         tmp.addAll(allAncestors.getElements()); continue;
/*    */       } 
/* 46 */       System.out.println("CLASS-->ANCESTOR CYCLE: " + anEntity.getName() + " --> " + crtClass.getName());
/*    */     } 
/* 48 */     return new ArrayList(tmp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memori\\uses\ClassDependsOnAncestors.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */