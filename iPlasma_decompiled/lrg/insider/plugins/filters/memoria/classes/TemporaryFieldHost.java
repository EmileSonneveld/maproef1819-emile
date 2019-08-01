/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TemporaryFieldHost
/*    */   extends FilteringRule
/*    */ {
/* 16 */   public TemporaryFieldHost() { super(new Descriptor("Temporary Field Host", "class")); }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public boolean applyFilter(AbstractEntityInterface anEntity) { return (new FilteringRule("NTempF", ">", "class", 0.0D)).applyFilter(anEntity); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\TemporaryFieldHost.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */