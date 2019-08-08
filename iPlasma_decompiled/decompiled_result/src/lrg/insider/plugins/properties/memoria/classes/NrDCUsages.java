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
/*    */ class NrDCUsages
/*    */   extends PropertyComputer
/*    */ {
/* 17 */   public NrDCUsages() { super("NrDCUsages", "Number of Usages of Data from Data Classes", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface measuredClass) {
/* 21 */     GroupEntity scopeOfForeignData = (GroupEntity)measuredClass.getGroup("foreign data").belongsTo("class");
/* 22 */     return new ResultEntity(scopeOfForeignData.applyFilter("Data Class").size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NrDCUsages.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */