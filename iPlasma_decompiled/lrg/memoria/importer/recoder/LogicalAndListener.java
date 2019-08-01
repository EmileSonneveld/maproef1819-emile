/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ public class LogicalAndListener implements Listener {
/*    */   private static Listener listener;
/*    */   
/*    */   private LogicalAndListener() {}
/*    */   
/*    */   static  {
/*  9 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.LogicalAndListener", new Factory());
/*    */   }
/*    */   
/*    */   static class Factory
/*    */     implements IFactory {
/*    */     public Listener getListener() {
/* 15 */       if (
/*    */ 
/*    */ 
/*    */         
/* 19 */         listener != null) {
/* 20 */         return listener;
/*    */       }
/* 22 */       listener = new LogicalAndListener(null); return new LogicalAndListener(null);
/*    */     }
/*    */ 
/*    */     
/* 26 */     public void cleanUp() { listener = null; }
/*    */   }
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 31 */     MetricsRepository mr = DefaultMetricRepository.getMetricRepository();
/* 32 */     mr.addLogicalAnd();
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\LogicalAndListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */