/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ public class IsInner
/*    */   extends FilteringRule {
/*  9 */   public IsInner() { super(new Descriptor("is inner", "class")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 13 */     if (!(anEntity instanceof lrg.memoria.core.Class)) return false;
/*    */     
/* 15 */     String address = (String)anEntity.getProperty("Address").getValue();
/* 16 */     return !(!address.contains("$") && anEntity.getName().length() > 2);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\IsInner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */