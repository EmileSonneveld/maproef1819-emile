/*    */ package lrg.memoria.utils;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
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
/*    */ class InputStreamHandler
/*    */   extends Thread
/*    */ {
/*    */   private InputStream m_stream;
/*    */   private StringBuffer m_captureBuffer;
/*    */   
/*    */   InputStreamHandler(StringBuffer captureBuffer, InputStream stream) {
/* 44 */     this.m_stream = stream;
/* 45 */     this.m_captureBuffer = captureBuffer;
/*    */     
/* 47 */     start();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/*    */     try {
/*    */       int nextChar;
/* 57 */       while ((nextChar = this.m_stream.read()) != -1) {
/* 58 */         this.m_captureBuffer.append((char)nextChar);
/*    */       }
/* 60 */     } catch (IOException ioe) {
/* 61 */       IOException iOException; iOException.getMessage();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memori\\utils\InputStreamHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */