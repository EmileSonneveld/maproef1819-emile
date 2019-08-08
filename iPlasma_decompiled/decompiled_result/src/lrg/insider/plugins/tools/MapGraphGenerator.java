/*     */ package classes.lrg.insider.plugins.tools;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*     */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*     */ import lrg.insider.plugins.filters.memoria.classes.IsInner;
/*     */ import lrg.insider.plugins.tools.MapGraphGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapGraphGenerator
/*     */   extends AbstractEntityTool
/*     */ {
/*  27 */   public MapGraphGenerator() { super("MapGraphGenerator", "MapGraphGenerator", "system"); }
/*     */ 
/*     */   
/*     */   public void run(AbstractEntityInterface anEntity, Object theToolParameters) {
/*  31 */     ArrayList<String> params = (ArrayList)theToolParameters;
/*  32 */     if (((String)params.get(0)).equals("")) {
/*  33 */       System.out.println(buildMapGraph(anEntity));
/*     */     } else {
/*     */       try {
/*  36 */         PrintStream out_stream = new PrintStream(new FileOutputStream((String)params.get(0)));
/*  37 */         out_stream.print(buildMapGraph(anEntity));
/*  38 */         out_stream.close();
/*  39 */       } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private GroupEntity typesOfAttributesAndParameters(AbstractEntityInterface crtClass) {
/*  45 */     GroupEntity typesOfAttributes = crtClass.getGroup("attribute group").getGroup("type of variable");
/*  46 */     GroupEntity typesOfParameters = crtClass.getGroup("parameter group").getGroup("type of variable");
/*  47 */     GroupEntity typesOfLocalVariables = crtClass.getGroup("local variable group").getGroup("type of variable");
/*     */     
/*  49 */     GroupEntity modelAncestors = crtClass.getGroup("all ancestors");
/*  50 */     return typesOfAttributes.union(typesOfParameters).union(typesOfLocalVariables).union(modelAncestors).distinct()
/*  51 */       .applyFilter("model class").exclude((AbstractEntity)crtClass);
/*     */   }
/*     */ 
/*     */   
/*     */   private GroupEntity usedClasses(AbstractEntityInterface crtClass) {
/*  56 */     GroupEntity calledClasses = (GroupEntity)crtClass.getGroup("operations called").distinct().belongsTo("class");
/*  57 */     GroupEntity accessedClasses = (GroupEntity)crtClass.getGroup("variables accessed").distinct().belongsTo("class");
/*  58 */     GroupEntity methodsOverriden = (GroupEntity)crtClass.getGroup("methods overriden").distinct().belongsTo("class");
/*     */ 
/*     */ 
/*     */     
/*  62 */     return accessedClasses.applyFilter("model class");
/*     */   }
/*     */   
/*     */   private int howManyDependencies(AbstractEntity declaredClass, GroupEntity usedClasses) {
/*  66 */     int cnt = 0;
/*  67 */     for (Object crt : usedClasses.getElements()) {
/*  68 */       if (declaredClass == (AbstractEntity)crt) cnt++; 
/*     */     } 
/*  70 */     return cnt;
/*     */   }
/*     */   
/*     */   private String buildMapGraph(AbstractEntityInterface theSystem) {
/*  74 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsInner());
/*  75 */     GroupEntity allModelClasses = theSystem.getGroup("class group").applyFilter("model class").applyFilter(notComposedFilteringRule);
/*  76 */     HashMap resultsTable = new HashMap();
/*  77 */     int cnt = 0;
/*     */     
/*  79 */     for (Object crt : allModelClasses.getElements()) {
/*  80 */       AbstractEntityInterface crtClass = (AbstractEntityInterface)crt;
/*     */       
/*  82 */       GroupEntity modelTypesOfAttributesAndParameters = typesOfAttributesAndParameters(crtClass);
/*  83 */       modelTypesOfAttributesAndParameters = modelTypesOfAttributesAndParameters.applyFilter("model class");
/*  84 */       for (Object crt2 : modelTypesOfAttributesAndParameters.getElements()) {
/*  85 */         AbstractEntity secondClass = (AbstractEntity)crt2;
/*  86 */         int resultValue = howManyDependencies(secondClass, usedClasses(crtClass));
/*  87 */         if (resultValue == 0)
/*  88 */           continue;  Object partialResult = resultsTable.get(buildKeyName(secondClass, (AbstractEntity)crtClass));
/*  89 */         if (partialResult == null) {
/*  90 */           System.out.println("@@@@ " + resultValue + " " + buildKeyName(crtClass, secondClass));
/*  91 */           resultsTable.put(buildKeyName(crtClass, secondClass), new Integer(resultValue));
/*     */           continue;
/*     */         } 
/*  94 */         resultValue += ((Integer)partialResult).intValue();
/*  95 */         System.out.println("#### " + resultValue + " " + buildKeyName(secondClass, (AbstractEntity)crtClass));
/*  96 */         resultsTable.put(buildKeyName(secondClass, (AbstractEntity)crtClass), new Integer(resultValue));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 101 */     return printGraph(allModelClasses, resultsTable);
/*     */   }
/*     */ 
/*     */   
/*     */   private String buildKeyName(AbstractEntityInterface crtClass, AbstractEntity secondClass) {
/* 106 */     return String.valueOf(crtClass.getName()) + "\t" + crtClass.belongsTo("package").getName() + "\t" + 
/* 107 */       secondClass.getName() + "\t" + secondClass.belongsTo("package").getName();
/*     */   }
/*     */   
/*     */   private String printGraph(GroupEntity allModelClasses, HashMap resultsTable) {
/* 111 */     String resultString = "NODES\t" + allModelClasses.size() + "\n";
/* 112 */     DecimalFormat twoDecimals = new DecimalFormat("#0.00");
/*     */     
/* 114 */     for (Object crt : allModelClasses.getElements()) {
/* 115 */       AbstractEntityInterface crtClass = (AbstractEntityInterface)crt;
/* 116 */       double pr = ((Double)crtClass.getProperty("WMC").getValue()).doubleValue();
/* 117 */       resultString = String.valueOf(resultString) + crtClass.getName() + "\t" + crtClass.belongsTo("package").getName() + "\t" + twoDecimals.format(pr) + "\n";
/*     */     } 
/* 119 */     resultString = String.valueOf(resultString) + "EDGES\t" + resultsTable.keySet().size() + "\n";
/* 120 */     for (Object key : resultsTable.keySet()) {
/* 121 */       resultString = String.valueOf(resultString) + key.toString() + "\t" + resultsTable.get(key).toString() + "\n";
/*     */     }
/* 123 */     return resultString;
/*     */   }
/*     */ 
/*     */   
/* 127 */   public String getToolName() { return "MapGraphGenerator"; }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getParameterList() {
/* 131 */     ArrayList<String> parList = new ArrayList<String>();
/* 132 */     parList.add("Filename");
/* 133 */     return parList;
/*     */   }
/*     */   
/*     */   public ArrayList<String> getParameterExplanations() {
/* 137 */     ArrayList<String> parList = new ArrayList<String>();
/* 138 */     parList.add("Filename");
/* 139 */     return parList;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\MapGraphGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */