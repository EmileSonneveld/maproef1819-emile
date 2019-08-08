/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class NOD
/*    */   extends PropertyComputer {
/*  9 */   public NOD() { super("NOD", "Number of Descendants", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/* 13 */   public ResultEntity compute(AbstractEntityInterface measuredClass) { return new ResultEntity(measuredClass.getGroup("all descendants").size()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NOD.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */