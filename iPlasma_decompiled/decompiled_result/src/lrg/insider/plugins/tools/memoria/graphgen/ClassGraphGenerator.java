/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.AbstractGraphGenerator;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.ClassGraphGenerator;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.AllBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.BalancedFieldTypeBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.FieldTypeBuildRule;
/*    */ 
/*    */ public class ClassGraphGenerator
/*    */   extends AbstractGraphGenerator
/*    */ {
/* 15 */   public ClassGraphGenerator() { super("GraphGenerator", "Creates class dependency graphs as GraphViz dot format files", "class"); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected GroupEntity getGroupOfRootClasses(AbstractEntityInterface entity, String attributeType) {
/* 23 */     ArrayList<AbstractEntityInterface> list = new ArrayList<AbstractEntityInterface>();
/* 24 */     list.add(entity);
/* 25 */     return new GroupEntity("oneclass group", list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   protected boolean useCommonFiles() { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void defineBuildStrategies(Object toolParameters) {
/* 37 */     ArrayList<String> params = (ArrayList)toolParameters;
/*    */     
/* 39 */     addRule(new AllBuildRule());
/* 40 */     addRule(new FieldTypeBuildRule((String)params.get(0)));
/* 41 */     addRule(new BalancedFieldTypeBuildRule((String)params.get(0)));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\ClassGraphGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */