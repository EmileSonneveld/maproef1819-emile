/*    */ package classes.lrg.insider.plugins.core.groups.memoria.uses;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.Variable;
/*    */ 
/*    */ public class VariableHasType
/*    */   extends GroupBuilder
/*    */ {
/* 11 */   public VariableHasType() { super("type of variable", "", new String[] { "global variable", "attribute", "local variable", "parameter" }); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aVariable) {
/* 15 */     ArrayList resultList = new ArrayList();
/* 16 */     if (!(aVariable instanceof Variable)) return resultList; 
/* 17 */     resultList.add(((Variable)aVariable).getType());
/*    */     
/* 19 */     return resultList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memori\\uses\VariableHasType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */