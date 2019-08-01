/*    */ package classes.lrg.insider.plugins.groups.membrain;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.groups.membrain.DirectOverrides;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Parameter;
/*    */ 
/*    */ public class DirectOverrides extends GroupBuilder {
/* 15 */   public DirectOverrides() { super("direct overriden methods", "The group of direct overriden methods", "method"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/*    */     try {
/* 20 */       Method theMethod = (Method)anEntity;
/* 21 */       if (theMethod.isAbstract()) throw new Exception("Not defined for abstract methods"); 
/* 22 */       ArrayList<Method> theResult = new ArrayList<Method>();
/* 23 */       LinkedList<Class> workingList = new LinkedList<Class>();
/* 24 */       workingList.addAll(theMethod.belongsTo("class").getGroup("base classes").applyFilter("model class").getElements());
/* 25 */       while (workingList.size() != 0) {
/* 26 */         DataAbstraction tmp = (DataAbstraction)workingList.removeFirst();
/* 27 */         Iterator it = tmp.getMethodList().iterator();
/* 28 */         boolean found = false;
/* 29 */         while (it.hasNext()) {
/* 30 */           Method tested = (Method)it.next();
/* 31 */           if (itOverrides(theMethod, tested)) {
/* 32 */             if (!theResult.contains(tested)) {
/* 33 */               theResult.add(tested);
/*    */             }
/* 35 */             found = true;
/*    */             break;
/*    */           } 
/*    */         } 
/* 39 */         if (!found) {
/* 40 */           workingList.addAll(tmp.getGroup("base classes").applyFilter("model class").getElements());
/*    */         }
/*    */       } 
/* 43 */       return theResult;
/* 44 */     } catch (Exception e) {
/* 45 */       return new ArrayList();
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean itOverrides(Method aMethod, Method ancestorMethod) {
/* 50 */     if (aMethod.getName().compareTo(ancestorMethod.getName()) != 0) return false; 
/* 51 */     ModelElementList<Parameter> methodParameters = aMethod.getParameterList();
/* 52 */     ModelElementList<Parameter> ancestorsParameters = ancestorMethod.getParameterList();
/* 53 */     if (methodParameters.size() != ancestorsParameters.size()) return false; 
/* 54 */     ArrayList paramTypes = new ArrayList();
/* 55 */     ArrayList paramTypesOfAncestor = new ArrayList();
/* 56 */     for (Iterator it = methodParameters.iterator(); it.hasNext(); paramTypes.add(((Parameter)it.next()).getType()));
/* 57 */     for (Iterator it = ancestorsParameters.iterator(); it.hasNext(); paramTypesOfAncestor.add(((Parameter)it.next()).getType()));
/* 58 */     return paramTypes.containsAll(paramTypesOfAncestor);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\DirectOverrides.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */