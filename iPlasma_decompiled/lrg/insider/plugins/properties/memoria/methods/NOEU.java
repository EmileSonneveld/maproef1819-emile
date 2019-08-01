/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class NOEU
/*    */   extends PropertyComputer
/*    */ {
/* 11 */   public NOEU() { super("NOEU", "Number of External Usages", "method", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface measuredMethod) {
/* 15 */     GroupEntity callers = measuredMethod.uses("operations calling me");
/*    */     
/* 17 */     int noOfInternalCallers = 
/* 18 */       callers.intersect(measuredMethod.belongsTo("class").contains("method group")).size();
/*    */     
/* 20 */     return new ResultEntity((callers.size() - noOfInternalCallers));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\NOEU.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */