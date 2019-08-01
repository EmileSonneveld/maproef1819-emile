/*    */ package classes.lrg.insider.metamodel;
/*    */ 
/*    */ import lrg.common.utils.ProgressObserver;
/*    */ import lrg.insider.metamodel.MemoriaCacheModelBuilder;
/*    */ import lrg.insider.metamodel.MemoriaModelBuilder;
/*    */ import lrg.memoria.importer.CacheModelLoader;
/*    */ 
/*    */ public class MemoriaCacheModelBuilder extends MemoriaModelBuilder {
/*  9 */   public MemoriaCacheModelBuilder(String cachePath, ProgressObserver observer) { super(null, cachePath, observer); }
/*    */ 
/*    */ 
/*    */   
/* 13 */   protected void loadModel() throws Exception { this.currentSystem = (new CacheModelLoader()).loadModel(this.cachePath); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\metamodel\MemoriaCacheModelBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */