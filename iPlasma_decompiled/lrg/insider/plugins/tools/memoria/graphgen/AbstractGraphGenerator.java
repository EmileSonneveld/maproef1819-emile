/*     */ package classes.lrg.insider.plugins.tools.memoria.graphgen;
/*     */ 
/*     */ import java.io.FileWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*     */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*     */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.AbstractGraphGenerator;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.Component;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.Composite;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.rules.AbstractGraphBuildRule;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.rules.StrategyDefinition;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.FactoryOfInterestingStuff;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.InterestingMembersProvider;
/*     */ import lrg.memoria.core.DataAbstraction;
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
/*     */ public abstract class AbstractGraphGenerator
/*     */   extends AbstractEntityTool
/*     */ {
/*     */   private ArrayList<AbstractGraphBuildRule> buildRules;
/*     */   
/*     */   protected abstract void defineBuildStrategies(Object paramObject);
/*     */   
/*  63 */   protected String toolParameter0Name() { return "String contained by type: "; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   protected String toolParameter0Default() { return "Socket"; }
/*     */ 
/*     */ 
/*     */   
/*  72 */   protected String toolParameter0Description() { return "The string to look for when considering the root field/class types"; }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractGraphGenerator(String name, String description, String entity) {
/*  77 */     super(name, description, entity);
/*  78 */     this.buildRules = new ArrayList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  83 */   protected void addRule(AbstractGraphBuildRule rule) { this.buildRules.add(rule); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getParameterExplanations() {
/*  89 */     ArrayList<String> list = new ArrayList<String>();
/*     */     
/*  91 */     list.add(toolParameter0Description());
/*  92 */     list.add("The folder where the files will be saved");
/*  93 */     list.add("The maximum depth of the call chain");
/*  94 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getParameterInitialValue() {
/* 100 */     ArrayList<String> list = new ArrayList<String>();
/*     */     
/* 102 */     list.add(toolParameter0Default());
/* 103 */     list.add("d:/prj/graphs");
/* 104 */     list.add("7");
/* 105 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getParameterList() {
/* 111 */     ArrayList<String> list = new ArrayList<String>();
/*     */     
/* 113 */     list.add(toolParameter0Name());
/* 114 */     list.add("Directory to save files: ");
/* 115 */     list.add("Maximum call depth: ");
/* 116 */     return list;
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
/* 127 */   public String getToolName() { return "GraphGenerator"; }
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeDotHeaders(String graphName) {
/* 132 */     String text = "// Generated at " + (new GregorianCalendar()).getTime().toString() + " by GraphGenerator, \n// (c) Dan Cosma & LRG, 2006\n\n";
/* 133 */     text = String.valueOf(text) + "digraph " + graphName + "_graph\n{\n";
/* 134 */     text = String.valueOf(text) + "node [color=green];\n";
/*     */     
/* 136 */     for (int i = 0; i < this.buildRules.size(); i++) {
/* 137 */       ((AbstractGraphBuildRule)this.buildRules.get(i)).writeToFile(text);
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeDotFooters() {
/* 142 */     for (int i = 0; i < this.buildRules.size(); i++) {
/* 143 */       ((AbstractGraphBuildRule)this.buildRules.get(i)).writeToFile("}\n");
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
/*     */   
/*     */   protected abstract GroupEntity getGroupOfRootClasses(AbstractEntityInterface paramAbstractEntityInterface, String paramString);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract boolean useCommonFiles();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run(AbstractEntityInterface anEntity, Object theToolParameters) {
/*     */     byte b;
/* 178 */     defineBuildStrategies(theToolParameters);
/*     */     
/* 180 */     if (!(anEntity instanceof lrg.memoria.core.Class) && !(anEntity instanceof lrg.memoria.core.System))
/*     */       return; 
/* 182 */     ArrayList<String> params = (ArrayList)theToolParameters;
/*     */     
/* 184 */     String type = (String)getParameterInitialValue().get(0), dir = (String)getParameterInitialValue().get(1);
/*     */     
/*     */     try {
/* 187 */       b = Integer.parseInt((String)getParameterInitialValue().get(2));
/* 188 */     } catch (NumberFormatException e) {
/* 189 */       b = 7;
/*     */     } 
/*     */     
/* 192 */     if (params.size() > 0)
/* 193 */       type = (String)params.get(0); 
/* 194 */     if (params.size() > 1)
/* 195 */       dir = (String)params.get(1); 
/* 196 */     if (params.size() > 2) {
/*     */       try {
/* 198 */         b = Integer.parseInt((String)params.get(2));
/* 199 */       } catch (NumberFormatException e) {
/* 200 */         b = 7;
/*     */       } 
/*     */     }
/* 203 */     GroupEntity group = getGroupOfRootClasses(anEntity, type);
/*     */     
/* 205 */     boolean commonFiles = useCommonFiles();
/*     */     
/* 207 */     if (commonFiles) {
/* 208 */       createGlobalFiles(dir);
/*     */     }
/* 210 */     for (int i = 0; i < group.size(); i++) {
/*     */       
/* 212 */       DataAbstraction rootClass = (DataAbstraction)group.getElementAt(i);
/*     */       
/* 214 */       if (!commonFiles) {
/* 215 */         createLocalFiles(dir, rootClass);
/*     */       }
/* 217 */       generateDotFiles(rootClass, type, dir, b);
/*     */       
/* 219 */       if (!commonFiles) {
/* 220 */         closeFiles();
/*     */       }
/*     */     } 
/* 223 */     if (commonFiles) {
/* 224 */       closeFiles();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void closeFiles() {
/* 231 */     writeDotFooters();
/* 232 */     for (int i = 0; i < this.buildRules.size(); i++) {
/* 233 */       ((AbstractGraphBuildRule)this.buildRules.get(i)).closeFile();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void createLocalFiles(String dir, DataAbstraction rootClass) {
/* 240 */     String rootClassName = rootClass.getName();
/* 241 */     for (int i = 0; i < this.buildRules.size(); i++) {
/* 242 */       ((AbstractGraphBuildRule)this.buildRules.get(i)).createFile(dir, rootClassName);
/*     */     }
/* 244 */     writeDotHeaders(rootClassName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void createGlobalFiles(String dir) {
/* 250 */     for (int i = 0; i < this.buildRules.size(); i++) {
/* 251 */       ((AbstractGraphBuildRule)this.buildRules.get(i)).createFile(dir, "global");
/*     */     }
/* 253 */     writeDotHeaders("global");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void generateDotFiles(DataAbstraction rootClass, String type, String dir, int maxDepth) {
/* 259 */     String rootClassName = rootClass.getName();
/* 260 */     for (int i = 0; i < this.buildRules.size(); i++) {
/* 261 */       ((AbstractGraphBuildRule)this.buildRules.get(i)).writeToFile(String.valueOf(rootClassName) + " [color=red];\n");
/*     */     }
/* 263 */     for (int i = 0; i < this.buildRules.size(); i++) {
/*     */       
/* 265 */       AbstractGraphBuildRule rule = (AbstractGraphBuildRule)this.buildRules.get(i);
/* 266 */       ArrayList<StrategyDefinition> strategies = rule.getStrategies();
/* 267 */       for (int j = 0; j < strategies.size(); j++) {
/*     */         
/* 269 */         StrategyDefinition strategy = (StrategyDefinition)strategies.get(j);
/* 270 */         buildDependencyTree(rootClass, null, 0, maxDepth, 
/* 271 */             strategy.getInitialStrategy().getName(), strategy.getInitialStrategyParameter(), 
/* 272 */             strategy.getLevelTwoStrategy().getName(), 
/* 273 */             rule.getWriter(), strategy.getEdgeColor(), strategy.isEdgeDirectedOutward());
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
/*     */   private Component buildDependencyTree(AbstractEntityInterface theClass, GroupEntity previouslyInterestingMethods, int crtDepth, int maxDepth, String initialStrategyClass, String initialStrategyParameters, String levelTwoStrategyHelperClass, FileWriter writer, String edgeColor, boolean edgeDirection) {
/* 286 */     if (crtDepth == maxDepth) {
/* 287 */       return new Component(theClass, crtDepth);
/*     */     }
/*     */     
/* 290 */     InterestingMembersProvider provider = null;
/* 291 */     if (crtDepth == 0) {
/* 292 */       if (initialStrategyParameters == null) {
/* 293 */         provider = FactoryOfInterestingStuff.create(initialStrategyClass, theClass);
/*     */       } else {
/* 295 */         provider = FactoryOfInterestingStuff.create(initialStrategyClass, theClass, initialStrategyParameters);
/*     */       } 
/*     */     } else {
/* 298 */       provider = FactoryOfInterestingStuff.create(levelTwoStrategyHelperClass, previouslyInterestingMethods.intersect(theClass.contains("method group")));
/*     */     } 
/*     */     
/* 301 */     GroupEntity interestingMethods = provider.methodRelated();
/* 302 */     GroupEntity classesWithInterestingMethods = ((GroupEntity)interestingMethods.belongsTo("class")).applyFilter("model class");
/*     */     
/* 304 */     GroupEntity methodsAccessingMyAttributes = provider.attributeRelated();
/* 305 */     GroupEntity classesAccessingMyAttributes = ((GroupEntity)methodsAccessingMyAttributes.belongsTo("class")).applyFilter("model class");
/* 306 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new FilteringRule("is inner", "is true", "class"));
/*     */     
/* 308 */     GroupEntity classesRelatedToMe = classesWithInterestingMethods.union(classesAccessingMyAttributes).distinct().exclude((AbstractEntity)theClass).applyFilter(notComposedFilteringRule);
/*     */ 
/*     */     
/* 311 */     if (classesRelatedToMe.size() == 0) return new Component(theClass, crtDepth);
/*     */     
/* 313 */     Iterator it = classesRelatedToMe.getElements().iterator();
/* 314 */     Composite composedComponent = new Composite(theClass, crtDepth);
/*     */     
/* 316 */     while (it.hasNext()) {
/* 317 */       AbstractEntityInterface crtClass = (AbstractEntityInterface)it.next();
/* 318 */       composedComponent.addComponentAndExportDot(
/* 319 */           buildDependencyTree(crtClass, interestingMethods, 
/* 320 */             crtDepth + 1, maxDepth, 
/* 321 */             initialStrategyClass, initialStrategyParameters, 
/* 322 */             levelTwoStrategyHelperClass, 
/* 323 */             writer, edgeColor, edgeDirection), 
/* 324 */           writer, edgeColor, edgeDirection);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 330 */     return composedComponent;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\AbstractGraphGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */