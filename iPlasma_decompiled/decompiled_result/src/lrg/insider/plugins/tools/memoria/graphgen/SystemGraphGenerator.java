/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.AbstractGraphGenerator;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.SystemGraphGenerator;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.AllBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.BalancedFieldTypeBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.FieldTypeBuildRule;
/*    */ import lrg.memoria.core.System;
/*    */ 
/*    */ public class SystemGraphGenerator
/*    */   extends AbstractGraphGenerator {
/* 16 */   public SystemGraphGenerator() { super("GraphGenerator", "Creates class dependency graphs as GraphViz dot format files", "system"); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public SystemGraphGenerator(String name, String description, String entity) { super(name, description, entity); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected GroupEntity getGroupOfRootClasses(AbstractEntityInterface entity, String attributeType) {
/* 28 */     System system = (System)entity;
/*    */     
/* 30 */     FilteringRule hasTypeName = new FilteringRule("Type", "contain", "attribute", attributeType);
/* 31 */     return system.contains("class group").applyFilter("model class").applyFilter(hasTypeName);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   protected boolean useCommonFiles() { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void defineBuildStrategies(Object toolParameters) {
/* 43 */     ArrayList<String> params = (ArrayList)toolParameters;
/*    */     
/* 45 */     addRule(new AllBuildRule());
/* 46 */     addRule(new FieldTypeBuildRule((String)params.get(0)));
/* 47 */     addRule(new BalancedFieldTypeBuildRule((String)params.get(0)));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\SystemGraphGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */