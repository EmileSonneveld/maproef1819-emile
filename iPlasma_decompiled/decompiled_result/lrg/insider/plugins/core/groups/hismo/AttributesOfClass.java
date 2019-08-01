/*    */ package classes.lrg.insider.plugins.core.groups.hismo;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.hismo.core.ClassHistory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttributesOfClass
/*    */   extends GroupBuilder
/*    */ {
/* 13 */   public AttributesOfClass() { super("attribute group", "", "class history"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof ClassHistory)) return new ArrayList(); 
/* 18 */     return ((ClassHistory)anEntity).getAttributeHistories().getHistoriesArrayList();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\hismo\AttributesOfClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */