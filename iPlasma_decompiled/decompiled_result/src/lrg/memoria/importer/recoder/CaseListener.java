/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.statement.Case;
/*    */ 
/*    */ public class CaseListener
/*    */   extends StripeContainerListener {
/*    */   static  {
/* 10 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.CaseListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private CaseListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 16 */       if (
/*    */ 
/*    */ 
/*    */         
/* 20 */         listener != null) {
/* 21 */         return listener;
/*    */       }
/* 23 */       listener = new CaseListener(null); return new CaseListener(null);
/*    */     }
/*    */ 
/*    */     
/* 27 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 32 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 33 */     Case currentCase = (Case)pe;
/* 34 */     if (!currentCase.getBody().isEmpty()) {
/* 35 */       mer.updateNestingLevel(1);
/* 36 */       mer.addDecision();
/*    */     } 
/*    */     
/* 39 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 40 */     CodeStripe cs = new CodeStripe(mr.getCurrentStripe());
/* 41 */     cs.setAccess(2);
/* 42 */     cs.setSignature("[case_code]");
/* 43 */     cs.addAtomicStatements(currentCase.getStatementCount());
/* 44 */     Case cas = (Case)pe;
/* 45 */     if (cas.getStatementCount() > 0) {
/* 46 */       setActiveStripe(cs, mr, pe, cas.getStatementAt(0), cas.getStatementAt(cas.getStatementCount() - 1));
/*    */     } else {
/* 48 */       cs.setAccess(3);
/* 49 */       setActiveStripe(cs, mr, pe, null, null);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {
/* 54 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 55 */     mer.updateNestingLevel(-1);
/*    */     
/* 57 */     restoreStripe(DefaultModelRepository.getModelRepository(null));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\CaseListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */