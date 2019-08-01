/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ class IsDependentOnSubclass
/*    */   extends FilteringRule {
/*  9 */   public IsDependentOnSubclass() { super(new Descriptor("is dependent on subclasses", "method")); }
/*    */ 
/*    */ 
/*    */   
/* 13 */   public boolean applyFilter(AbstractEntityInterface aMethod) { return (aMethod.getGroup("subclasses dependencies").size() > 0); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\IsDependentOnSubclass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */