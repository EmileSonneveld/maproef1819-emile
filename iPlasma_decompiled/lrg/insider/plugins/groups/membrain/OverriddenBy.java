/*    */ package classes.lrg.insider.plugins.groups.membrain;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.groups.membrain.OverriddenBy;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Parameter;
/*    */ 
/*    */ public class OverriddenBy
/*    */   extends GroupBuilder
/*    */ {
/* 16 */   public OverriddenBy() { super("overridden by", "", "method"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface entity) {
/* 21 */     Method aMethod = (Method)entity;
/* 22 */     if ((!aMethod.isPublic() && !aMethod.isProtected()) || aMethod.isStatic() || aMethod.isConstructor()) return new ArrayList();
/*    */     
/* 24 */     GroupEntity allDescendantsMethods = aMethod.belongsTo("class").getGroup("all descendants").getGroup("method group");
/* 25 */     ArrayList result = new ArrayList();
/* 26 */     Iterator it = allDescendantsMethods.iterator();
/* 27 */     while (it.hasNext()) {
/* 28 */       Method tested = (Method)it.next();
/* 29 */       if (isOverriding(tested, aMethod)) {
/* 30 */         result.add(tested);
/*    */       }
/*    */     } 
/* 33 */     return result;
/*    */   }
/*    */   
/*    */   private boolean isOverriding(Method aMethod, Method ancestorMethod) {
/* 37 */     if (aMethod.getName().compareTo(ancestorMethod.getName()) != 0) return false; 
/* 38 */     ModelElementList<Parameter> methodParameters = aMethod.getParameterList();
/* 39 */     ModelElementList<Parameter> ancestorsParameters = ancestorMethod.getParameterList();
/* 40 */     if (methodParameters.size() != ancestorsParameters.size()) return false; 
/* 41 */     ArrayList paramTypes = new ArrayList();
/* 42 */     ArrayList paramTypesOfAncestor = new ArrayList();
/* 43 */     for (Iterator it = methodParameters.iterator(); it.hasNext(); paramTypes.add(((Parameter)it.next()).getType()));
/* 44 */     for (Iterator it = ancestorsParameters.iterator(); it.hasNext(); paramTypesOfAncestor.add(((Parameter)it.next()).getType()));
/* 45 */     return paramTypes.containsAll(paramTypesOfAncestor);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\OverriddenBy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */