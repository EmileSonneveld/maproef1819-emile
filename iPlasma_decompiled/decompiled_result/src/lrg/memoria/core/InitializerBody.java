/*    */ package lrg.memoria.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InitializerBody
/*    */   extends Body
/*    */ {
/*    */   private boolean isStatic = false;
/*    */   
/* 19 */   public InitializerBody(DataAbstraction scope) { super(scope); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public void setStatic() { this.isStatic = true; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public boolean isStatic() { return this.isStatic; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public DataAbstraction getScope() { return (DataAbstraction)super.getScope(); }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void accept(ModelVisitor v) { v.visitInitializerBody(this); }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     StringBuffer myStr = new StringBuffer("\n\t\t\t InitializerBody: ");
/*    */     
/* 47 */     myStr.append("\n\t\t\t\t - flags: ");
/* 48 */     if (isStatic()) myStr.append("static."); 
/* 49 */     myStr.append(super.toString());
/* 50 */     return new String(myStr);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\InitializerBody.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */