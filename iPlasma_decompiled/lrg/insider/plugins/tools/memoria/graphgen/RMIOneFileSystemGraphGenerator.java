/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen;
/*    */ 
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.RMISystemGraphGenerator;
/*    */ 
/*    */ public class RMIOneFileSystemGraphGenerator
/*    */   extends RMISystemGraphGenerator {
/*  7 */   public RMIOneFileSystemGraphGenerator() { super("One-File RMIGraphGenerator", "Creates class dependency graphs as Graphviz dot format files", "system"); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 14 */   protected boolean useCommonFiles() { return true; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\RMIOneFileSystemGraphGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */