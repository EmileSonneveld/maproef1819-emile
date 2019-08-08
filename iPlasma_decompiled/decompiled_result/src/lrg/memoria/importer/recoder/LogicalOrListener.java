/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import recoder.java.ProgramElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogicalOrListener
/*    */   implements Listener
/*    */ {
/*    */   private static Listener listener;
/*    */   
/*    */   private LogicalOrListener() {}
/*    */   
/*    */   static  {
/* 15 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.LogicalOrListener", new Factory());
/*    */   }
/*    */   
/*    */   static class Factory
/*    */     implements IFactory {
/*    */     public Listener getListener() {
/* 21 */       if (
/*    */ 
/*    */ 
/*    */         
/* 25 */         listener != null) {
/* 26 */         return listener;
/*    */       }
/* 28 */       listener = new LogicalOrListener(null); return new LogicalOrListener(null);
/*    */     }
/*    */ 
/*    */     
/* 32 */     public void cleanUp() { listener = null; }
/*    */   }
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 37 */     MetricsRepository mr = DefaultMetricRepository.getMetricRepository();
/* 38 */     mr.addLogicalOr();
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\LogicalOrListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */