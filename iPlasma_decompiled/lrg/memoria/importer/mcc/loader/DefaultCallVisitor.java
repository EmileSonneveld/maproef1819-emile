/*    */ package lrg.memoria.importer.mcc.loader;
/*    */ 
/*    */ import lrg.memoria.core.Body;
/*    */ import lrg.memoria.core.Call;
/*    */ import lrg.memoria.core.Function;
/*    */ 
/*    */ public class DefaultCallVisitor
/*    */   extends DefaultVisitorRoot implements CallVisitor {
/*    */   private int callId;
/*    */   private Body scope;
/*    */   private int count;
/*    */   private Function calledFunction;
/*    */   
/* 14 */   public void setId(Integer id) { this.callId = id.intValue(); }
/*    */ 
/*    */   
/*    */   public void setBodyId(String bodyId) {
/* 18 */     this.scope = null;
/* 19 */     if (!bodyId.equals("<ERROR>"))
/* 20 */       this.scope = Loader.getInstance().getBody(new Integer(bodyId)); 
/* 21 */     if (this.scope == null) {
/* 22 */       this.scope = Body.getUnkonwnBody();
/*    */     }
/*    */   }
/*    */   
/*    */   public void setFuncId(String functId) {
/* 27 */     this.calledFunction = null;
/* 28 */     if (!functId.equals("<ERROR>"))
/* 29 */       this.calledFunction = Loader.getInstance().getFunction(new Integer(functId)); 
/* 30 */     if (this.calledFunction == null) {
/* 31 */       this.calledFunction = Function.getUnknownFunction();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/* 36 */   public void setCounter(Integer counter) { this.count = counter.intValue(); }
/*    */ 
/*    */   
/*    */   public void addCall() {
/* 40 */     Call currentCall = new Call(this.calledFunction, this.scope);
/* 41 */     currentCall.setCount(this.count);
/* 42 */     this.scope.addCall(currentCall);
/* 43 */     this.calledFunction.addCall(currentCall);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\DefaultCallVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */