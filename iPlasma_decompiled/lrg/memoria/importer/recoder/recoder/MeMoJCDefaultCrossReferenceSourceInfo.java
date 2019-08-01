/*    */ package lrg.memoria.importer.recoder.recoder;
/*    */ 
/*    */ import recoder.CrossReferenceServiceConfiguration;
/*    */ import recoder.service.ChangeHistoryEvent;
/*    */ import recoder.service.DefaultCrossReferenceSourceInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MeMoJCDefaultCrossReferenceSourceInfo
/*    */   extends DefaultCrossReferenceSourceInfo
/*    */ {
/* 14 */   public MeMoJCDefaultCrossReferenceSourceInfo(CrossReferenceServiceConfiguration crsc) { super(crsc); }
/*    */ 
/*    */   
/*    */   public void modelChanged(ChangeHistoryEvent changes) {
/*    */     try {
/* 19 */       super.modelChanged(changes);
/* 20 */     } catch (Exception e) {
/* 21 */       System.out.println(e);
/*    */ 
/*    */     
/*    */     }
/* 25 */     catch (Throwable e) {
/* 26 */       System.out.println(e);
/* 27 */       System.out.println("Strange Character: " + changes.toString());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\recoder\MeMoJCDefaultCrossReferenceSourceInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */