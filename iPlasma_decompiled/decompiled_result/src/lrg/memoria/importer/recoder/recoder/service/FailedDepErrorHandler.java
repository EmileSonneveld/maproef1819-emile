/*    */ package lrg.memoria.importer.recoder.recoder.service;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.service.DefaultErrorHandler;
/*    */ 
/*    */ public class FailedDepErrorHandler
/*    */   extends DefaultErrorHandler
/*    */ {
/* 10 */   public FailedDepErrorHandler() { super(10000000); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   protected boolean isReferingUnavailableCode(ProgramElement e) { return true; }
/*    */   
/*    */   public void modelUpdated(EventObject e) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\recoder\service\FailedDepErrorHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */