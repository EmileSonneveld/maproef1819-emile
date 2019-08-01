/*    */ package classes.lrg.insider.plugins.groups.membrain;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ 
/*    */ public class MethodsDefinedInClass
/*    */   extends GroupBuilder
/*    */ {
/* 15 */   public MethodsDefinedInClass() { super("virtual methods of class", "", "class"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface entity) {
/* 20 */     GroupEntity allVirtualMethods = ((DataAbstraction)entity).getGroup("method group");
/* 21 */     allVirtualMethods = allVirtualMethods.applyFilter(new NotComposedFilteringRule(new FilteringRule("is constructor", "is true", "method")));
/* 22 */     allVirtualMethods = allVirtualMethods.applyFilter(new FilteringRule("is public", "is true", "method"));
/* 23 */     allVirtualMethods = allVirtualMethods.applyFilter(new NotComposedFilteringRule(new FilteringRule("is static", "is true", "method")));
/* 24 */     return allVirtualMethods.getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\MethodsDefinedInClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */