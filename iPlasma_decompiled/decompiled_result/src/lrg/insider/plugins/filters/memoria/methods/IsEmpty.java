/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.FunctionBody;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IsEmpty
/*    */   extends FilteringRule
/*    */ {
/* 18 */   public IsEmpty() { super(new Descriptor("is empty", "method")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 22 */     if (!(anEntity instanceof Method)) return false;
/*    */     
/* 24 */     FunctionBody functionBody = ((Method)anEntity).getBody();
/* 25 */     if (functionBody == null) return false;
/*    */     
/* 27 */     String theCode = functionBody.getSourceCode();
/* 28 */     if (theCode == null) return false;
/*    */     
/* 30 */     int startIndex = theCode.indexOf("{") + 1;
/* 31 */     int stopIndex = theCode.indexOf("}") - 1;
/*    */     
/* 33 */     if (stopIndex - startIndex <= 0) return true;
/*    */     
/* 35 */     String realCode = theCode.substring(startIndex, stopIndex);
/*    */     
/* 37 */     return (realCode.trim().length() == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\IsEmpty.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */