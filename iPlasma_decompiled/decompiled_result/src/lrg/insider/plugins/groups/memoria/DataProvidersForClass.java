/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ 
/*    */ public class DataProvidersForClass
/*    */   extends GroupBuilder
/*    */ {
/* 11 */   public DataProvidersForClass() { super("foreign data providers", "", "class"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface measuredClass) {
/* 15 */     GroupEntity scopeClassOfAccesses = (GroupEntity)measuredClass.getGroup("foreign data").belongsTo("class");
/*    */     
/* 17 */     return scopeClassOfAccesses.distinct().getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\DataProvidersForClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */