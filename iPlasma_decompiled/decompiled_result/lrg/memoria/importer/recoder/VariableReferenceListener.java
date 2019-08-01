/*    */ package lrg.memoria.importer.recoder;
/*    */ import lrg.memoria.core.Access;
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import lrg.memoria.core.Location;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.Reference;
/*    */ import recoder.java.reference.VariableReference;
/*    */ 
/*    */ public class VariableReferenceListener implements Listener {
/*    */   static  {
/* 11 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.VariableReferenceListener", new Factory());
/*    */   } private static Listener listener; private VariableReferenceListener() {}
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 14 */       if (
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 21 */         listener != null) {
/* 22 */         return listener;
/*    */       }
/* 24 */       listener = new VariableReferenceListener(null); return new VariableReferenceListener(null);
/*    */     }
/*    */ 
/*    */     
/* 28 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 33 */     VariableReference vr = (VariableReference)pe;
/* 34 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     CodeStripe cs = mr.getCurrentStripe();
/*    */     
/* 42 */     try { if (cs != null) {
/* 43 */         Variable mmmv = ReferenceConverter.getVariable(vr);
/* 44 */         Access access = mr.addAccess(mmmv, mmmv, cs);
/*    */         
/* 46 */         Location loc = new Location(mr.getCurrentFile());
/* 47 */         loc.setStartLine(vr.getFirstElement().getStartPosition().getLine());
/* 48 */         loc.setStartChar(vr.getFirstElement().getStartPosition().getColumn());
/* 49 */         loc.setEndLine(vr.getLastElement().getEndPosition().getLine());
/* 50 */         loc.setEndChar(vr.getLastElement().getEndPosition().getColumn());
/* 51 */         Reference reference = vr;
/* 52 */         while (reference.getASTParent() instanceof Reference)
/* 53 */           reference = (Reference)reference.getASTParent(); 
/* 54 */         if (reference.getASTParent() instanceof recoder.java.expression.Assignment && reference.getASTParent().getChildAt(false) == reference)
/* 55 */         { access.addInstance(cs.getRelPosOf(loc), 2); }
/*    */         else
/* 57 */         { access.addInstance(cs.getRelPosOf(loc), 1); } 
/*    */       }  }
/* 59 */     catch (IllegalArgumentException e) { System.err.println(e); }
/*    */   
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\VariableReferenceListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */