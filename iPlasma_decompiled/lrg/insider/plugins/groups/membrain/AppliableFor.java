/*    */ package classes.lrg.insider.plugins.groups.membrain;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Parameter;
/*    */ 
/*    */ public class AppliableFor
/*    */   extends GroupBuilder {
/* 15 */   public AppliableFor() { super("appliable-for group", "The appliable-for group for the class", "class"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/*    */     try {
/* 20 */       DataAbstraction theClass = (DataAbstraction)anEntity;
/* 21 */       ArrayList<Method> theResult = new ArrayList<Method>();
/* 22 */       GroupEntity allMethods = theClass.getGroup("all ancestors").getGroup("method group").applyFilter("model class");
/* 23 */       allMethods.addAll(theClass.getGroup("method group"));
/* 24 */       Iterator it = allMethods.iterator();
/* 25 */       while (it.hasNext()) {
/* 26 */         Method tested = (Method)it.next();
/* 27 */         if (!((Boolean)tested.getProperty("is abstract").getValue()).booleanValue() && 
/* 28 */           tested.getGroup("applies-to group").isInGroup(theClass) && !theResult.contains(tested)) {
/* 29 */           theResult.add(tested);
/*    */         }
/*    */       } 
/*    */       
/* 33 */       return theResult;
/* 34 */     } catch (Exception e) {
/* 35 */       return new ArrayList();
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean itOverrides(Method aMethod, Method ancestorMethod) {
/* 40 */     if (aMethod.getName().compareTo(ancestorMethod.getName()) != 0) return false; 
/* 41 */     ModelElementList<Parameter> methodParameters = aMethod.getParameterList();
/* 42 */     ModelElementList<Parameter> ancestorsParameters = ancestorMethod.getParameterList();
/* 43 */     if (methodParameters.size() != ancestorsParameters.size()) return false; 
/* 44 */     ArrayList paramTypes = new ArrayList();
/* 45 */     ArrayList paramTypesOfAncestor = new ArrayList();
/* 46 */     for (Iterator it = methodParameters.iterator(); it.hasNext(); paramTypes.add(((Parameter)it.next()).getType()));
/* 47 */     for (Iterator it = ancestorsParameters.iterator(); it.hasNext(); paramTypesOfAncestor.add(((Parameter)it.next()).getType()));
/* 48 */     return paramTypes.containsAll(paramTypesOfAncestor);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\AppliableFor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */