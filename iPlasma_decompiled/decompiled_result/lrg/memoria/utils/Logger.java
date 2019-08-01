/*    */ package lrg.memoria.utils;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Logger
/*    */   extends PrintStream
/*    */ {
/*    */   private PrintStream oldStdout;
/*    */   private PrintStream oldStderr;
/*    */   
/*    */   public Logger(OutputStream os) {
/* 18 */     super(os);
/* 19 */     this.oldStdout = System.out;
/* 20 */     this.oldStderr = System.err;
/* 21 */     System.setErr(this);
/* 22 */     System.setOut(this);
/*    */   }
/*    */   
/*    */   public void write(int b) {
/*    */     try {
/* 27 */       super.write(b);
/* 28 */       this.oldStdout.write(b);
/* 29 */     } catch (Exception e) {
/* 30 */       e.printStackTrace();
/* 31 */       setError();
/*    */     } 
/* 33 */     super.write(b);
/*    */   }
/*    */   
/*    */   public void write(byte[] buf, int off, int len) {
/*    */     try {
/* 38 */       super.write(buf, off, len);
/* 39 */       this.oldStdout.write(buf, off, len);
/* 40 */     } catch (Exception e) {
/* 41 */       e.printStackTrace();
/* 42 */       setError();
/*    */     } 
/* 44 */     super.write(buf, off, len);
/*    */   }
/*    */   
/*    */   public void close() {
/* 48 */     System.setErr(this.oldStderr);
/* 49 */     System.setOut(this.oldStdout);
/* 50 */     super.close();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memori\\utils\Logger.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */