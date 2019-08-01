/*    */ package classes.lrg.insider.plugins.properties.memoria.packages;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupEntityBuilder;
/*    */ 
/*    */ class ModelClassesInPackage
/*    */   extends GroupEntityBuilder
/*    */ {
/* 10 */   public ModelClassesInPackage() { super("model classes in package", "model classes in package", "package"); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public GroupEntity buildGroupEntity(AbstractEntityInterface aPackage) { return aPackage.contains("class group").applyFilter("model class"); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\packages\ModelClassesInPackage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */