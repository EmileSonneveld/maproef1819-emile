/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.properties.memoria.methods.ALD;
/*    */ import lrg.insider.plugins.properties.memoria.methods.AccessedModelClasses;
/*    */ import lrg.insider.plugins.properties.memoria.methods.CurrentClassAndAncestors;
/*    */ 
/*    */ 
/*    */ public class ALD
/*    */   extends PropertyComputer
/*    */ {
/*    */   public ALD() {
/* 16 */     super("ALD", "Access of Local Data", "method", "numerical");
/* 17 */     basedOnGroup(new AccessedModelClasses());
/* 18 */     basedOnGroup(new CurrentClassAndAncestors());
/*    */   }
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aMethod) {
/* 22 */     GroupEntity allAccessedClasses = getGroup("accessed model classes", aMethod);
/* 23 */     GroupEntity currentClassAndAncestors = getGroup("current class and ancestors", aMethod);
/*    */     
/* 25 */     return new ResultEntity(allAccessedClasses.intersect(currentClassAndAncestors).size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\ALD.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */