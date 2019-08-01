/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class NODD
/*    */   extends PropertyComputer {
/*  9 */   public NODD() { super("NODD", "Number of Direct Descendants", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/* 13 */   public ResultEntity compute(AbstractEntityInterface measuredClass) { return new ResultEntity(measuredClass.getGroup("derived classes").size()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NODD.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */