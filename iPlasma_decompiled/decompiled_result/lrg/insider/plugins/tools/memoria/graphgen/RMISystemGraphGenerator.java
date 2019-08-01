/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.AbstractGraphGenerator;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.RMISystemGraphGenerator;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.RMIBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.RMICalledHierarcyRule;
/*    */ import lrg.memoria.core.System;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RMISystemGraphGenerator
/*    */   extends AbstractGraphGenerator
/*    */ {
/* 18 */   public RMISystemGraphGenerator() { super("RMIGraphGenerator", "Creates class dependency graphs as Graphviz dot format files", "system"); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public RMISystemGraphGenerator(String name, String description, String entity) { super(name, description, entity); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected GroupEntity getGroupOfRootClasses(AbstractEntityInterface entity, String ancestorName) {
/* 30 */     System system = (System)entity;
/*    */     
/* 32 */     FilteringRule derivedOf = new FilteringRule("ancestor name", "==", "class", ancestorName);
/* 33 */     return system.contains("class group").applyFilter("model class").applyFilter(derivedOf);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   protected boolean useCommonFiles() { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void defineBuildStrategies(Object toolParameters) {
/* 45 */     addRule(new RMIBuildRule());
/* 46 */     addRule(new RMICalledHierarcyRule());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 51 */   protected String toolParameter0Name() { return "Name of the ancestor: "; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   protected String toolParameter0Default() { return "Remote"; }
/*    */ 
/*    */ 
/*    */   
/* 60 */   protected String toolParameter0Description() { return "The exact name of the ancestor class or interface"; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\RMISystemGraphGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */