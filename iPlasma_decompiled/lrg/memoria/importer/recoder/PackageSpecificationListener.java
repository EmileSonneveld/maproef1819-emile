/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ public class PackageSpecificationListener implements Listener {
/*    */   private static Listener listener;
/*    */   
/*    */   private PackageSpecificationListener() {}
/*    */   
/*    */   static  {
/*  9 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.PackageSpecificationListener", new Factory());
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
/* 22 */       listener = new PackageSpecificationListener(null); return new PackageSpecificationListener(null);
/*    */     }
/*    */ 
/*    */     
/* 26 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 31 */     Package mmmp = ReferenceConverter.getPackage(((PackageSpecification)pe).getPackageReference());
/* 32 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 33 */     mmmp.setStatute(1);
/* 34 */     mr.setCurrentPackage(mmmp);
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\PackageSpecificationListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */