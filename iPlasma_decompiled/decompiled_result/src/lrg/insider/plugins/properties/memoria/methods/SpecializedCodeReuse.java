/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.properties.memoria.methods.SpecializedCodeReuse;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Parameter;
/*    */ 
/*    */ public class SpecializedCodeReuse extends PropertyComputer {
/* 17 */   public SpecializedCodeReuse() { super("SCR", "Specialized Code Reuse", "method", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface m) {
/* 22 */     if (!(m instanceof Method)) {
/* 23 */       return new ResultEntity(-1.0D);
/*    */     }
/*    */     
/* 26 */     Method theMethod = (Method)m;
/* 27 */     GroupEntity allConcreteDescendants = theMethod.belongsTo("class").getGroup("all descendants").applyFilter(
/* 28 */         new NotComposedFilteringRule(
/* 29 */           new FilteringRule("is abstract", "is true", "class", null)));
/*    */     
/* 31 */     double reuse = 0.0D;
/* 32 */     double total = allConcreteDescendants.size();
/*    */     
/* 34 */     if (total == 0.0D) {
/* 35 */       return new ResultEntity(-1.0D);
/*    */     }
/*    */     
/* 38 */     if (theMethod.isConstructor() || theMethod.isPrivate() || theMethod.isStatic() || theMethod.isProtected() || theMethod.isPackage()) {
/* 39 */       return new ResultEntity(-1.0D);
/*    */     }
/*    */     
/* 42 */     if (theMethod.isAbstract()) {
/* 43 */       return new ResultEntity(0.0D);
/*    */     }
/*    */     
/* 46 */     Iterator it = allConcreteDescendants.iterator();
/* 47 */     while (it.hasNext()) {
/* 48 */       DataAbstraction aClass = (DataAbstraction)it.next();
/* 49 */       Method my = findMyMethod(aClass, theMethod);
/* 50 */       if (my != theMethod && isSpecialization(my, theMethod)) {
/* 51 */         reuse++;
/*    */       }
/*    */     } 
/* 54 */     return new ResultEntity(reuse / total);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean isSpecialization(Method my, Method initial) {
/* 59 */     if (initial.getGroup("operations calling me").isInGroup(my)) {
/* 60 */       return true;
/*    */     }
/* 62 */     GroupEntity called = my.getGroup("operations called");
/* 63 */     Iterator it = called.iterator();
/* 64 */     while (it.hasNext()) {
/* 65 */       Method other = (Method)it.next();
/* 66 */       if (isOverriding(other, initial) && isSpecialization(other, initial)) {
/* 67 */         return true;
/*    */       }
/*    */     } 
/* 70 */     return false;
/*    */   }
/*    */   
/*    */   private Method findMyMethod(DataAbstraction aClass, Method initial) {
/* 74 */     GroupEntity methods = aClass.getGroup("method group");
/* 75 */     Iterator it = methods.iterator();
/* 76 */     while (it.hasNext()) {
/* 77 */       Method tmp = (Method)it.next();
/* 78 */       if (isOverriding(tmp, initial)) {
/* 79 */         return tmp;
/*    */       }
/*    */     } 
/* 82 */     GroupEntity superClass = aClass.getGroup("base classes").applyFilter(
/* 83 */         new NotComposedFilteringRule(
/* 84 */           new FilteringRule("is interface", "is true", "class", null)));
/* 85 */     return findMyMethod((DataAbstraction)superClass.getElementAt(0), initial);
/*    */   }
/*    */   
/*    */   private boolean isOverriding(Method aMethod, Method ancestorMethod) {
/* 89 */     if (aMethod.getName().compareTo(ancestorMethod.getName()) != 0) return false; 
/* 90 */     ModelElementList<Parameter> methodParameters = aMethod.getParameterList();
/* 91 */     ModelElementList<Parameter> ancestorsParameters = ancestorMethod.getParameterList();
/* 92 */     if (methodParameters.size() != ancestorsParameters.size()) return false; 
/* 93 */     ArrayList paramTypes = new ArrayList();
/* 94 */     ArrayList paramTypesOfAncestor = new ArrayList();
/* 95 */     for (Iterator it = methodParameters.iterator(); it.hasNext(); paramTypes.add(((Parameter)it.next()).getType()));
/* 96 */     for (Iterator it = ancestorsParameters.iterator(); it.hasNext(); paramTypesOfAncestor.add(((Parameter)it.next()).getType()));
/* 97 */     return paramTypes.containsAll(paramTypesOfAncestor);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\SpecializedCodeReuse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */