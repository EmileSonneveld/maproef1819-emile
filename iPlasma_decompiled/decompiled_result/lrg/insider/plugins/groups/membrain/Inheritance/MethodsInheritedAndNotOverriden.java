/*    */ package classes.lrg.insider.plugins.groups.membrain.Inheritance;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.InheritanceRelation;
/*    */ 
/*    */ 
/*    */ public class MethodsInheritedAndNotOverriden
/*    */   extends GroupBuilder
/*    */ {
/* 15 */   public MethodsInheritedAndNotOverriden() { super("methods inherited but not overriden", "", "inheritance relation"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface entity) {
/* 20 */     InheritanceRelation inh = (InheritanceRelation)entity;
/* 21 */     GroupEntity allSuperObjectMethods = inh.getSuperClass().getGroup("virtual methods of object");
/* 22 */     GroupEntity allSubClassMethods = inh.getSubClass().getGroup("virtual methods of class");
/* 23 */     Iterator anIt = allSubClassMethods.iterator();
/* 24 */     while (anIt.hasNext()) {
/* 25 */       AbstractEntity t = (AbstractEntity)anIt.next();
/* 26 */       allSuperObjectMethods = allSuperObjectMethods.exclude(t.getGroup("methods overriden"));
/*    */     } 
/* 28 */     return allSuperObjectMethods.getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\Inheritance\MethodsInheritedAndNotOverriden.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */