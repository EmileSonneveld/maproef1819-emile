/*     */ package lrg.memoria.importer.recoder;
/*     */ 
/*     */ import java.util.Stack;
/*     */ import lrg.membrain.representation.ControlFlowGraph;
/*     */ import lrg.memoria.core.Body;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.FunctionBody;
/*     */ import lrg.memoria.core.Location;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.XFunctionBody;
/*     */ import lrg.memoria.core.factories.BodyFactory;
/*     */ import recoder.java.ProgramElement;
/*     */ import recoder.java.declaration.MethodDeclaration;
/*     */ import recoder.java.reference.TypeReference;
/*     */ 
/*     */ 
/*     */ public class MethodDeclarationListener
/*     */   implements Listener
/*     */ {
/*     */   static  {
/*  21 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.MethodDeclarationListener", new Factory());
/*     */   }
/*     */ 
/*     */   
/*  25 */   private Stack oldBodies = new Stack(); private static Listener listener;
/*  26 */   private Stack oldMethods = new Stack();
/*     */   
/*     */   static class Factory implements IFactory { public Listener getListener() {
/*  29 */       if (
/*     */ 
/*     */ 
/*     */         
/*  33 */         listener != null) {
/*  34 */         return listener;
/*     */       }
/*  36 */       listener = new MethodDeclarationListener(); return new MethodDeclarationListener();
/*     */     }
/*     */ 
/*     */     
/*  40 */     public void cleanUp() { listener = null; } }
/*     */ 
/*     */ 
/*     */   
/*     */   public void enterModelComponent(ProgramElement pe) {
/*  45 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*  46 */     MethodDeclaration md = (MethodDeclaration)pe;
/*  47 */     String name = md.getName();
/*  48 */     Method mmmm = mr.addMethod(md, name);
/*  49 */     mmmm.setStatute(1);
/*  50 */     Class clazz = mr.getCurrentClass();
/*  51 */     mmmm.setScope(clazz);
/*  52 */     clazz.addMethod(mmmm);
/*  53 */     this.oldMethods.push(mr.getCurrentMethod());
/*  54 */     mr.setCurrentMethod(mmmm);
/*  55 */     Location loc = new Location(mr.getCurrentFile());
/*  56 */     loc.setStartLine(md.getFirstElement().getStartPosition().getLine() - 1);
/*  57 */     loc.setStartChar(md.getFirstElement().getStartPosition().getColumn() - 1);
/*  58 */     loc.setEndLine(md.getLastElement().getEndPosition().getLine() - 1);
/*  59 */     loc.setEndChar(md.getLastElement().getEndPosition().getColumn() - 1);
/*  60 */     mmmm.setLocation(loc);
/*  61 */     if (md instanceof recoder.java.declaration.ConstructorDeclaration) {
/*  62 */       mmmm.setConstructor();
/*     */     } else {
/*     */       
/*  65 */       if (name.startsWith("get") || name.startsWith("set"))
/*  66 */         mmmm.setAccessor(); 
/*  67 */       TypeReference tr = md.getTypeReference();
/*  68 */       mmmm.setReturnType(ReferenceConverter.getTypeFromTypeReference(tr));
/*     */     } 
/*  70 */     mmmm.setAccessMode(RecoderToMemojConverter.convertAccessMode(md));
/*  71 */     if (md.isStatic())
/*  72 */       mmmm.setStatic(); 
/*  73 */     if (md.isFinal())
/*  74 */       mmmm.setFinal(); 
/*  75 */     this.oldBodies.push(mr.getCurrentBody());
/*  76 */     if (md.isAbstract()) {
/*  77 */       mr.setCurrentBody(null);
/*     */       
/*  79 */       mmmm.setAbstract();
/*     */     } else {
/*  81 */       MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/*  82 */       mer.resetAll();
/*  83 */       FunctionBody mb = (FunctionBody)BodyFactory.getInstance().produceBody(mmmm);
/*     */       
/*  85 */       MemoriaPrettyPrinter mpp = MemoriaPrettyPrinter.getMemoriaPrettyPrinter();
/*  86 */       mb.setSourceCode(mpp.getSource(md));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  94 */       mmmm.setFunctionBody(mb);
/*  95 */       mr.setCurrentBody(mb);
/*     */       try {
/*  97 */         if (mb instanceof XFunctionBody) {
/*  98 */           System.out.print("Building control flow graph...");
/*  99 */           ((XFunctionBody)mb).setControlFlowGraph(
/* 100 */               new ControlFlowGraph(md));
/* 101 */           System.out.println("done");
/*     */         } 
/* 103 */       } catch (Throwable e) {
/* 104 */         System.out.print("Type: " + e.getClass() + " Message: ");
/* 105 */         System.out.println(e.getLocalizedMessage());
/* 106 */         if (!(e instanceof lrg.membrain.representation.exception.TranslationException)) e.printStackTrace(); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void leaveModelComponent(ProgramElement pe) {
/* 112 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 113 */     MethodDeclaration md = (MethodDeclaration)pe;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     mr.setCurrentBody((Body)this.oldBodies.pop());
/* 122 */     mr.setCurrentMethod((Method)this.oldMethods.pop());
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\MethodDeclarationListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */