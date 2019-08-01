/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SystemHasComponents
/*    */   extends GroupBuilder
/*    */ {
/* 22 */   public SystemHasComponents() { super("component group", "", "system"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 27 */     if (!(anEntity instanceof lrg.memoria.core.System)) {
/* 28 */       return new ArrayList();
/*    */     }
/* 30 */     return (ModelElementList)anEntity.getAnnotation("allComponents");
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\SystemHasComponents.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */