/*    */ package lrg.memoria.importer.recoder;
/*    */ public class ReturnListener implements Listener {
/*    */   private static Listener listener;
/*    */   
/*    */   private ReturnListener() {}
/*    */   
/*    */   static  {
/*  8 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.ReturnListener", new Factory());
/*    */   }
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 11 */       if (
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 18 */         listener != null) {
/* 19 */         return listener;
/*    */       }
/* 21 */       listener = new ReturnListener(null); return new ReturnListener(null);
/*    */     }
/*    */ 
/*    */     
/* 25 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 30 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 31 */     mer.addExit();
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\ReturnListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */