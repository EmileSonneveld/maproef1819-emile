/*    */ package lrg.memoria.importer.recoder;
/*    */ import lrg.memoria.core.Method;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.declaration.Throws;
/*    */ import recoder.java.reference.TypeReference;
/*    */ import recoder.list.generic.ASTList;
/*    */ 
/*    */ public class ThrowsListener implements Listener {
/*    */   static  {
/* 10 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.ThrowsListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private ThrowsListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 16 */       if (
/*    */ 
/*    */ 
/*    */         
/* 20 */         listener == null) {
/* 21 */         listener = new ThrowsListener(null); return new ThrowsListener(null);
/*    */       } 
/* 23 */       return listener;
/*    */     }
/*    */ 
/*    */     
/* 27 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 32 */     Throws t = (Throws)pe;
/* 33 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 34 */     Method mmmm = mr.getCurrentMethod();
/*    */     
/* 36 */     ASTList<TypeReference> trl = t.getExceptions();
/* 37 */     for (int i = 0; i < trl.size(); i++)
/* 38 */       mmmm.addException(ReferenceConverter.getClassType((TypeReference)trl.get(i))); 
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\ThrowsListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */