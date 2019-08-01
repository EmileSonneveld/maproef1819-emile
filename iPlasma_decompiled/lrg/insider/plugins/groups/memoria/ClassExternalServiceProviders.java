/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
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
/*    */ public class ClassExternalServiceProviders
/*    */   extends GroupBuilder
/*    */ {
/* 19 */   public ClassExternalServiceProviders() { super("external service providers for class", "", "class"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aClass) {
/* 23 */     GroupEntity callees = (GroupEntity)aClass.uses("operations called").distinct().belongsTo("class");
/* 24 */     GroupEntity accessed = (GroupEntity)aClass.uses("variables accessed").distinct().belongsTo("class");
/*    */     
/* 26 */     callees.addAll(accessed);
/* 27 */     callees = callees.applyFilter("model class");
/*    */     
/* 29 */     GroupEntity relatedClasses = aClass.getGroup("all ancestors").union((AbstractEntity)aClass);
/*    */     
/* 31 */     return callees.exclude(relatedClasses).getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\ClassExternalServiceProviders.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */