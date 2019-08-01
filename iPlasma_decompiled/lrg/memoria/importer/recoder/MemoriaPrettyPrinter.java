/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import java.io.StringWriter;
/*    */ import java.io.Writer;
/*    */ import recoder.java.PrettyPrinter;
/*    */ import recoder.java.ProgramElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MemoriaPrettyPrinter
/*    */   extends PrettyPrinter
/*    */ {
/*    */   static MemoriaPrettyPrinter singleton;
/*    */   private Writer w;
/*    */   
/*    */   public static MemoriaPrettyPrinter getMemoriaPrettyPrinter() {
/* 21 */     if (singleton == null)
/* 22 */       singleton = new MemoriaPrettyPrinter(); 
/* 23 */     singleton.reset();
/* 24 */     return singleton;
/*    */   }
/*    */ 
/*    */   
/* 28 */   private MemoriaPrettyPrinter() { super(new StringWriter(), JavaModelLoader.getCrossReferenceServiceConfiguration().getProjectSettings().getProperties()); }
/*    */ 
/*    */ 
/*    */   
/*    */   private void reset() {
/* 33 */     this.w = new StringWriter();
/* 34 */     setWriter(this.w);
/*    */   }
/*    */   
/*    */   public String getSource(ProgramElement pe) {
/* 38 */     pe.accept(this);
/* 39 */     return this.w.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\MemoriaPrettyPrinter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */