/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.properties.memoria.methods.ATFD;
/*    */ import lrg.insider.plugins.properties.memoria.methods.AccessedModelClasses;
/*    */ import lrg.insider.plugins.properties.memoria.methods.CurrentClassAndAncestors;
/*    */ 
/*    */ public class ATFD extends PropertyComputer {
/*    */   public ATFD() {
/* 11 */     super("ATFD", "Access of foreign Data", "method", "numerical");
/* 12 */     basedOnGroup(new AccessedModelClasses());
/* 13 */     basedOnGroup(new CurrentClassAndAncestors());
/*    */   }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aMethod) {
/* 18 */     GroupEntity allAccessedClasses = getGroup("accessed model classes", aMethod);
/* 19 */     GroupEntity currentClassAndAncestors = getGroup("current class and ancestors", aMethod);
/*    */     
/* 21 */     return new ResultEntity(allAccessedClasses.exclude(currentClassAndAncestors).size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\ATFD.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */