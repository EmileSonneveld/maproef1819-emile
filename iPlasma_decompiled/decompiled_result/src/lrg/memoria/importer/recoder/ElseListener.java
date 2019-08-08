/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.statement.Else;
/*    */ 
/*    */ public class ElseListener
/*    */   extends StripeContainerListener {
/*    */   static  {
/* 10 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.ElseListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private ElseListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 16 */       if (
/*    */ 
/*    */ 
/*    */         
/* 20 */         listener != null) {
/* 21 */         return listener;
/*    */       }
/* 23 */       listener = new ElseListener(null); return new ElseListener(null);
/*    */     }
/*    */ 
/*    */     
/* 27 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 32 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 33 */     mer.addStatements(1);
/*    */     
/* 35 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 36 */     CodeStripe cs = new CodeStripe(mr.getCurrentStripe());
/* 37 */     cs.setAccess(2);
/* 38 */     cs.setSignature("[else_code]");
/* 39 */     setActiveStripe(cs, mr, pe, ((Else)pe).getBody(), ((Else)pe).getBody());
/*    */   }
/*    */ 
/*    */   
/* 43 */   public void leaveModelComponent(ProgramElement pe) { restoreStripe(DefaultModelRepository.getModelRepository(null)); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\ElseListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */