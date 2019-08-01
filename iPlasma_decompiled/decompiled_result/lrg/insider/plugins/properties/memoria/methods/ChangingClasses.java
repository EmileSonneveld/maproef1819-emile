/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupEntityBuilder;
/*    */ import lrg.insider.plugins.properties.memoria.methods.ChangingMethods;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChangingClasses
/*    */   extends GroupEntityBuilder
/*    */ {
/* 19 */   public ChangingClasses() { super("group of changing classes", "classes that would be potentiall affected by a change in analyzed class", "method"); }
/*    */ 
/*    */   
/*    */   public GroupEntity buildGroupEntity(AbstractEntityInterface measuredClass) {
/* 23 */     GroupEntity clientMethods = (new ChangingMethods()).buildGroupEntity(measuredClass);
/* 24 */     return (GroupEntity)clientMethods.belongsTo("class");
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\ChangingClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */