/*    */ package lrg.memoria.importer.recoder;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.declaration.ClassDeclaration;
/*    */ import recoder.java.declaration.Extends;
/*    */ 
/*    */ public class ClassDeclarationListener extends TypeDeclarationListener {
/*    */   static  {
/* 10 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.ClassDeclarationListener", new Factory());
/*    */   } private static Listener listener;
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 13 */       if (
/*    */ 
/*    */ 
/*    */         
/* 17 */         listener != null) {
/* 18 */         return listener;
/*    */       }
/* 20 */       listener = new ClassDeclarationListener(null); return new ClassDeclarationListener(null);
/*    */     }
/*    */ 
/*    */     
/* 24 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   private ClassDeclarationListener() {}
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 32 */     ClassDeclaration td = (ClassDeclaration)pe;
/* 33 */     DataAbstraction mmmc = buildClassFromDeclaration(td);
/* 34 */     Extends ext = td.getExtendedTypes();
/* 35 */     if (ext == null)
/* 36 */       mmmc.addAncestor(Class.getHierarchyRootClass()); 
/* 37 */     super.enterModelComponent(pe);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\ClassDeclarationListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */