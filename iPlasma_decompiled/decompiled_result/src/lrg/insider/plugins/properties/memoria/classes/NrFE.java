/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NrFE
/*    */   extends PropertyComputer
/*    */ {
/* 17 */   public NrFE() { super("NrFE", "Number of FeatureEnvy Methods", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface measuredClass) {
/* 21 */     GroupEntity aGroup = measuredClass.getGroup("method group");
/*    */     
/* 23 */     return new ResultEntity(aGroup.applyFilter("Feature Envy").size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NrFE.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */