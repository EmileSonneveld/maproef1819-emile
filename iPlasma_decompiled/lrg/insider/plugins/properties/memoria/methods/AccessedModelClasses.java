/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.properties.memoria.methods.AccessedModelClasses;
/*    */ import lrg.insider.plugins.properties.memoria.methods.AccessedModelData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AccessedModelClasses
/*    */   extends AccessedModelData
/*    */ {
/* 15 */   public AccessedModelClasses() { super("accessed model classes", "model classes accessed by using attributes or calling accessor methods"); }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public GroupEntity buildGroupEntity(AbstractEntityInterface aMethod) { return ((GroupEntity)super.buildGroupEntity(aMethod)
/* 20 */       .belongsTo("class")).applyFilter("model class"); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\AccessedModelClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */