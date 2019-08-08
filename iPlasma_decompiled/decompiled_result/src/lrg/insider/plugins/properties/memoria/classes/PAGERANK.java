/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.tools.PRStructure;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PAGERANK
/*    */   extends PropertyComputer
/*    */ {
/* 19 */   public PAGERANK() { super("PAGERANK", "Importance of class computed using PAGERANK", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface measuredClass) {
/* 23 */     ResultEntity aResult = measuredClass.getProperty("#PAGERANK#");
/*    */ 
/*    */     
/* 26 */     if (aResult == null) return new ResultEntity(-1.0D);
/*    */     
/* 28 */     PRStructure classessPRStruct = (PRStructure)aResult.getValue();
/* 29 */     return new ResultEntity(classessPRStruct.getPR());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\PAGERANK.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */