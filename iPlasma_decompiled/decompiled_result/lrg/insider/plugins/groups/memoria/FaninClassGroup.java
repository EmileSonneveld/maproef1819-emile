/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ 
/*    */ public class FaninClassGroup
/*    */   extends GroupBuilder
/*    */ {
/* 11 */   public FaninClassGroup() { super("fanin class group", "", new String[] { "package", "subsystem" }); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aPackageOrSubsystem) {
/* 16 */     GroupEntity descendantClasses = aPackageOrSubsystem.isUsed("all descendants");
/* 17 */     GroupEntity classesOfOperationsCallers = (GroupEntity)aPackageOrSubsystem.uses("operations calling me").belongsTo("class");
/* 18 */     GroupEntity classesOfVariableAccessors = (GroupEntity)aPackageOrSubsystem.uses("methods accessing variable").belongsTo("class");
/*    */     
/* 20 */     GroupEntity userClasses = descendantClasses.union(classesOfOperationsCallers).union(classesOfVariableAccessors).distinct().applyFilter("model class");
/*    */     
/* 22 */     return userClasses.exclude(aPackageOrSubsystem.contains("class group")).getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\FaninClassGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */