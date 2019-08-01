/*    */ package classes.lrg.insider.plugins.core.groups.memoria.uses;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.core.groups.memoria.uses.MethodOverrides;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Parameter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MethodOverrides
/*    */   extends GroupBuilder
/*    */ {
/* 22 */   public MethodOverrides() { super("methods overriden", "", new String[] { "global function", "method" }); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aFunction) {
/* 26 */     ArrayList resultList = new ArrayList();
/*    */     
/* 28 */     if (!(aFunction instanceof Method)) return resultList;
/*    */     
/* 30 */     Method aMethod = (Method)aFunction;
/*    */     
/* 32 */     if (aMethod.getBody() == null) return resultList;
/*    */     
/* 34 */     GroupEntity methodsOfAncestors = aMethod.belongsTo("class").uses("all ancestors").applyFilter("model class").contains("method group");
/*    */     
/* 36 */     Iterator it = methodsOfAncestors.iterator();
/*    */ 
/*    */     
/* 39 */     while (it.hasNext()) {
/* 40 */       Method ancestorMethod = (Method)it.next();
/* 41 */       if (isOverriding(aMethod, ancestorMethod)) resultList.add(ancestorMethod); 
/*    */     } 
/* 43 */     return resultList;
/*    */   }
/*    */   
/*    */   private boolean isOverriding(Method aMethod, Method ancestorMethod) {
/* 47 */     if (aMethod.getName().compareTo(ancestorMethod.getName()) != 0) return false;
/*    */     
/* 49 */     ModelElementList<Parameter> methodParameters = aMethod.getParameterList();
/* 50 */     ModelElementList<Parameter> ancestorsParameters = ancestorMethod.getParameterList();
/*    */     
/* 52 */     if (methodParameters.size() != ancestorsParameters.size()) return false;
/*    */     
/* 54 */     ArrayList paramTypes = new ArrayList();
/* 55 */     ArrayList paramTypesOfAncestor = new ArrayList();
/*    */     
/* 57 */     for (Iterator it = methodParameters.iterator(); it.hasNext(); paramTypes.add(((Parameter)it.next()).getType()));
/* 58 */     for (Iterator it = ancestorsParameters.iterator(); it.hasNext(); paramTypesOfAncestor.add(((Parameter)it.next()).getType()));
/*    */     
/* 60 */     return paramTypes.containsAll(paramTypesOfAncestor);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memori\\uses\MethodOverrides.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */