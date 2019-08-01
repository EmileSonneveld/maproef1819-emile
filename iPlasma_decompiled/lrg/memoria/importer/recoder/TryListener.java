/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.statement.Try;
/*    */ 
/*    */ public class TryListener
/*    */   extends StripeContainerListener {
/*    */   static  {
/* 10 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.TryListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private TryListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 16 */       if (
/*    */ 
/*    */ 
/*    */         
/* 20 */         listener != null) {
/* 21 */         return listener;
/*    */       }
/* 23 */       listener = new TryListener(null); return new TryListener(null);
/*    */     }
/*    */ 
/*    */     
/* 27 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 32 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 33 */     mer.updateNestingLevel(1);
/*    */     
/* 35 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 36 */     CodeStripe cs = new CodeStripe(mr.getCurrentStripe());
/* 37 */     cs.setAccess(0);
/* 38 */     cs.setSignature("[try_code]");
/* 39 */     setActiveStripe(cs, mr, pe, ((Try)pe).getStatementAt(0), (
/* 40 */         (Try)pe).getStatementAt(((Try)pe).getStatementCount() - 1));
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {
/* 44 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 45 */     mer.updateNestingLevel(-1);
/* 46 */     restoreStripe(DefaultModelRepository.getModelRepository(null));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\TryListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */