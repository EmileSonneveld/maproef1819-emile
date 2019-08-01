/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import lrg.memoria.core.InheritanceRelation;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.java.declaration.InheritanceSpecification;
/*    */ import recoder.java.reference.TypeReference;
/*    */ import recoder.list.generic.ASTList;
/*    */ 
/*    */ public abstract class InheritanceSpecificationListener implements Listener {
/*    */   public void enterModelComponent(ProgramElement pe) {
/* 13 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/* 14 */     InheritanceSpecification e = (InheritanceSpecification)pe;
/*    */     
/* 16 */     ASTList<TypeReference> trl = e.getSupertypes();
/* 17 */     for (int i = 0; i < trl.size(); i++) {
/* 18 */       TypeReference tr = (TypeReference)trl.get(i);
/* 19 */       Class curClass = mr.getCurrentClass();
/* 20 */       Class mmmc = ReferenceConverter.getClassType(tr);
/* 21 */       setInheritance(curClass, mmmc);
/* 22 */       mmmc.addDescendant(curClass);
/* 23 */       InheritanceRelation rel = new InheritanceRelation(curClass, mmmc, (byte)0);
/* 24 */       curClass.addRelationAsDescendent(rel);
/* 25 */       mmmc.addRelationAsAncestor(rel);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract void setInheritance(Class paramClass, DataAbstraction paramDataAbstraction);
/*    */   
/*    */   public void leaveModelComponent(ProgramElement pe) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\InheritanceSpecificationListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */