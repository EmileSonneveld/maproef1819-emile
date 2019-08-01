/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ 
/*    */ public class ClassesWithHierarchyDuplication
/*    */   extends GroupBuilder
/*    */ {
/* 11 */   public ClassesWithHierarchyDuplication() { super("same hierarchy class duplication", "", "class"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 15 */     GroupEntity siblingClasses = (GroupEntity)anEntity.contains("method group").getGroup("same hierarchy duplicated methods").belongsTo("class");
/*    */     
/* 17 */     return siblingClasses.distinct().getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\ClassesWithHierarchyDuplication.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */