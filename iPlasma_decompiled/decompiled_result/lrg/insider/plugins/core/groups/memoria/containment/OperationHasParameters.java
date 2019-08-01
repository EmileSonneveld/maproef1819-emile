/*    */ package classes.lrg.insider.plugins.core.groups.memoria.containment;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OperationHasParameters
/*    */   extends GroupBuilder
/*    */ {
/* 17 */   public OperationHasParameters() { super("parameter group", "", new String[] { "method", "global function" }); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 21 */     if (!(anEntity instanceof Function))
/* 22 */       return new ArrayList(); 
/* 23 */     return ((Function)anEntity).getParameterList();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memoria\containment\OperationHasParameters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */