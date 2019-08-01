/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
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
/*    */ public class ChangingMethods
/*    */   extends GroupEntityBuilder
/*    */ {
/* 21 */   public ChangingMethods() { super("group of changing methods", "methods that would be potentiall affected by a change in analyzed method", "method"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public GroupEntity buildGroupEntity(AbstractEntityInterface measuredMethod) {
/* 26 */     GroupEntity clientMethods = measuredMethod.isUsed("operations calling me");
/* 27 */     AbstractEntity measuredClass = measuredMethod.belongsTo("class");
/*    */     
/* 29 */     GroupEntity relatedMethods = measuredClass.contains("method group");
/* 30 */     relatedMethods = relatedMethods.union(measuredClass.getGroup("all ancestors").getGroup("method group"));
/* 31 */     relatedMethods = relatedMethods.union(measuredClass.getGroup("all descendants").getGroup("method group"));
/* 32 */     return clientMethods.exclude(relatedMethods);
/*    */   }
/*    */ 
/*    */   
/* 36 */   public ArrayList buildGroup(AbstractEntityInterface measuredClass) { return buildGroupEntity(measuredClass).getElements(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\ChangingMethods.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */