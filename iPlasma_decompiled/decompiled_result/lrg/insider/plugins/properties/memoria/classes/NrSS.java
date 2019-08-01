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
/*    */ public class NrSS
/*    */   extends PropertyComputer
/*    */ {
/* 17 */   public NrSS() { super("NrSS", "Number of methods with ShotgunSurgery", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface measuredClass) {
/* 21 */     GroupEntity aGroup = measuredClass.getGroup("method group");
/*    */     
/* 23 */     return new ResultEntity(aGroup.applyFilter("Shotgun Surgery").size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NrSS.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */