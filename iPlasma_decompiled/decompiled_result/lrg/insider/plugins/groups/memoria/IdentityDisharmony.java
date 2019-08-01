/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.groups.memoria.IdentityDisharmony;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IdentityDisharmony
/*    */   extends GroupBuilder
/*    */ {
/*    */   private GroupEntity brainClasses;
/*    */   
/* 23 */   public IdentityDisharmony() { super("Identity Disharmony", "", "system"); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface theSystem) {
/* 29 */     String header = "Name\tnrCriticMeth\tnrBM\tIDUPLINES\tATFD\tnrFE\tAnnotations";
/*    */ 
/*    */     
/* 32 */     this.brainClasses = theSystem.contains("class group").applyFilter("model class").applyFilter("God Class");
/*    */     
/* 34 */     annotateClassWithOtherFlaws();
/*    */     
/* 36 */     System.out.println("GOD CLASSES");
/* 37 */     System.out.println("===========\n" + header);
/*    */     
/* 39 */     for (Iterator it = this.brainClasses.iterator(); it.hasNext(); ) {
/* 40 */       AbstractEntity crtClass = (AbstractEntity)it.next();
/* 41 */       printClass(crtClass);
/*    */     } 
/*    */     
/* 44 */     GroupEntity tmp = this.brainClasses;
/*    */     
/* 46 */     this.brainClasses = theSystem.contains("class group").applyFilter("model class").applyFilter("Brain Class");
/* 47 */     annotateClassWithOtherFlaws();
/*    */     
/* 49 */     System.out.println("BRAIN CLASSES");
/* 50 */     System.out.println("=============\n" + header);
/* 51 */     for (Iterator it = this.brainClasses.iterator(); it.hasNext(); ) {
/* 52 */       AbstractEntity crtClass = (AbstractEntity)it.next();
/* 53 */       printClass(crtClass);
/*    */     } 
/*    */ 
/*    */     
/* 57 */     return this.brainClasses.union(tmp).getElements();
/*    */   }
/*    */ 
/*    */   
/*    */   private void annotateClassWithOtherFlaws() {
/* 62 */     this.brainClasses.applyFilter("Tradition Breaker").putAnnotation("TB", "Tradition Breaker");
/* 63 */     this.brainClasses.applyFilter("Refused Parent Bequest").putAnnotation("RPB", "Refused Parent Bequest");
/*    */     
/* 65 */     annotateWithMethodFlaw("NrIC", "Intensive Coupling", "IC");
/* 66 */     annotateWithMethodFlaw("NrEC", "Extensive Coupling", "EC");
/* 67 */     annotateWithMethodFlaw("NrSS", "Shotgun Surgery", "SS");
/* 68 */     annotateWithMethodFlaw("EDUPLCLS", "External Duplication", "EDUP");
/* 69 */     annotateWithMethodFlaw("HDUPCLS", "Hierarchy Duplication", "HDUP");
/*    */   }
/*    */ 
/*    */   
/*    */   private void annotateWithMethodFlaw(String metricName, String designFlaw, String annotationName) {
/* 74 */     FilteringRule aFilter = new FilteringRule(metricName, ">", "class", 0.0D);
/* 75 */     GroupEntity thefilteredGroup = this.brainClasses.applyFilter(aFilter);
/*    */ 
/*    */     
/* 78 */     for (Iterator it = thefilteredGroup.iterator(); it.hasNext(); ) {
/* 79 */       AbstractEntity crtClass = (AbstractEntity)it.next();
/* 80 */       int metricValue = ((Double)crtClass.getProperty(metricName).getValue()).intValue();
/* 81 */       crtClass.putAnnotation(String.valueOf(annotationName) + metricValue, designFlaw);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void printClass(AbstractEntity crtClass) {
/* 87 */     double nrCriticalMethods = crtClass.getGroup("Identity Harmony (critical methods)").size();
/* 88 */     double duplication = ((Double)crtClass.getProperty("IDUPLINES").getValue()).doubleValue();
/* 89 */     double nrGM = ((Double)crtClass.getProperty("NrBM").getValue()).doubleValue();
/* 90 */     double nrFE = ((Double)crtClass.getProperty("NrFE").getValue()).doubleValue();
/* 91 */     double atfd = ((Double)crtClass.getProperty("ATFD").getValue()).doubleValue();
/* 92 */     double nom = ((Double)crtClass.getProperty("NOM").getValue()).doubleValue();
/* 93 */     double wmc = ((Double)crtClass.getProperty("WMC").getValue()).doubleValue();
/* 94 */     double tcc = ((Double)crtClass.getProperty("TCC").getValue()).doubleValue();
/*    */     
/* 96 */     String toPrint = String.valueOf(crtClass.getName()) + "\t" + nrCriticalMethods + "\t" + nrGM + "\t" + 
/* 97 */       duplication + "\t" + atfd + "\t" + nrFE + "\t" + crtClass.annotationsToString() + 
/* 98 */       "\t" + nom + "\t" + wmc + "\t" + tcc;
/* 99 */     System.out.println(toPrint);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\IdentityDisharmony.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */