/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import java.util.Hashtable;
/*    */ import java.util.List;
/*    */ import recoder.CrossReferenceServiceConfiguration;
/*    */ import recoder.abstraction.ClassTypeContainer;
/*    */ import recoder.bytecode.ClassFile;
/*    */ import recoder.java.Import;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.reference.PackageReference;
/*    */ import recoder.java.reference.TypeReference;
/*    */ 
/*    */ public class ImportListener implements Listener {
/*    */   private static Listener listener;
/*    */   private Hashtable name2package;
/*    */   
/*    */   static  {
/* 18 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.ImportListener", new Factory());
/*    */   }
/*    */ 
/*    */   
/* 22 */   private ImportListener() { getAllLibraryPackages(); }
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 25 */       if (
/*    */ 
/*    */ 
/*    */         
/* 29 */         listener != null) {
/* 30 */         return listener;
/*    */       }
/* 32 */       listener = new ImportListener(null); return new ImportListener(null);
/*    */     }
/*    */ 
/*    */     
/* 36 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/*    */     String packName;
/* 43 */     Import imp = (Import)pe;
/* 44 */     PackageReference packageReference = imp.getReference();
/* 45 */     if (packageReference instanceof TypeReference)
/* 46 */       packageReference = ((TypeReference)packageReference).getPackageReference(); 
/* 47 */     if (packageReference == null) {
/*    */       return;
/*    */     }
/* 50 */     if (packageReference instanceof PackageReference) {
/* 51 */       packName = ((PackageReference)packageReference).getName();
/*    */     } else {
/*    */       return;
/* 54 */     }  PackageReference prefix = (PackageReference)((PackageReference)packageReference).getReferencePrefix();
/* 55 */     while (prefix != null) {
/* 56 */       packName = String.valueOf(prefix.getName()) + "." + packName;
/* 57 */       prefix = (PackageReference)prefix.getReferencePrefix();
/*    */     } 
/* 59 */     CrossReferenceServiceConfiguration crsc = JavaModelLoader.getCrossReferenceServiceConfiguration();
/* 60 */     if (this.name2package.get(packName) == null) {
/* 61 */       DefaultModelRepository dmr = DefaultModelRepository.getModelRepository(null);
/* 62 */       dmr.addPackage(null, packName);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {}
/*    */   
/*    */   private void getAllLibraryPackages() {
/* 70 */     this.name2package = new Hashtable();
/* 71 */     CrossReferenceServiceConfiguration crsc = JavaModelLoader.getCrossReferenceServiceConfiguration();
/* 72 */     List<ClassFile> cf = crsc.getClassFileRepository().getKnownClassFiles();
/* 73 */     for (int i = cf.size() - 1; i >= 0; i--) {
/* 74 */       ClassTypeContainer ctc = ((ClassFile)cf.get(i)).getContainer();
/* 75 */       if (ctc instanceof recoder.abstraction.Package)
/* 76 */         this.name2package.put(ctc.getFullName(), ctc); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\ImportListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */