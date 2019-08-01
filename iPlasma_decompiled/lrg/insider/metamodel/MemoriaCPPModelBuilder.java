/*    */ package classes.lrg.insider.metamodel;
/*    */ 
/*    */ import lrg.common.utils.ProgressObserver;
/*    */ import lrg.insider.metamodel.MemoriaCPPModelBuilder;
/*    */ import lrg.insider.metamodel.MemoriaModelBuilder;
/*    */ import lrg.memoria.importer.mcc.TablesLoader;
/*    */ 
/*    */ public class MemoriaCPPModelBuilder extends MemoriaModelBuilder {
/*  9 */   public MemoriaCPPModelBuilder(String sourcePath, String cachePath, ProgressObserver observer) { super(sourcePath, cachePath, observer, "C++"); }
/*    */ 
/*    */ 
/*    */   
/* 13 */   protected void loadModel() throws Exception { this.currentSystem = (new TablesLoader(this.progressObserver, this.sourcePath, this.cachePath)).getSystem(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\metamodel\MemoriaCPPModelBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */