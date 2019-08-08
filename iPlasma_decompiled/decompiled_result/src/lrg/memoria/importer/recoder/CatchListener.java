/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.statement.Catch;
/*    */ 
/*    */ public class CatchListener
/*    */   extends StripeContainerListener {
/*    */   static  {
/* 10 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.CatchListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private CatchListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 16 */       if (
/*    */ 
/*    */ 
/*    */         
/* 20 */         listener != null) {
/* 21 */         return listener;
/*    */       }
/* 23 */       listener = new CatchListener(null); return new CatchListener(null);
/*    */     }
/*    */ 
/*    */     
/* 27 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 32 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 33 */     mer.addException();
/* 34 */     mer.updateNestingLevel(1);
/*    */     
/* 36 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 37 */     CodeStripe cs = new CodeStripe(mr.getCurrentStripe());
/* 38 */     cs.setAccess(2);
/* 39 */     cs.setSignature("[catch_code]");
/* 40 */     setActiveStripe(cs, mr, pe, ((Catch)pe).getBody(), ((Catch)pe).getBody());
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {
/* 44 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 45 */     mer.updateNestingLevel(-1);
/* 46 */     restoreStripe(DefaultModelRepository.getModelRepository(null));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\CatchListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */