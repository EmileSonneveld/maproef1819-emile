/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ 
/*    */ public class FanoutClassGroup
/*    */   extends GroupBuilder
/*    */ {
/* 11 */   public FanoutClassGroup() { super("fanout class group", "", new String[] { "package", "subsystem" }); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aPackageOrSubsystem) {
/* 15 */     GroupEntity ancestorClasses = aPackageOrSubsystem.uses("all ancestors");
/* 16 */     GroupEntity classesOfCalledOperations = (GroupEntity)aPackageOrSubsystem.uses("operations called").belongsTo("class");
/* 17 */     GroupEntity classesOfAccessedVariables = (GroupEntity)aPackageOrSubsystem.uses("variables accessed").belongsTo("class");
/*    */ 
/*    */     
/* 20 */     GroupEntity usedClasses = ancestorClasses.union(classesOfCalledOperations).union(classesOfAccessedVariables).distinct().applyFilter("model class");
/*    */     
/* 22 */     return usedClasses.exclude(aPackageOrSubsystem.getGroup("model classes in package")).getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\FanoutClassGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */