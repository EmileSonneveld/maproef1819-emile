/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.statement.Finally;
/*    */ 
/*    */ public class FinallyListener
/*    */   extends StripeContainerListener {
/*    */   static  {
/* 10 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.FinallyListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private FinallyListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 16 */       if (
/*    */ 
/*    */ 
/*    */         
/* 20 */         listener != null) {
/* 21 */         return listener;
/*    */       }
/* 23 */       listener = new FinallyListener(null); return new FinallyListener(null);
/*    */     }
/*    */ 
/*    */     
/* 27 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 32 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 33 */     CodeStripe cs = new CodeStripe(mr.getCurrentStripe());
/* 34 */     cs.setAccess(2);
/* 35 */     cs.setSignature("[finally_code]");
/* 36 */     setActiveStripe(cs, mr, pe, ((Finally)pe).getBody(), ((Finally)pe).getBody());
/*    */   }
/*    */ 
/*    */   
/* 40 */   public void leaveModelComponent(ProgramElement pe) { restoreStripe(DefaultModelRepository.getModelRepository(null)); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\FinallyListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */