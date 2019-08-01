/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.groups.memoria.ExternalServiceProviders;
/*    */ import lrg.insider.plugins.properties.memoria.methods.CEXT;
/*    */ 
/*    */ public class CEXT extends PropertyComputer {
/*    */   public CEXT() {
/* 11 */     super("CEXT", "Coupling Extent", new String[] { "method", "global function" }, "numerical");
/* 12 */     basedOnDistinctGroup(new ExternalServiceProviders());
/*    */   }
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 16 */     GroupEntity aGroup = (GroupEntity)anEntity.getGroup("External Service Providers (methods)").belongsTo("class");
/* 17 */     return new ResultEntity(aGroup.distinct().applyFilter("model class").size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\CEXT.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */