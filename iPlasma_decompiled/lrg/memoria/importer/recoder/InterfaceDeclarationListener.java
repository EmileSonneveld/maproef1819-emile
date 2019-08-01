/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.declaration.InterfaceDeclaration;
/*    */ 
/*    */ public class InterfaceDeclarationListener extends TypeDeclarationListener {
/*    */   static  {
/*  8 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.InterfaceDeclarationListener", new Factory());
/*    */   } private static Listener listener;
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 11 */       if (
/*    */ 
/*    */ 
/*    */         
/* 15 */         listener != null) {
/* 16 */         return listener;
/*    */       }
/* 18 */       listener = new InterfaceDeclarationListener(null); return new InterfaceDeclarationListener(null);
/*    */     }
/*    */ 
/*    */     
/* 22 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   private InterfaceDeclarationListener() {}
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 30 */     InterfaceDeclaration td = (InterfaceDeclaration)pe;
/* 31 */     buildClassFromDeclaration(td);
/* 32 */     super.enterModelComponent(pe);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\InterfaceDeclarationListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */