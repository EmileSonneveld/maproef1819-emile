/*    */ package lrg.memoria.importer.recoder.recoder.io;
/*    */ 
/*    */ import java.io.FilenameFilter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import recoder.ServiceConfiguration;
/*    */ import recoder.io.DataLocation;
/*    */ import recoder.io.DefaultSourceFileRepository;
/*    */ import recoder.java.CompilationUnit;
/*    */ import recoder.parser.TokenMgrError;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MemoriaDefaultSourceFileRepository
/*    */   extends DefaultSourceFileRepository
/*    */ {
/* 23 */   public MemoriaDefaultSourceFileRepository(ServiceConfiguration sc) { super(sc); }
/*    */ 
/*    */   
/*    */   public List<CompilationUnit> getAllCompilationUnitsFromPath(FilenameFilter filenameFilter) {
/* 27 */     DataLocation[] locations = getSearchPathList().findAll(filenameFilter);
/* 28 */     ArrayList<CompilationUnit> res = 
/* 29 */       new ArrayList<CompilationUnit>(locations.length);
/*    */     
/* 31 */     for (int i = 0; i < locations.length; i++) {
/*    */       try {
/* 33 */         CompilationUnit cu = getCompilationUnitFromLocation(locations[i]);
/* 34 */         res.add(cu);
/* 35 */       } catch (Exception e) {
/* 36 */         System.err.println("Error in compilation unit: " + locations[i].toString());
/* 37 */         System.out.println(e);
/* 38 */         e.printStackTrace();
/* 39 */       } catch (TokenMgrError e) {
/* 40 */         System.err.println("Error in compilation unit: " + locations[i].toString());
/* 41 */         System.out.println(e);
/* 42 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/* 45 */     return res;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\recoder\io\MemoriaDefaultSourceFileRepository.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */