/*    */ package lrg.memoria.importer.mcc;
/*    */ 
/*    */ import java.io.FileNotFoundException;
/*    */ import lrg.common.utils.ProgressObserver;
/*    */ import lrg.memoria.importer.AbstractModelLoader;
/*    */ import lrg.memoria.importer.mcc.javacc.ParseException;
/*    */ import lrg.memoria.importer.mcc.javacc.TablesParser;
/*    */ import lrg.memoria.importer.mcc.loader.Loader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TablesLoader
/*    */   extends AbstractModelLoader
/*    */ {
/*    */   public TablesLoader(ProgressObserver po, String sourcePath, String cachePath) throws Exception {
/* 22 */     super(po);
/* 23 */     Loader.getInstance().setSystemName(sourcePath);
/* 24 */     loadModel(sourcePath, cachePath);
/*    */   }
/*    */   
/*    */   protected void loadModelFromSources(String path) throws FileNotFoundException {
/* 28 */     TablesParser tp = new TablesParser(path, this.loadingProgressObserver);
/*    */     
/*    */     try {
/* 31 */       tp.parseTables();
/* 32 */     } catch (ParseException e1) {
/* 33 */       System.out.println(e1);
/* 34 */       System.err.println("\nThe loading was aborted. An error occured while parsing.\n");
/* 35 */       System.exit(25);
/*    */     } 
/* 37 */     Loader.getInstance().createLazyLinks();
/* 38 */     this.system = Loader.getInstance().getSystem();
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 42 */     if (args.length != 1) {
/* 43 */       System.out.println("Usage: java TablesLoader tables_path !!");
/* 44 */       System.exit(1);
/*    */     } 
/*    */     
/*    */     try {
/* 48 */       TablesLoader tl = new TablesLoader(null, args[0], null);
/* 49 */       System.out.println("Loading mcc from the path: " + args[0]);
/* 50 */       System.out.println(tl.getSystem());
/* 51 */     } catch (Exception e) {
/* 52 */       System.out.println(e);
/* 53 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\TablesLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */