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
/*    */ 
/*    */ public class SchizofrenicClass
/*    */   extends FilteringRule
/*    */ {
/* 17 */   public SchizofrenicClass() { super(new Descriptor("Schizofrenic Class", "class")); }
/*    */ 
/*    */ 
/*    */   
/* 21 */   public boolean applyFilter(AbstractEntityInterface anEntity) { return (new FilteringRule("SCHIZO", ">=", "class", 2.0D)).applyFilter(anEntity); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\SchizofrenicClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */