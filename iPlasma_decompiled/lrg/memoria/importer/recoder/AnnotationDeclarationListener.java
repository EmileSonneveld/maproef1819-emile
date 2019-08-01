/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import java.util.Stack;
/*    */ import lrg.memoria.core.Annotation;
/*    */ import lrg.memoria.core.AnnotationProperty;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Location;
/*    */ import recoder.abstraction.Method;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.declaration.AnnotationDeclaration;
/*    */ import recoder.java.declaration.MethodDeclaration;
/*    */ 
/*    */ public class AnnotationDeclarationListener
/*    */   implements Listener {
/*    */   static  {
/* 16 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.AnnotationDeclarationListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/* 19 */   static class Factory implements IFactory { public void cleanUp() { listener = 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 24 */         null; }
/*    */ 
/*    */     
/*    */     public Listener getListener() {
/* 28 */       if (listener != null) {
/* 29 */         return listener;
/*    */       }
/* 31 */       listener = new AnnotationDeclarationListener(null); return new AnnotationDeclarationListener(null);
/*    */     } }
/*    */ 
/*    */ 
/*    */   
/* 36 */   private Stack<Annotation> oldAnnotations = new Stack();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 42 */     AnnotationDeclaration ad = (AnnotationDeclaration)pe;
/* 43 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*    */ 
/*    */     
/* 46 */     Class cls = mr.addClass(ad, ad.getName());
/* 47 */     Annotation mmma = mr.addAnnotation(ad, ad.getFullName());
/*    */ 
/*    */ 
/*    */     
/* 51 */     Location loc = new Location(mr.getCurrentFile());
/* 52 */     loc.setStartLine(ad.getStartPosition().getLine());
/* 53 */     loc.setStartChar(ad.getStartPosition().getColumn());
/* 54 */     loc.setEndLine(ad.getEndPosition().getLine());
/* 55 */     loc.setEndChar(ad.getEndPosition().getColumn());
/* 56 */     mmma.setLocation(loc);
/* 57 */     for (Method f : ad.getMethods()) {
/* 58 */       AnnotationProperty ap = null;
/* 59 */       for (AnnotationProperty apr : mmma.getAnnotationProperties()) {
/* 60 */         if (apr.getName() == f.getName()) {
/*    */           
/* 62 */           ap = apr;
/*    */           break;
/*    */         } 
/*    */       } 
/* 66 */       Location location = new Location(mr.getCurrentFile());
/* 67 */       location.setStartLine(((MethodDeclaration)f).getFirstElement().getStartPosition().getLine());
/* 68 */       location.setStartChar(((MethodDeclaration)f).getFirstElement().getStartPosition().getColumn());
/* 69 */       location.setEndLine(((MethodDeclaration)f).getEndPosition().getLine());
/* 70 */       location.setEndChar(((MethodDeclaration)f).getEndPosition().getColumn());
/* 71 */       if (ap != null)
/* 72 */         ap.setLocation(location); 
/*    */     } 
/* 74 */     if (cls != null) {
/*    */       
/* 76 */       cls.setStatute(mmma.getStatute());
/* 77 */       cls.setLocation(mmma.getLocation());
/*    */ 
/*    */       
/* 80 */       cls.setInterface();
/*    */     } 
/* 82 */     this.oldAnnotations.push(mr.getCurrentAnnotation());
/* 83 */     mr.setCurrentAnnotation(mmma);
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {
/* 87 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 88 */     mr.setCurrentAnnotation((Annotation)this.oldAnnotations.pop());
/*    */   }
/*    */   
/*    */   private AnnotationDeclarationListener() {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\AnnotationDeclarationListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */