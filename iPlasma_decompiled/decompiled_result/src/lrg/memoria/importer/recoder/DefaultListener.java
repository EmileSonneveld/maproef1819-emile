/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.statement.Default;
/*    */ 
/*    */ public class DefaultListener
/*    */   extends StripeContainerListener {
/*    */   static  {
/* 10 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.DefaultListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private DefaultListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 16 */       if (
/*    */ 
/*    */ 
/*    */         
/* 20 */         listener != null) {
/* 21 */         return listener;
/*    */       }
/* 23 */       listener = new DefaultListener(null); return new DefaultListener(null);
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
/* 35 */     cs.setSignature("[default_case_code]");
/*    */     
/* 37 */     Default d = (Default)pe;
/* 38 */     cs.addAtomicStatements(d.getStatementCount());
/*    */     
/* 40 */     if (d.getStatementCount() > 0) {
/* 41 */       setActiveStripe(cs, mr, pe, d.getStatementAt(0), d.getStatementAt(d.getStatementCount() - 1));
/*    */     } else {
/* 43 */       cs.setAccess(3);
/* 44 */       setActiveStripe(cs, mr, pe, null, null);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 50 */   public void leaveModelComponent(ProgramElement pe) { restoreStripe(DefaultModelRepository.getModelRepository(null)); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\DefaultListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */