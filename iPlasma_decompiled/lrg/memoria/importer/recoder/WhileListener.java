/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.statement.While;
/*    */ 
/*    */ public class WhileListener
/*    */   extends StripeContainerListener {
/*    */   static  {
/* 10 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.WhileListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private WhileListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 16 */       if (
/*    */ 
/*    */ 
/*    */         
/* 20 */         listener != null) {
/* 21 */         return listener;
/*    */       }
/* 23 */       listener = new WhileListener(null); return new WhileListener(null);
/*    */     }
/*    */ 
/*    */     
/* 27 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 32 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 33 */     mer.addLoop();
/* 34 */     mer.addStatements(1);
/* 35 */     mer.updateNestingLevel(1);
/*    */     
/* 37 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 38 */     CodeStripe cs = new CodeStripe(mr.getCurrentStripe());
/* 39 */     cs.setAccess(0);
/* 40 */     cs.setSignature("[while_code]");
/* 41 */     setActiveStripe(cs, mr, pe, ((While)pe).getBody(), ((While)pe).getBody());
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {
/* 45 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/*    */     
/* 47 */     restoreStripe(DefaultModelRepository.getModelRepository(null));
/* 48 */     mer.updateNestingLevel(-1);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\WhileListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */