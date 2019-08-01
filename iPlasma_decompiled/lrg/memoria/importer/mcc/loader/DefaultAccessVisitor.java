/*    */ package lrg.memoria.importer.mcc.loader;
/*    */ 
/*    */ import lrg.memoria.core.Access;
/*    */ import lrg.memoria.core.Body;
/*    */ import lrg.memoria.core.Variable;
/*    */ 
/*    */ public class DefaultAccessVisitor
/*    */   extends DefaultVisitorRoot implements AccessVisitor {
/*    */   private int id;
/*    */   private int count;
/*    */   private Body currentBody;
/*    */   private Variable currentVariable;
/*    */   
/* 14 */   public void setId(Integer id) { this.id = id.intValue(); }
/*    */ 
/*    */   
/*    */   public void setBodyId(String bodyId) {
/* 18 */     this.currentBody = null;
/* 19 */     if (!bodyId.equals("<ERROR>"))
/* 20 */       this.currentBody = Loader.getInstance().getBody(new Integer(bodyId)); 
/* 21 */     if (this.currentBody == null) {
/* 22 */       this.currentBody = Body.getUnkonwnBody();
/*    */     }
/*    */   }
/*    */   
/*    */   public void setVarId(String varId) {
/* 27 */     this.currentVariable = null;
/* 28 */     if (!varId.equals("<ERROR>"))
/* 29 */       this.currentVariable = Loader.getInstance().getVariable(new Integer(varId)); 
/* 30 */     if (this.currentVariable == null) {
/* 31 */       this.currentVariable = Variable.getUnknownVariable();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/* 36 */   public void setCounter(Integer counter) { this.count = counter.intValue(); }
/*    */ 
/*    */   
/*    */   public void addAccess() {
/* 40 */     Access currentAccess = new Access(this.currentVariable, this.currentBody);
/* 41 */     currentAccess.setCount(this.count);
/* 42 */     this.currentVariable.addAccess(currentAccess);
/* 43 */     this.currentBody.addAccess(currentAccess);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\DefaultAccessVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */