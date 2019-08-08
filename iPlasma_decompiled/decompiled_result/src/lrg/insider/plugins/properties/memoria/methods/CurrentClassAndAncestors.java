/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupEntityBuilder;
/*    */ 
/*    */ public class CurrentClassAndAncestors
/*    */   extends GroupEntityBuilder {
/*  9 */   public CurrentClassAndAncestors() { super("current class and ancestors", "a class and its ancestors", "method"); }
/*    */ 
/*    */ 
/*    */   
/* 13 */   public GroupEntity buildGroupEntity(AbstractEntityInterface aMethod) { return aMethod.belongsTo("class").uses("all ancestors").union(aMethod.belongsTo("class")); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\CurrentClassAndAncestors.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */