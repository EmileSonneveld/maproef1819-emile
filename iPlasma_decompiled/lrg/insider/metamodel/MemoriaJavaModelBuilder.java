/*    */ package classes.lrg.insider.metamodel;
/*    */ import lrg.common.utils.ProgressObserver;
/*    */ import lrg.insider.metamodel.MemoriaJavaModelBuilder;
/*    */ import lrg.insider.metamodel.MemoriaModelBuilder;
/*    */ import lrg.memoria.importer.recoder.JavaModelLoader;
/*    */ 
/*    */ public class MemoriaJavaModelBuilder extends MemoriaModelBuilder {
/*    */   public MemoriaJavaModelBuilder(String sourcePath, String cachePath, String additionalCP, ProgressObserver observer) {
/*  9 */     super(sourcePath, cachePath, observer, "Java");
/* 10 */     this.additionalClassPath = additionalCP;
/*    */   }
/*    */   private String additionalClassPath;
/*    */   
/* 14 */   protected void loadModel() throws Exception { this.currentSystem = (new JavaModelLoader(this.sourcePath, this.cachePath, this.additionalClassPath, this.progressObserver)).getSystem(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\metamodel\MemoriaJavaModelBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */