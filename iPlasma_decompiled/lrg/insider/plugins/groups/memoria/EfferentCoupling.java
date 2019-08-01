/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ 
/*    */ public class EfferentCoupling
/*    */   extends GroupBuilder
/*    */ {
/* 12 */   public EfferentCoupling() { super("efferent coupling", "", "package"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aPackage) {
/* 16 */     GroupEntity callees = (GroupEntity)aPackage.uses("operations called").distinct().belongsTo("package");
/* 17 */     GroupEntity accessed = (GroupEntity)aPackage.uses("variables accessed").distinct().belongsTo("package");
/*    */     
/* 19 */     callees.addAll(accessed);
/* 20 */     callees = callees.applyFilter("model package");
/*    */ 
/*    */     
/* 23 */     return callees.exclude((AbstractEntity)aPackage).getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\EfferentCoupling.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */