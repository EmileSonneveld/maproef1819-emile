/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.AbstractGraphGenerator;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.ClassGroupGraphGenerator;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.AllBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.BalancedFieldTypeBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.FieldTypeBuildRule;
/*    */ 
/*    */ public class ClassGroupGraphGenerator
/*    */   extends AbstractGraphGenerator
/*    */ {
/* 15 */   public ClassGroupGraphGenerator() { super("GraphGenerator", "Creates class dependency graphs as GraphViz dot format files", "class group"); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   protected GroupEntity getGroupOfRootClasses(AbstractEntityInterface entity, String attributeType) { return (GroupEntity)entity; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   protected boolean useCommonFiles() { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void defineBuildStrategies(Object toolParameters) {
/* 35 */     ArrayList<String> params = (ArrayList)toolParameters;
/*    */     
/* 37 */     addRule(new AllBuildRule());
/* 38 */     addRule(new FieldTypeBuildRule((String)params.get(0)));
/* 39 */     addRule(new BalancedFieldTypeBuildRule((String)params.get(0)));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\ClassGroupGraphGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */