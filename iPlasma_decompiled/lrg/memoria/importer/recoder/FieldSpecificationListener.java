/*    */ package lrg.memoria.importer.recoder;
/*    */ import lrg.memoria.core.ArrayDecorator;
/*    */ import lrg.memoria.core.Attribute;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Location;
/*    */ import lrg.memoria.core.Type;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.declaration.FieldSpecification;
/*    */ import recoder.java.reference.TypeReference;
/*    */ 
/*    */ public class FieldSpecificationListener implements Listener {
/*    */   static  {
/* 13 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.FieldSpecificationListener", new Factory());
/*    */   }
/*    */   private static Listener listener;
/*    */   private FieldSpecificationListener() {}
/*    */   
/*    */   static class Factory implements IFactory { public Listener getListener() {
/* 19 */       if (
/*    */ 
/*    */ 
/*    */         
/* 23 */         listener != null) {
/* 24 */         return listener;
/*    */       }
/* 26 */       listener = new FieldSpecificationListener(null); return new FieldSpecificationListener(null);
/*    */     }
/*    */ 
/*    */     
/* 30 */     public void cleanUp() { listener = null; } }
/*    */ 
/*    */ 
/*    */   
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 35 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 36 */     FieldSpecification fs = (FieldSpecification)pe;
/* 37 */     Class clazz = mr.getCurrentClass();
/* 38 */     Attribute mmma = mr.addAttribute(fs, fs.getName());
/* 39 */     mmma.setStatute(1);
/* 40 */     TypeReference tr = fs.getParent().getTypeReference();
/* 41 */     int arrayDim = ReferenceConverter.getArrayDimension(tr);
/* 42 */     Type baseType = ReferenceConverter.getTypeFromTypeReference(tr);
/* 43 */     if (arrayDim > 0) {
/* 44 */       ArrayDecorator ad = mr.getArrayType(baseType, arrayDim);
/* 45 */       mmma.setType(ad);
/*    */     } else {
/* 47 */       mmma.setType(baseType);
/* 48 */     }  Location loc = new Location(mr.getCurrentFile());
/* 49 */     loc.setStartLine(fs.getFirstElement().getStartPosition().getLine());
/* 50 */     loc.setStartChar(fs.getFirstElement().getStartPosition().getColumn());
/* 51 */     loc.setEndLine(fs.getLastElement().getEndPosition().getLine());
/* 52 */     loc.setEndChar(fs.getLastElement().getEndPosition().getColumn());
/* 53 */     mmma.setLocation(loc);
/* 54 */     mmma.setScope(clazz);
/* 55 */     clazz.addAttribute(mmma);
/* 56 */     mmma.setAccessMode(RecoderToMemojConverter.convertAccessMode(fs));
/* 57 */     if (fs.isStatic())
/* 58 */       mmma.setStatic(); 
/* 59 */     if (fs.isFinal()) {
/* 60 */       mmma.setFinal();
/*    */     }
/*    */ 
/*    */     
/* 64 */     if (mr.getCurrentAnnotationInstance() != null) {
/* 65 */       mmma.addAnnotationInstance(mr.getCurrentAnnotationInstance());
/* 66 */       mr.getCurrentAnnotationInstance().setAnnotatedElement(mmma);
/* 67 */       mr.setCurrentAnnotationInstance(null);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\FieldSpecificationListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */