/*    */ package classes.lrg.insider.plugins.filters.memoria.packages;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.insider.plugins.filters.memoria.packages.GraphUtil;
/*    */ import lrg.insider.plugins.filters.memoria.packages.Node;
/*    */ 
/*    */ public class CyclicSubsystemsDependency extends FilteringRule {
/*  9 */   public CyclicSubsystemsDependency() { super(new Descriptor("Cyclic Subsystems Dependency", "package")); }
/*    */ 
/*    */ 
/*    */   
/* 13 */   public boolean applyFilter(AbstractEntityInterface anEntity) { return GraphUtil.buildGraph(anEntity, "system", "package group", "efferent coupling").belongstoCycle(new Node(anEntity)); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\packages\CyclicSubsystemsDependency.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */