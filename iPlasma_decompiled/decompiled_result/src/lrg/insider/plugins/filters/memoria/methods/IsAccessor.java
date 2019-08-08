/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IsAccessor
/*    */   extends FilteringRule
/*    */ {
/* 17 */   public IsAccessor() { super(new Descriptor("is accessor", "method")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 21 */     if (!(anEntity instanceof Method)) {
/* 22 */       return false;
/*    */     }
/* 24 */     if (!anEntity.getName().startsWith("get") && !anEntity.getName().startsWith("Get") && 
/* 25 */       !anEntity.getName().startsWith("set") && !anEntity.getName().startsWith("Set")) {
/* 26 */       return false;
/*    */     }
/* 28 */     if (anEntity.getGroup("operations called").size() > 0) return false; 
/* 29 */     Method aMethod = (Method)anEntity;
/*    */     
/* 31 */     if (aMethod.getAccessMode() == 4) return false; 
/* 32 */     if (aMethod.isStatic()) return false;
/*    */     
/* 34 */     if (aMethod.getBody() == null) return false;
/*    */     
/* 36 */     return (aMethod.getBody().getCyclomaticNumber() == 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\IsAccessor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */