/*    */ package classes.lrg.insider.plugins.core.groups.memoria.containment;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.core.groups.memoria.containment.SystemHasPackages;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Package;
/*    */ import lrg.memoria.core.System;
/*    */ 
/*    */ class SystemHasPackages extends GroupBuilder {
/* 12 */   public SystemHasPackages() { super("package group", "", "system"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ModelElementList<Package> buildGroup(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof System)) {
/* 18 */       return new ModelElementList();
/*    */     }
/* 20 */     return ((System)anEntity).getPackages();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memoria\containment\SystemHasPackages.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */