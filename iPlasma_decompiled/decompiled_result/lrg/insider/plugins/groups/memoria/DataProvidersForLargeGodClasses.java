/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataProvidersForLargeGodClasses
/*    */   extends GroupBuilder
/*    */ {
/* 18 */   public DataProvidersForLargeGodClasses() { super("Data Providers For Large-God Classes", "", "system"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aSystem) {
/* 22 */     GroupEntity allClasses = aSystem.getGroup("class group").applyFilter("model class");
/* 23 */     GroupEntity largeGodClasses = allClasses.applyFilter("God Class");
/*    */     
/* 25 */     largeGodClasses = largeGodClasses.union(allClasses.applyFilter("Large Classes"));
/*    */     
/* 27 */     return largeGodClasses.getGroup("foreign data providers").distinct().getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\DataProvidersForLargeGodClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */