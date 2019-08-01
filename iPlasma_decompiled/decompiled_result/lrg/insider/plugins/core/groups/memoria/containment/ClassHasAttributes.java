/*    */ package classes.lrg.insider.plugins.core.groups.memoria.containment;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassHasAttributes
/*    */   extends GroupBuilder
/*    */ {
/* 13 */   public ClassHasAttributes() { super("attribute group", "", "class"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 18 */     if (!(anEntity instanceof lrg.memoria.core.Class))
/* 19 */       return new ArrayList(); 
/* 20 */     return ((DataAbstraction)anEntity).getAttributeList();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memoria\containment\ClassHasAttributes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */