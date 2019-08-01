/*    */ package classes.lrg.insider.plugins.core.groups.memoria.containment;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ComponentContainsClasses
/*    */   extends GroupBuilder
/*    */ {
/* 19 */   public ComponentContainsClasses() { super("class group", "", "component"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 24 */     if (!(anEntity instanceof Component)) {
/* 25 */       return new ArrayList();
/*    */     }
/* 27 */     return ((Component)anEntity).getScopedElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memoria\containment\ComponentContainsClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */