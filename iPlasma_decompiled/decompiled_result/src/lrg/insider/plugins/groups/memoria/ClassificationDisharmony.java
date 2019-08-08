/*     */ package classes.lrg.insider.plugins.groups.memoria;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*     */ import lrg.common.abstractions.plugins.filters.composed.AndComposedFilteringRule;
/*     */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*     */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*     */ import lrg.insider.plugins.core.filters.memoria.ModelClassFilter;
/*     */ import lrg.insider.plugins.filters.memoria.classes.IsInterface;
/*     */ import lrg.insider.plugins.groups.memoria.ClassificationDisharmony;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClassificationDisharmony
/*     */   extends GroupBuilder
/*     */ {
/*     */   private GroupEntity badRoots;
/*     */   private GroupEntity flawedClasses;
/*     */   private GroupEntity modelClasses;
/*     */   
/*     */   public ClassificationDisharmony() {
/*  29 */     super("Classification Disharmony", "", "system");
/*  30 */     this.flawedClasses = new GroupEntity("classification disharmonies", new ArrayList());
/*  31 */     this.badRoots = new GroupEntity("classification disharmony roots", new ArrayList());
/*     */   }
/*     */   
/*     */   private GroupEntity getBaseClasses(AbstractEntity initialGroup) {
/*  35 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsInterface());
/*  36 */     return initialGroup.getGroup("base classes").applyFilter("model class").applyFilter(notComposedFilteringRule);
/*     */   }
/*     */   
/*     */   private void processDesignFlawInDerived(String designFlaw, String annotationName) {
/*  40 */     GroupEntity currentFlawGroup = this.modelClasses.applyFilter(designFlaw);
/*  41 */     currentFlawGroup.putAnnotation(annotationName, designFlaw);
/*     */     
/*  43 */     this.flawedClasses = this.flawedClasses.union(currentFlawGroup).distinct();
/*  44 */     this.badRoots = this.badRoots.union(getBaseClasses(currentFlawGroup)).distinct();
/*     */     
/*  46 */     System.out.println(String.valueOf(designFlaw) + " processed:" + this.badRoots.size() + " " + this.flawedClasses.size());
/*     */   }
/*     */ 
/*     */   
/*     */   private void processDesignFlawInBase(String designFlaw, String annotationName) {
/*  51 */     GroupEntity currentFlawGroup = this.modelClasses.applyFilter(designFlaw);
/*  52 */     currentFlawGroup.putAnnotation(annotationName, designFlaw);
/*     */     
/*  54 */     this.badRoots = this.badRoots.union(currentFlawGroup).distinct();
/*  55 */     this.flawedClasses = this.flawedClasses.union(currentFlawGroup.getGroup("derived classes")).distinct();
/*     */     
/*  57 */     System.out.println(String.valueOf(designFlaw) + " processed:" + this.badRoots.size() + " " + this.flawedClasses.size());
/*     */   }
/*     */   
/*     */   private void processIdentityDesignFlaw(String designFlaw, String annotationName) {
/*  61 */     AndComposedFilteringRule andComposedFilteringRule = new AndComposedFilteringRule(new ModelClassFilter(), new NotComposedFilteringRule(new IsInterface()));
/*  62 */     FilteringRule hasDescendants = new FilteringRule("NODD", ">", "class", 0.0D);
/*  63 */     GroupEntity identityFlawedClasses = this.modelClasses.applyFilter(designFlaw);
/*     */ 
/*     */     
/*  66 */     for (Iterator it = identityFlawedClasses.iterator(); it.hasNext(); ) {
/*  67 */       AbstractEntity crtBadClass = (AbstractEntity)it.next();
/*  68 */       GroupEntity modelBaseClasses = crtBadClass.getGroup("base classes").applyFilter(andComposedFilteringRule);
/*  69 */       if (modelBaseClasses.size() > 0) {
/*  70 */         crtBadClass.putAnnotation(annotationName, designFlaw);
/*  71 */         this.flawedClasses = this.flawedClasses.union(crtBadClass);
/*  72 */         this.badRoots = this.badRoots.union(modelBaseClasses);
/*     */       } 
/*  74 */       if (hasDescendants.applyFilter(crtBadClass)) {
/*  75 */         crtBadClass.putAnnotation(annotationName, designFlaw);
/*  76 */         this.flawedClasses = this.flawedClasses.union(crtBadClass.getGroup("derived classes"));
/*  77 */         this.badRoots = this.badRoots.union(crtBadClass);
/*     */       } 
/*     */     } 
/*     */     
/*  81 */     this.badRoots = this.badRoots.distinct();
/*  82 */     this.flawedClasses = this.flawedClasses.distinct();
/*     */     
/*  84 */     System.out.println(String.valueOf(designFlaw) + " processed:" + this.badRoots.size() + " " + this.flawedClasses.size());
/*     */   }
/*     */   
/*     */   private void annotateWithMethodFlaw(String metricName, String designFlaw, String annotationName) {
/*  88 */     FilteringRule aFilter = new FilteringRule(metricName, ">", "class", 0.0D);
/*  89 */     GroupEntity thefilteredGroup = this.badRoots.union(this.flawedClasses).applyFilter(aFilter);
/*     */ 
/*     */     
/*  92 */     for (Iterator it = thefilteredGroup.iterator(); it.hasNext(); ) {
/*  93 */       AbstractEntity crtClass = (AbstractEntity)it.next();
/*  94 */       int metricValue = ((Double)crtClass.getProperty(metricName).getValue()).intValue();
/*  95 */       crtClass.putAnnotation(String.valueOf(annotationName) + metricValue, designFlaw);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void annotateWithOtherFlaws() {
/* 100 */     System.out.println("Start additional annotation");
/*     */     
/* 102 */     this.badRoots.applyFilter("God Class").putAnnotation("GC", "God Class");
/* 103 */     this.flawedClasses.applyFilter("God Class").putAnnotation("GC", "God Class");
/*     */     
/* 105 */     this.badRoots.applyFilter("Brain Class").putAnnotation("BC", "Brain Class");
/* 106 */     this.flawedClasses.applyFilter("Brain Class").putAnnotation("BC", "Brain Class");
/*     */     
/* 108 */     this.badRoots.applyFilter("Data Class").putAnnotation("DC", "Data Class");
/* 109 */     this.flawedClasses.applyFilter("Data Class").putAnnotation("DC", "Data Class");
/*     */ 
/*     */     
/* 112 */     annotateWithMethodFlaw("NrBM", "Brain Method", "BM");
/* 113 */     annotateWithMethodFlaw("NrFE", "Feature Envy", "FE");
/* 114 */     annotateWithMethodFlaw("NrIC", "Intensive Coupling", "IC");
/* 115 */     annotateWithMethodFlaw("NrEC", "Extensive Coupling", "EC");
/* 116 */     annotateWithMethodFlaw("NrSS", "Shotgun Surgery", "SS");
/* 117 */     annotateWithMethodFlaw("EDUPLCLS", "External Duplication", "EDUP");
/* 118 */     annotateWithMethodFlaw("IDUPLINES", "Internal Duplication", "IDUP");
/*     */     
/* 120 */     System.out.println("Stop additional annotation " + this.badRoots.size() + " " + this.flawedClasses.size());
/*     */   }
/*     */ 
/*     */   
/*     */   private String printFlawedDescendants(AbstractEntity aRoot, int depth) {
/* 125 */     GroupEntity derivedClasses = aRoot.getGroup("derived classes").intersect(this.flawedClasses.union(this.badRoots));
/*     */     
/* 127 */     String indentation = "";
/* 128 */     for (int i = 0; i < depth; ) { indentation = String.valueOf(indentation) + "\t"; i++; }
/* 129 */      String result = "";
/*     */     
/* 131 */     for (Iterator itDerived = derivedClasses.iterator(); itDerived.hasNext(); ) {
/* 132 */       AbstractEntity crtDerived = (AbstractEntity)itDerived.next();
/* 133 */       result = String.valueOf(result) + indentation + crtDerived.getName() + "\t[" + crtDerived.annotationsToString() + "]\n";
/* 134 */       if (!isLeaf(crtDerived)) result = String.valueOf(result) + printFlawedDescendants(crtDerived, ++depth); 
/*     */     } 
/* 136 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isRoot(AbstractEntity aNode) {
/* 141 */     GroupEntity ancestors = getBaseClasses(aNode);
/* 142 */     return (ancestors.intersect(this.badRoots).size() == 0);
/*     */   }
/*     */ 
/*     */   
/* 146 */   private boolean isLeaf(AbstractEntity aNode) { return !this.badRoots.isInGroup(aNode); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList printResults() {
/* 152 */     ArrayList theRoots = new ArrayList();
/*     */     
/* 154 */     for (Iterator it = this.badRoots.iterator(); it.hasNext();)
/* 155 */       System.out.print(String.valueOf(((AbstractEntity)it.next()).getName()) + "  "); 
/* 156 */     System.out.println("\n");
/*     */     
/* 158 */     for (Iterator it = this.flawedClasses.iterator(); it.hasNext();)
/* 159 */       System.out.print(String.valueOf(((AbstractEntity)it.next()).getName()) + "  "); 
/* 160 */     System.out.println("\n");
/*     */ 
/*     */     
/* 163 */     for (Iterator it = this.badRoots.iterator(); it.hasNext(); ) {
/* 164 */       AbstractEntity crtRoot = (AbstractEntity)it.next();
/* 165 */       if (isRoot(crtRoot)) {
/* 166 */         System.out.println(String.valueOf(crtRoot.getName()) + "\t[" + crtRoot.annotationsToString() + "]");
/* 167 */         System.out.println(printFlawedDescendants(crtRoot, 1));
/* 168 */         theRoots.add(crtRoot);
/*     */       } 
/*     */     } 
/* 171 */     return theRoots;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList buildGroup(AbstractEntityInterface theSystem) {
/* 176 */     this.modelClasses = theSystem.contains("class group").applyFilter("model class");
/*     */     
/* 178 */     processIdentityDesignFlaw("God Class", "GC");
/* 179 */     processIdentityDesignFlaw("Brain Class", "BC");
/*     */     
/* 181 */     processDesignFlawInDerived("Hierarchy Duplication", "HDUP");
/* 182 */     processDesignFlawInDerived("Refused Parent Bequest", "RPB");
/* 183 */     processDesignFlawInDerived("Tradition Breaker", "TB");
/*     */     
/* 185 */     processDesignFlawInBase("Futile Hierarchy", "FH");
/*     */     
/* 187 */     processDesignFlawInBase("Futile Abstract Pipeline", "FH");
/* 188 */     processDesignFlawInDerived("Futile Abstract Pipeline", "FH");
/*     */     
/* 190 */     annotateWithOtherFlaws();
/*     */     
/* 192 */     return printResults();
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\ClassificationDisharmony.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */