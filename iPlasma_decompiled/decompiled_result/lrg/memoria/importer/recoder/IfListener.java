/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.statement.If;
/*    */ 
/*    */ public class IfListener
/*    */   extends StripeContainerListener {
/*    */   static  {
/* 10 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.IfListener", new Factory());
/*    */   }
/*    */   
/*    */   private static Listener listener;
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 16 */       if (
/*    */ 
/*    */ 
/*    */         
/* 20 */         listener != null) {
/* 21 */         return listener;
/*    */       }
/* 23 */       listener = new IfListener(); return new IfListener();
/*    */     }
/*    */ 
/*    */     
/* 27 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 32 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 33 */     mer.addDecision();
/* 34 */     mer.addStatements(1);
/* 35 */     mer.updateNestingLevel(1);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     if (!(pe instanceof If)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 46 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*    */     
/* 48 */     CodeStripe cs = new CodeStripe(mr.getCurrentStripe());
/* 49 */     cs.setAccess(0);
/* 50 */     cs.setSignature("[if_code]");
/* 51 */     if (((If)pe).getElse() != null) {
/* 52 */       setActiveStripe(cs, mr, pe, ((If)pe).getThen().getBody(), ((If)pe).getElse().getBody());
/*    */     } else {
/* 54 */       setActiveStripe(cs, mr, pe, ((If)pe).getThen().getBody(), ((If)pe).getThen().getBody());
/*    */     } 
/*    */   }
/*    */   public void leaveModelComponent(ProgramElement pe) {
/* 58 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 59 */     mer.updateNestingLevel(-1);
/*    */     
/* 61 */     if (!(pe instanceof If))
/*    */       return; 
/* 63 */     restoreStripe(DefaultModelRepository.getModelRepository(null));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\IfListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */