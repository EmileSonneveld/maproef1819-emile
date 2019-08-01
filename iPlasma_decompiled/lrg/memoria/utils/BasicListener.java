/*    */ package lrg.memoria.utils;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import org.netbeans.lib.cvsclient.event.CVSAdapter;
/*    */ import org.netbeans.lib.cvsclient.event.MessageEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class BasicListener
/*    */   extends CVSAdapter
/*    */ {
/* 29 */   private final StringBuffer taggedLine = new StringBuffer();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void messageSent(MessageEvent e) {
/* 39 */     String line = e.getMessage();
/* 40 */     PrintStream stream = e.isError() ? System.err : System.out;
/*    */     
/* 42 */     if (e.isTagged()) {
/* 43 */       String message = MessageEvent.parseTaggedMessage(this.taggedLine, line);
/*    */ 
/*    */ 
/*    */       
/* 47 */       if (message != null) {
/* 48 */         stream.println(message);
/*    */       }
/*    */     } else {
/* 51 */       stream.println(line);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memori\\utils\BasicListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */