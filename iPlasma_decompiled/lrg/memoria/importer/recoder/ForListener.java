/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.statement.For;
/*    */ 
/*    */ public class ForListener
/*    */   extends StripeContainerListener {
/*    */   static  {
/* 10 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.ForListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private ForListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 16 */       if (
/*    */ 
/*    */ 
/*    */         
/* 20 */         listener != null) {
/* 21 */         return listener;
/*    */       }
/* 23 */       listener = new ForListener(null); return new ForListener(null);
/*    */     }
/*    */ 
/*    */     
/* 27 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 33 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 34 */     mer.addLoop();
/* 35 */     mer.addStatements(1);
/* 36 */     mer.updateNestingLevel(1);
/*    */     
/* 38 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 39 */     CodeStripe cs = new CodeStripe(mr.getCurrentStripe());
/* 40 */     cs.setAccess(0);
/* 41 */     cs.setSignature("[for_code]");
/* 42 */     setActiveStripe(cs, mr, pe, ((For)pe).getBody(), ((For)pe).getBody());
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {
/* 46 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/*    */     
/* 48 */     restoreStripe(DefaultModelRepository.getModelRepository(null));
/* 49 */     mer.updateNestingLevel(-1);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\ForListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */