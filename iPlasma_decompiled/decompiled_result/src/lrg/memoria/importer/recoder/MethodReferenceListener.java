/*    */ package lrg.memoria.importer.recoder;
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import lrg.memoria.core.Location;
/*    */ import lrg.memoria.core.Method;
/*    */ import recoder.java.reference.MethodReference;
/*    */ 
/*    */ public class MethodReferenceListener implements Listener {
/*    */   static  {
/*  9 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.MethodReferenceListener", new Factory());
/*    */   } private static Listener listener; private MethodReferenceListener() {}
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
/* 22 */       listener = new MethodReferenceListener(null); return new MethodReferenceListener(null);
/*    */     }
/*    */ 
/*    */     
/* 26 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 35 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 51 */     CodeStripe cs = mr.getCurrentStripe();
/* 52 */     if (cs != null) {
/* 53 */       MethodReference metr = (MethodReference)pe;
/* 54 */       Method mmmm = ReferenceConverter.getMethod(metr);
/* 55 */       Location loc = new Location(mr.getCurrentFile());
/* 56 */       Call call = mr.addCall(mmmm, mmmm, cs);
/* 57 */       loc.setStartLine(metr.getFirstElement().getStartPosition().getLine());
/* 58 */       loc.setStartChar(metr.getFirstElement().getStartPosition().getColumn());
/* 59 */       loc.setEndLine(metr.getLastElement().getEndPosition().getLine());
/* 60 */       loc.setEndChar(metr.getLastElement().getEndPosition().getColumn());
/* 61 */       call.addInstance(cs.getRelPosOf(loc));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\MethodReferenceListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */