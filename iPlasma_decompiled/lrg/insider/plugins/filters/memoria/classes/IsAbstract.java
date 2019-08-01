/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.Class;
/*    */ 
/*    */ public class IsAbstract extends FilteringRule {
/*  9 */   public IsAbstract() { super(new Descriptor("is abstract", "class")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 13 */     if (!(anEntity instanceof Class)) return false;
/*    */     
/* 15 */     return ((Class)anEntity).isAbstract();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\IsAbstract.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */