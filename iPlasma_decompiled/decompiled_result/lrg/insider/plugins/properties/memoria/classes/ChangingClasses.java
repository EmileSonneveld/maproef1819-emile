/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupEntityBuilder;
/*    */ import lrg.insider.plugins.properties.memoria.classes.ChangingMethods;
/*    */ 
/*    */ class ChangingClasses
/*    */   extends GroupEntityBuilder
/*    */ {
/* 11 */   public ChangingClasses() { super("group of changing classes", "classes that would be potentiall affected by a change in analyzed class", "class"); }
/*    */ 
/*    */   
/*    */   public GroupEntity buildGroupEntity(AbstractEntityInterface measuredClass) {
/* 15 */     GroupEntity clientMethods = (new ChangingMethods()).buildGroupEntity(measuredClass);
/* 16 */     return (GroupEntity)clientMethods.belongsTo("class");
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\ChangingClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */