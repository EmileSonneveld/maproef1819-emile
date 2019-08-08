/*    */ package lrg.memoria.importer.recoder;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ public class ExtendsListener extends InheritanceSpecificationListener {
/*    */   static  {
/*  7 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.ExtendsListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private ExtendsListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 13 */       if (
/*    */ 
/*    */ 
/*    */         
/* 17 */         listener != null) {
/* 18 */         return listener;
/*    */       }
/* 20 */       listener = new ExtendsListener(null); return new ExtendsListener(null);
/*    */     }
/*    */ 
/*    */     
/* 24 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setInheritance(Class curClass, DataAbstraction superType) {
/* 29 */     if (curClass.isInterface()) {
/* 30 */       curClass.addInterface(superType);
/*    */     } else {
/* 32 */       curClass.addAncestor(superType);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\ExtendsListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */