/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.dude.duplication.Duplication;
/*    */ import lrg.dude.duplication.DuplicationList;
/*    */ import lrg.dude.duplication.MethodEntity;
/*    */ import lrg.insider.plugins.filters.memoria.classes.IsInterface;
/*    */ import lrg.insider.plugins.groups.memoria.MethodsWithExternalDuplication;
/*    */ 
/*    */ public class MethodsWithExternalDuplication
/*    */   extends GroupBuilder
/*    */ {
/* 21 */   public MethodsWithExternalDuplication() { super("unrelated methods with duplication", "", "method"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aMethod) {
/* 25 */     HashSet resultUniqueList = new HashSet();
/*    */ 
/*    */     
/* 28 */     ResultEntity aResult = aMethod.getProperty("#DUPLICATION#");
/* 29 */     if (aResult == null || !(aResult.getValue() instanceof DuplicationList)) return new ArrayList();
/*    */     
/* 31 */     ArrayList duplicators = getUnrelatedMethodsWithDuplication((DuplicationList)aResult.getValue());
/*    */     
/* 33 */     for (Iterator it = duplicators.iterator(); it.hasNext(); ) {
/* 34 */       MethodEntity duplicator = (MethodEntity)((Duplication)it.next()).getDuplicateCode().getEntity();
/* 35 */       resultUniqueList.add(duplicator.getMethod());
/*    */     } 
/* 37 */     return new ArrayList(resultUniqueList);
/*    */   }
/*    */ 
/*    */   
/*    */   public static ArrayList getUnrelatedMethodsWithDuplication(DuplicationList aDuplicationList) {
/* 42 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsInterface());
/* 43 */     ArrayList resultList = new ArrayList();
/*    */     
/* 45 */     if (aDuplicationList.size() == 0) return resultList;
/*    */     
/* 47 */     AbstractEntity referenceMethod = ((MethodEntity)aDuplicationList.get(0).getReferenceCode().getEntity()).getMethod();
/* 48 */     AbstractEntity referenceClass = referenceMethod.belongsTo("class");
/* 49 */     GroupEntity referenceClassAndAncestors = referenceClass.getGroup("all ancestors").applyFilter("model class").applyFilter(notComposedFilteringRule).union(referenceClass);
/*    */ 
/*    */ 
/*    */     
/* 53 */     for (int i = 0; i < aDuplicationList.size(); i++) {
/* 54 */       Duplication aDuplication = aDuplicationList.get(i);
/* 55 */       AbstractEntity duplicatorMethod = ((MethodEntity)aDuplication.getDuplicateCode().getEntity()).getMethod();
/* 56 */       AbstractEntity duplicatorClass = duplicatorMethod.belongsTo("class");
/*    */       
/* 58 */       if (duplicatorClass != referenceClass) {
/*    */         
/* 60 */         GroupEntity duplicatorClassAndAncestors = duplicatorClass.getGroup("all ancestors").applyFilter("model class").applyFilter(notComposedFilteringRule).union(duplicatorClass);
/*    */         
/* 62 */         if (referenceClassAndAncestors.intersect(duplicatorClassAndAncestors).size() == 0) resultList.add(aDuplication); 
/*    */       } 
/* 64 */     }  return resultList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\MethodsWithExternalDuplication.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */