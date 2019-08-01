/*    */ package lrg.memoria.importer.recoder;
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import lrg.memoria.core.Location;
/*    */ import lrg.memoria.core.Method;
/*    */ import recoder.java.expression.operator.New;
/*    */ 
/*    */ public class NewListener extends StripeContainerListener {
/*    */   static  {
/*  9 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.NewListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private NewListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 15 */       if (
/*    */ 
/*    */ 
/*    */         
/* 19 */         listener == null) {
/* 20 */         listener = new NewListener(null); return new NewListener(null);
/*    */       } 
/* 22 */       return listener;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 40 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*    */     
/* 42 */     CodeStripe cs = mr.getCurrentStripe();
/* 43 */     if (cs != null) {
/* 44 */       New cr = (New)pe;
/* 45 */       Method met = ReferenceConverter.getConstructor(cr);
/* 46 */       met.setConstructor();
/* 47 */       if (((DataAbstraction)met.getScope()).getStatute() == 2)
/* 48 */         met.setStatute(2); 
/* 49 */       Call call = mr.addCall(met, met, cs);
/* 50 */       Location loc = new Location(mr.getCurrentFile());
/* 51 */       loc.setStartLine(cr.getStartPosition().getLine());
/* 52 */       loc.setStartChar(cr.getStartPosition().getColumn());
/* 53 */       loc.setEndLine(cr.getEndPosition().getLine());
/* 54 */       loc.setEndChar(cr.getEndPosition().getColumn());
/* 55 */       call.addInstance(cs.getRelPosOf(loc));
/*    */ 
/*    */       
/* 58 */       cs = new CodeStripe(mr.getCurrentStripe());
/* 59 */       cs.setAccess(1);
/* 60 */       cs.setSignature("[new_stmnt]");
/* 61 */       setActiveStripe(cs, mr, pe, null, null);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {
/* 68 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*    */     
/* 70 */     if (mr.getCurrentStripe() != null)
/* 71 */       restoreStripe(DefaultModelRepository.getModelRepository(null)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\NewListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */