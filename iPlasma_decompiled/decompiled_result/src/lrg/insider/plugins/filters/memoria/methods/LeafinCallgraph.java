/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ public class LeafinCallgraph
/*    */   extends FilteringRule
/*    */ {
/* 10 */   public LeafinCallgraph() { super(new Descriptor("leaf in callgraph", "method")); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public boolean applyFilter(AbstractEntityInterface aMethod) { return (aMethod.uses("operations called").applyFilter("model function").size() == 0); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\LeafinCallgraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */