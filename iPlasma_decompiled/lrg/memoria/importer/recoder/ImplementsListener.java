/*    */ package lrg.memoria.importer.recoder;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ public class ImplementsListener extends InheritanceSpecificationListener {
/*    */   static  {
/*  7 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.ImplementsListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private ImplementsListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 13 */       if (
/*    */ 
/*    */ 
/*    */         
/* 17 */         listener != null) {
/* 18 */         return listener;
/*    */       }
/* 20 */       listener = new ImplementsListener(null); return new ImplementsListener(null);
/*    */     }
/*    */ 
/*    */     
/* 24 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   protected void setInheritance(Class curClass, DataAbstraction superType) { curClass.addInterface(superType); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\ImplementsListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */