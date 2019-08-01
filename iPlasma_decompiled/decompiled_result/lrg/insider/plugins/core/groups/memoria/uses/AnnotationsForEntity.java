/*    */ package classes.lrg.insider.plugins.core.groups.memoria.uses;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.NamedModelElement;
/*    */ 
/*    */ 
/*    */ public class AnnotationsForEntity
/*    */   extends GroupBuilder
/*    */ {
/* 13 */   public AnnotationsForEntity() { super("annotation instances (for all entities)", "", new String[] { "method", "class", "attribute", "local variable", "parameter" }); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 18 */     ArrayList theAnnotations = new ArrayList();
/*    */     
/* 20 */     theAnnotations.addAll(((NamedModelElement)anEntity).getAnnotations());
/* 21 */     if (anEntity instanceof lrg.memoria.core.Class) {
/* 22 */       GroupEntity members = anEntity.contains("method group").union(anEntity.contains("attribute group"));
/* 23 */       theAnnotations.addAll(members.getGroup("annotation instances (for all entities)").getElements());
/*    */     } 
/*    */     
/* 26 */     if (anEntity instanceof lrg.memoria.core.Method) {
/* 27 */       GroupEntity members = anEntity.contains("parameter group").union(anEntity.contains("local variable group"));
/* 28 */       theAnnotations.addAll(members.getGroup("annotation instances (for all entities)").getElements());
/*    */     } 
/*    */     
/* 31 */     return theAnnotations;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memori\\uses\AnnotationsForEntity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */