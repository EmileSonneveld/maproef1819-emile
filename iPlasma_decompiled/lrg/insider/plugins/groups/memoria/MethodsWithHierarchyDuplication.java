/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.dude.duplication.Duplication;
/*    */ import lrg.dude.duplication.DuplicationList;
/*    */ import lrg.dude.duplication.MethodEntity;
/*    */ import lrg.insider.plugins.groups.memoria.MethodsWithHierarchyDuplication;
/*    */ 
/*    */ public class MethodsWithHierarchyDuplication
/*    */   extends GroupBuilder {
/* 18 */   public MethodsWithHierarchyDuplication() { super("same hierarchy duplicated methods", "", "method"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aMethod) {
/* 22 */     HashSet resultUniqueList = new HashSet();
/*    */ 
/*    */     
/* 25 */     ResultEntity aResult = aMethod.getProperty("#DUPLICATION#");
/* 26 */     if (aResult == null || !(aResult.getValue() instanceof DuplicationList)) return new ArrayList();
/*    */     
/* 28 */     ArrayList duplicators = getMethodsWithHierarchyDuplication((DuplicationList)aResult.getValue());
/*    */     
/* 30 */     for (Iterator it = duplicators.iterator(); it.hasNext(); ) {
/* 31 */       MethodEntity duplicator = (MethodEntity)((Duplication)it.next()).getDuplicateCode().getEntity();
/* 32 */       resultUniqueList.add(duplicator.getMethod());
/*    */     } 
/* 34 */     return new ArrayList(resultUniqueList);
/*    */   }
/*    */ 
/*    */   
/*    */   public static ArrayList getMethodsWithHierarchyDuplication(DuplicationList aDuplicationList) {
/* 39 */     ArrayList resultList = new ArrayList();
/*    */     
/* 41 */     if (aDuplicationList.size() == 0) return resultList;
/*    */     
/* 43 */     AbstractEntity referenceMethod = ((MethodEntity)aDuplicationList.get(0).getReferenceCode().getEntity()).getMethod();
/* 44 */     AbstractEntity referenceClass = referenceMethod.belongsTo("class");
/* 45 */     GroupEntity referenceClassAndAncestors = referenceClass.getGroup("all ancestors").applyFilter("model class").union(referenceClass);
/*    */ 
/*    */ 
/*    */     
/* 49 */     for (int i = 0; i < aDuplicationList.size(); i++) {
/* 50 */       Duplication aDuplication = aDuplicationList.get(i);
/* 51 */       AbstractEntity duplicatorMethod = ((MethodEntity)aDuplication.getDuplicateCode().getEntity()).getMethod();
/* 52 */       AbstractEntity duplicatorClass = duplicatorMethod.belongsTo("class");
/*    */       
/* 54 */       if (duplicatorClass != referenceClass) {
/*    */         
/* 56 */         GroupEntity duplicatorClassAndAncestors = duplicatorClass.getGroup("all ancestors").applyFilter("model class").union(duplicatorClass);
/*    */         
/* 58 */         if (referenceClassAndAncestors.intersect(duplicatorClassAndAncestors).size() > 0) resultList.add(aDuplication); 
/*    */       } 
/* 60 */     }  return resultList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\MethodsWithHierarchyDuplication.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */