/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import java.util.Stack;
/*    */ import lrg.memoria.core.Body;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.InitializerBody;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.declaration.ClassInitializer;
/*    */ 
/*    */ public class ClassInitializerListener
/*    */   implements Listener
/*    */ {
/*    */   static  {
/* 14 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.ClassInitializerListener", new Factory());
/*    */   }
/*    */   
/*    */   private static Listener listener;
/* 18 */   private Stack oldBodies = new Stack();
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 21 */       if (
/*    */ 
/*    */ 
/*    */         
/* 25 */         listener == null) {
/* 26 */         listener = new ClassInitializerListener(null); return new ClassInitializerListener(null);
/*    */       } 
/* 28 */       return listener;
/*    */     }
/*    */ 
/*    */     
/* 32 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 37 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 38 */     mer.resetAll();
/* 39 */     ClassInitializer ci = (ClassInitializer)pe;
/* 40 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 41 */     Class mmmc = mr.getCurrentClass();
/*    */     
/* 43 */     InitializerBody ib = new InitializerBody(mmmc);
/* 44 */     if (ci.isStatic())
/* 45 */       ib.setStatic(); 
/* 46 */     mmmc.addInitializer(ib);
/*    */ 
/*    */     
/* 49 */     MemoriaPrettyPrinter mpp = MemoriaPrettyPrinter.getMemoriaPrettyPrinter();
/* 50 */     ib.setSourceCode(mpp.getSource(ci));
/*    */     
/* 52 */     this.oldBodies.push(mr.getCurrentBody());
/* 53 */     mr.setCurrentBody(ib);
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {
/* 57 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 62 */     mr.setCurrentBody((Body)this.oldBodies.pop());
/*    */   }
/*    */   
/*    */   private ClassInitializerListener() {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\ClassInitializerListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */