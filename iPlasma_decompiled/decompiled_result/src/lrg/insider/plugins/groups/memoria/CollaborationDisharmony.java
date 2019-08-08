/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.groups.memoria.CollaborationDisharmony;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CollaborationDisharmony
/*    */   extends GroupBuilder
/*    */ {
/*    */   private GroupEntity heavyCoupledMethods;
/*    */   private GroupEntity classesOfHeavyCoupledMth;
/*    */   
/* 24 */   public CollaborationDisharmony() { super("Collaboration Disharmony", "", "system"); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface theSystem) {
/* 32 */     FilteringRule extDup = new FilteringRule("EDUPCLS", ">", "class", 0.0D);
/* 33 */     this.heavyCoupledMethods = theSystem.contains("method group").applyFilter("model function").applyFilter("Intensive Coupling");
/* 34 */     this.heavyCoupledMethods = this.heavyCoupledMethods.union(theSystem.contains("method group").applyFilter("model function").applyFilter("Extensive Coupling"));
/* 35 */     this.heavyCoupledMethods = this.heavyCoupledMethods.union(theSystem.contains("method group").applyFilter("model function").applyFilter("Shotgun Surgery"));
/*    */     
/* 37 */     this.classesOfHeavyCoupledMth = ((GroupEntity)this.heavyCoupledMethods.belongsTo("class")).distinct();
/* 38 */     this.classesOfHeavyCoupledMth = this.classesOfHeavyCoupledMth.union(theSystem.contains("class group").applyFilter(extDup)).distinct();
/*    */     
/* 40 */     annotateClassWithOtherFlaws();
/*    */     
/* 42 */     for (Iterator it = this.classesOfHeavyCoupledMth.iterator(); it.hasNext(); ) {
/* 43 */       AbstractEntity crtClass = (AbstractEntity)it.next();
/* 44 */       printClass(crtClass);
/*    */     } 
/*    */ 
/*    */     
/* 48 */     return this.classesOfHeavyCoupledMth.getElements();
/*    */   }
/*    */   
/*    */   private void printClass(AbstractEntity crtClass) {
/* 52 */     double ic = ((Double)crtClass.getProperty("NrIC").getValue()).doubleValue();
/* 53 */     double ec = ((Double)crtClass.getProperty("NrEC").getValue()).doubleValue();
/* 54 */     double ss = ((Double)crtClass.getProperty("NrSS").getValue()).doubleValue();
/* 55 */     double nom = ((Double)crtClass.getProperty("NOM").getValue()).doubleValue();
/*    */ 
/*    */     
/* 58 */     String toString = String.valueOf(crtClass.getName()) + "\t" + ic + "\t" + ec + "\t" + 
/* 59 */       ss + "\t" + nom + "\t" + "\t" + "\t" + crtClass.annotationsToString();
/*    */     
/* 61 */     System.out.println(toString);
/*    */   }
/*    */   
/*    */   private void annotateClassWithOtherFlaws() {
/* 65 */     this.classesOfHeavyCoupledMth.applyFilter("God Class").putAnnotation("GC", "God Class");
/* 66 */     this.classesOfHeavyCoupledMth.applyFilter("Brain Class").putAnnotation("BC", "Brain Class");
/* 67 */     this.classesOfHeavyCoupledMth.applyFilter("Data Class").putAnnotation("DC", "Data Class");
/* 68 */     this.classesOfHeavyCoupledMth.applyFilter("Tradition Breaker").putAnnotation("TB", "Tradition Breaker");
/* 69 */     this.classesOfHeavyCoupledMth.applyFilter("Refused Parent Bequest").putAnnotation("RPB", "Refused Parent Bequest");
/*    */     
/* 71 */     annotateWithMethodFlaw("NrBM", "Brain Method", "GM");
/* 72 */     annotateWithMethodFlaw("NrFE", "Feature Envy", "FE");
/*    */   }
/*    */ 
/*    */   
/*    */   private void annotateWithMethodFlaw(String metricName, String designFlaw, String annotationName) {
/* 77 */     FilteringRule aFilter = new FilteringRule(metricName, ">", "class", 0.0D);
/* 78 */     GroupEntity thefilteredGroup = this.classesOfHeavyCoupledMth.applyFilter(aFilter);
/*    */ 
/*    */     
/* 81 */     for (Iterator it = thefilteredGroup.iterator(); it.hasNext(); ) {
/* 82 */       AbstractEntity crtClass = (AbstractEntity)it.next();
/* 83 */       int metricValue = ((Double)crtClass.getProperty(metricName).getValue()).intValue();
/* 84 */       crtClass.putAnnotation(String.valueOf(annotationName) + metricValue, designFlaw);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\CollaborationDisharmony.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */