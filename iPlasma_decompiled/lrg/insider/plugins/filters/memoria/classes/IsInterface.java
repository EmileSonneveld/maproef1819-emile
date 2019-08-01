/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IsInterface
/*    */   extends FilteringRule
/*    */ {
/* 17 */   public IsInterface() { super(new Descriptor("is interface", "class")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 21 */     if (!(anEntity instanceof lrg.memoria.core.Class)) return false;
/*    */     
/* 23 */     return ((DataAbstraction)anEntity).isInterface();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\IsInterface.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */