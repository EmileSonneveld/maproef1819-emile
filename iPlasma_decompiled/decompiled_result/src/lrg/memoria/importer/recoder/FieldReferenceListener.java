/*    */ package lrg.memoria.importer.recoder;
/*    */ import lrg.memoria.core.Access;
/*    */ import lrg.memoria.core.Attribute;
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import lrg.memoria.core.Location;
/*    */ import recoder.java.Reference;
/*    */ import recoder.java.reference.FieldReference;
/*    */ 
/*    */ public class FieldReferenceListener implements Listener {
/*    */   static  {
/* 11 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.FieldReferenceListener", new Factory());
/*    */   } private static Listener listener; private FieldReferenceListener() {}
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
/* 24 */       listener = new FieldReferenceListener(null); return new FieldReferenceListener(null);
/*    */     }
/*    */ 
/*    */     
/* 28 */     public void cleanUp() { listener = null; } }
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
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 41 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 62 */     CodeStripe cs = mr.getCurrentStripe();
/* 63 */     if (cs != null) {
/* 64 */       FieldReference fr = (FieldReference)pe;
/* 65 */       Attribute attribute = ReferenceConverter.getField(fr);
/* 66 */       Access access = mr.addAccess(attribute, attribute, cs);
/*    */       
/* 68 */       Location loc = new Location(mr.getCurrentFile());
/* 69 */       loc.setStartLine(fr.getFirstElement().getStartPosition().getLine());
/* 70 */       loc.setStartChar(fr.getFirstElement().getStartPosition().getColumn());
/* 71 */       loc.setEndLine(fr.getLastElement().getEndPosition().getLine());
/* 72 */       loc.setEndChar(fr.getLastElement().getEndPosition().getColumn());
/*    */       
/* 74 */       Reference reference = fr;
/* 75 */       while (reference.getASTParent() instanceof Reference)
/* 76 */         reference = (Reference)reference.getASTParent(); 
/* 77 */       if (reference.getASTParent() instanceof recoder.java.expression.Assignment && reference.getASTParent().getChildAt(false) == reference) {
/* 78 */         access.addInstance(cs.getRelPosOf(loc), 2);
/*    */       } else {
/* 80 */         access.addInstance(cs.getRelPosOf(loc), 1);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\FieldReferenceListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */