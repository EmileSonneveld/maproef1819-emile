/*    */ package classes.lrg.insider.plugins.filters.membrain.calls;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ 
/*    */ public class IsNormalMethodDynamicDispatched
/*    */   extends FilteringRule
/*    */ {
/* 11 */   public IsNormalMethodDynamicDispatched() { super(new Descriptor("Dynamic Dispached Calls", "membrain call")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 15 */     if (anEntity instanceof lrg.membrain.representation.instructionSet.Abstractions.MCall) {
/* 16 */       if (anEntity.toString().indexOf("VirtualCall") == 0 || anEntity.toString().indexOf("InterfaceCall") == 0) {
/* 17 */         return true;
/*    */       }
/* 19 */       return false;
/*    */     } 
/*    */     
/* 22 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\membrain\calls\IsNormalMethodDynamicDispatched.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */