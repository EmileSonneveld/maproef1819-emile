/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ public class CompilationUnitListener implements Listener {
/*    */   private static Listener listener;
/*    */   
/*    */   private CompilationUnitListener() {}
/*    */   
/*    */   static  {
/*  9 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.CompilationUnitListener", new Factory());
/*    */   }
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 12 */       if (
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 19 */         listener != null) {
/* 20 */         return listener;
/*    */       }
/* 22 */       listener = new CompilationUnitListener(null); return new CompilationUnitListener(null);
/*    */     }
/*    */ 
/*    */     
/* 26 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 31 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 32 */     mr.setCurrentFile(mr.addFile(pe, ((CompilationUnit)pe).getName()));
/* 33 */     mr.setCurrentPackage(Package.getAnonymousPackage());
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {
/* 37 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 38 */     mr.setCurrentFile(File.getUnknownFile());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\CompilationUnitListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */