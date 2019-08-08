/*    */ package classes.lrg.insider.plugins.groups.membrain;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.groups.membrain.AppliesTo;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Parameter;
/*    */ 
/*    */ public class AppliesTo extends GroupBuilder {
/* 15 */   public AppliesTo() { super("applies-to group", "The applies-to group for the method", "method"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/*    */     try {
/* 20 */       Method theMethod = (Method)anEntity;
/* 21 */       if (theMethod.isAbstract()) throw new Exception("Not defined for abstract methods");
/*    */       
/* 23 */       ArrayList<Class> theResult = new ArrayList<Class>();
/* 24 */       LinkedList<Class> workingList = new LinkedList<Class>();
/*    */       
/* 26 */       theResult.add((Class)theMethod.belongsTo("class"));
/* 27 */       workingList.addAll(theMethod.belongsTo("class").getGroup("derived classes").applyFilter("model class").getElements());
/* 28 */       while (workingList.size() != 0) {
/* 29 */         Class tested = (Class)workingList.removeFirst();
/* 30 */         Iterator it = tested.getMethodList().iterator();
/* 31 */         boolean found = false;
/* 32 */         while (it.hasNext()) {
/* 33 */           if (itOverrides((Method)it.next(), theMethod)) {
/* 34 */             found = true;
/*    */             break;
/*    */           } 
/*    */         } 
/* 38 */         if (!found && !theResult.contains(tested)) {
/* 39 */           theResult.add(tested);
/* 40 */           workingList.addAll(tested.getGroup("derived classes").applyFilter("model class").getElements());
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


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\AppliesTo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */