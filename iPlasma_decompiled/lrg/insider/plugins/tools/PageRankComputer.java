/*     */ package classes.lrg.insider.plugins.tools;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.ResultEntity;
/*     */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*     */ import lrg.insider.plugins.tools.AbstractCAListBuilder;
/*     */ import lrg.insider.plugins.tools.CALisIncludeInheritance;
/*     */ import lrg.insider.plugins.tools.ComposedCAListBuilder;
/*     */ import lrg.insider.plugins.tools.PRStructure;
/*     */ import lrg.insider.plugins.tools.PageRankComputer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PageRankComputer
/*     */   extends AbstractEntityTool
/*     */ {
/*  68 */   private static int NO_ITERATIONS = 40;
/*  69 */   private static double DAMPING_FACTOR = 0.85D;
/*     */   
/*     */   private AbstractCAListBuilder caListBuilder;
/*     */   private HashMap<AbstractEntityInterface, PRStructure> resultsTable;
/*     */   
/*     */   private ArrayList<AbstractCAListBuilder> prepareCollection() {
/*  75 */     ArrayList<AbstractCAListBuilder> theCollection = new ArrayList<AbstractCAListBuilder>();
/*     */     
/*  77 */     theCollection.add(new CALisIncludeInheritance());
/*     */ 
/*     */     
/*  80 */     return theCollection;
/*     */   }
/*     */   
/*     */   public PageRankComputer() {
/*  84 */     super("PageRankComputer", "PageRankComputer", "system");
/*  85 */     this.resultsTable = new HashMap();
/*  86 */     this.caListBuilder = new ComposedCAListBuilder(prepareCollection());
/*     */   }
/*     */   
/*     */   public void run(AbstractEntityInterface anEntity, Object theToolParameters) {
/*     */     try {
/*  91 */       PrintStream out_stream = new PrintStream(new FileOutputStream("D:\\temp\\" + anEntity.getName() + "-pagerank_" + NO_ITERATIONS + ".txt"));
/*  92 */       out_stream.print(computePageRank(anEntity));
/*  93 */       out_stream.close();
/*  94 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String computePageRank(AbstractEntityInterface theSystem) {
/* 109 */     ArrayList<AbstractEntityInterface> allModelClasses = getAllModelClasses(theSystem);
/* 110 */     initPageRank(allModelClasses);
/* 111 */     for (int i = 0; i < NO_ITERATIONS; i++) {
/* 112 */       computeIteration(allModelClasses);
/* 113 */       updatePRForAllClasses(allModelClasses);
/*     */     } 
/*     */     
/* 116 */     return printPRTable();
/*     */   }
/*     */ 
/*     */   
/* 120 */   private ArrayList<AbstractEntityInterface> getAllModelClasses(AbstractEntityInterface theSystem) { return theSystem.getGroup("class group").applyFilter("model class").getElements(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initPageRank(ArrayList<AbstractEntityInterface> allModelClasses) {
/* 127 */     for (AbstractEntityInterface crtClass : allModelClasses)
/*     */     {
/* 129 */       this.resultsTable.put(crtClass, new PRStructure(crtClass, this.caListBuilder.buildCAListFor(crtClass), 0.0D));
/*     */     }
/*     */     
/* 132 */     for (AbstractEntityInterface crtClass : this.resultsTable.keySet()) {
/* 133 */       PRStructure crtPRStructure = (PRStructure)this.resultsTable.get(crtClass);
/* 134 */       for (AbstractEntityInterface caClass : crtPRStructure.CAList) {
/* 135 */         PRStructure linkedPRStructure = (PRStructure)this.resultsTable.get(caClass);
/* 136 */         if (linkedPRStructure != null) {
/* 137 */           linkedPRStructure.TList.add(crtPRStructure);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String printPRTable() {
/* 152 */     String resultString = "";
/* 153 */     DecimalFormat twoDecimals = new DecimalFormat("#0.00");
/* 154 */     for (AbstractEntityInterface crtClass : this.resultsTable.keySet()) {
/* 155 */       PRStructure crtPRStructure = (PRStructure)this.resultsTable.get(crtClass);
/* 156 */       crtClass.addProperty("#PAGERANK#", new ResultEntity(crtPRStructure));
/* 157 */       resultString = String.valueOf(resultString) + crtClass.getName() + "\t" + twoDecimals.format(crtPRStructure.getPR()) + "\n";
/*     */     } 
/* 159 */     return resultString;
/*     */   }
/*     */ 
/*     */   
/*     */   private void computeIteration(ArrayList<AbstractEntityInterface> allModelClasses) {
/* 164 */     for (AbstractEntityInterface crtClass : allModelClasses) {
/* 165 */       PRStructure crtPRStruct = (PRStructure)this.resultsTable.get(crtClass);
/* 166 */       PRStructure newPRStruct = computeNewPRValue(crtPRStruct);
/*     */       
/* 168 */       this.resultsTable.put(crtClass, newPRStruct);
/*     */     } 
/*     */   }
/*     */   
/*     */   private PRStructure computeNewPRValue(PRStructure crtPRStructure) {
/* 173 */     double newPR = 1.0D - DAMPING_FACTOR;
/*     */     
/* 175 */     for (PRStructure crtTLink : crtPRStructure.TList) {
/* 176 */       if (crtTLink.CA != 0.0D)
/* 177 */         newPR += DAMPING_FACTOR * crtTLink.getPR() / crtTLink.CA; 
/*     */     } 
/* 179 */     return new PRStructure(crtPRStructure, newPR);
/*     */   }
/*     */ 
/*     */   
/*     */   private void updatePRForAllClasses(ArrayList<AbstractEntityInterface> allModelClasses) {
/* 184 */     for (AbstractEntityInterface crtClass : allModelClasses) {
/* 185 */       PRStructure crtPRStructure = (PRStructure)this.resultsTable.get(crtClass);
/* 186 */       for (PRStructure crtTLink : crtPRStructure.TList) {
/* 187 */         PRStructure temp = (PRStructure)this.resultsTable.get(crtTLink.theClass);
/* 188 */         if (temp != null) crtTLink.PR = temp.getPR();
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/* 194 */   public String getToolName() { return "PageRankComputer"; }
/*     */ 
/*     */ 
/*     */   
/* 198 */   public ArrayList<String> getParameterList() { return new ArrayList<String>(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public ArrayList<String> getParameterExplanations() { return new ArrayList<String>(); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\PageRankComputer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */