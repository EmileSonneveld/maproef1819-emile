/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.statement.Branch;
/*    */ import recoder.java.statement.Switch;
/*    */ import recoder.list.generic.ASTList;
/*    */ 
/*    */ public class SwitchListener
/*    */   extends StripeContainerListener {
/*    */   static  {
/* 12 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.SwitchListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private SwitchListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 18 */       if (
/*    */ 
/*    */ 
/*    */         
/* 22 */         listener != null) {
/* 23 */         return listener;
/*    */       }
/* 25 */       listener = new SwitchListener(null); return new SwitchListener(null);
/*    */     }
/*    */ 
/*    */     
/* 29 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 34 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/*    */     
/* 36 */     Switch sw = (Switch)pe;
/* 37 */     for (int i = 0; i < sw.getBranchCount(); i++) {
/* 38 */       ASTList<Branch> bl = sw.getBranchList();
/* 39 */       mer.addStatements(((Branch)bl.get(i)).getStatementCount());
/*    */     } 
/* 41 */     mer.updateNestingLevel(1);
/*    */     
/* 43 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 44 */     CodeStripe cs = new CodeStripe(mr.getCurrentStripe());
/* 45 */     cs.setAccess(0);
/* 46 */     cs.setSignature("[switch_code]");
/* 47 */     if (sw.getBranchCount() > 0)
/* 48 */     { Branch last = sw.getBranchAt(sw.getBranchCount() - 1);
/* 49 */       if (last.getStatementCount() > 0)
/* 50 */       { setActiveStripe(cs, mr, pe, sw.getBranchAt(0), 
/* 51 */             last.getStatementAt(last.getStatementCount() - 1)); }
/* 52 */       else { setActiveStripe(cs, mr, pe, sw.getBranchAt(0), last); }
/*    */        }
/* 54 */     else { cs.setAccess(1);
/* 55 */       setActiveStripe(cs, mr, pe, null, null); }
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {
/* 61 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 62 */     mer.updateNestingLevel(-1);
/*    */     
/* 64 */     restoreStripe(DefaultModelRepository.getModelRepository(null));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\SwitchListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */