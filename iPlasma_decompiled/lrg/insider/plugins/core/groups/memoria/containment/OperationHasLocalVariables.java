/*    */ package classes.lrg.insider.plugins.core.groups.memoria.containment;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.Function;
/*    */ 
/*    */ 
/*    */ public class OperationHasLocalVariables
/*    */   extends GroupBuilder
/*    */ {
/* 12 */   public OperationHasLocalVariables() { super("local variable group", "", new String[] { "method", "global function" }); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof Function))
/* 18 */       return new ArrayList(); 
/* 19 */     return ((Function)anEntity).getBody().getLocalVarList();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memoria\containment\OperationHasLocalVariables.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */