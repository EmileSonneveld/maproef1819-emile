/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.groups.memoria.InheritanceRelation;
/*    */ 
/*    */ public class AllInheritanceRelations
/*    */   extends GroupBuilder
/*    */ {
/* 14 */   public AllInheritanceRelations() { super("all inheritance relations", "All inheritance relation in the system", new String[] { "system", "package root", "package" }); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aSystem) {
/* 18 */     ArrayList resultGroup = new ArrayList();
/* 19 */     GroupEntity allClasses = aSystem.getGroup("class group").applyFilter("model class");
/*    */     
/* 21 */     for (Iterator iterator = allClasses.iterator(); iterator.hasNext(); ) {
/* 22 */       AbstractEntity crtClass = (AbstractEntity)iterator.next();
/* 23 */       GroupEntity derivedClasses = crtClass.getGroup("derived classes");
/* 24 */       for (Iterator iterator2 = derivedClasses.iterator(); iterator2.hasNext();)
/* 25 */         resultGroup.add(new InheritanceRelation(crtClass, (AbstractEntity)iterator2.next())); 
/*    */     } 
/* 27 */     return resultGroup;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\AllInheritanceRelations.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */