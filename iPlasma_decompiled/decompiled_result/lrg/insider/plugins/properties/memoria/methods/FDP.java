/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.properties.memoria.methods.AccessedModelClasses;
/*    */ import lrg.insider.plugins.properties.memoria.methods.FDP;
/*    */ 
/*    */ public class FDP extends PropertyComputer {
/*    */   public FDP() {
/* 10 */     super("FDP", "Number of Import Classes", "method", "numerical");
/* 11 */     basedOnGroup(new AccessedModelClasses());
/* 12 */     basedOnGroup(new CurrentClassAndAncestors());
/*    */   }
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aMethod) {
/* 16 */     GroupEntity allAccessedClasses = getGroup("accessed model classes", aMethod);
/* 17 */     GroupEntity currentClassAndAncestors = getGroup("current class and ancestors", aMethod);
/*    */     
/* 19 */     return new ResultEntity(allAccessedClasses.exclude(currentClassAndAncestors).distinct().size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\FDP.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */