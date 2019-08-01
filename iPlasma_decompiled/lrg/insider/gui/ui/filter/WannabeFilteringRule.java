/*    */ package classes.lrg.insider.gui.ui.filter;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.insider.gui.ui.filter.WannabeFilteringRule;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WannabeFilteringRule
/*    */   extends FilteringRule
/*    */ {
/*    */   private static WannabeFilteringRule theInstance;
/*    */   
/*    */   public static WannabeFilteringRule instance() {
/* 18 */     if (theInstance == null) {
/* 19 */       theInstance = new WannabeFilteringRule();
/*    */     }
/*    */     
/* 22 */     return theInstance;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   private WannabeFilteringRule() { super(new Descriptor("<html><font color=#ff0000>wannabe filteringRule</font></html>", "?")); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public boolean applyFilter(AbstractEntityInterface anEntity) { return false; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\filter\WannabeFilteringRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */