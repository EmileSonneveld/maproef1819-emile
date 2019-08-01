/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class HDUPCLS
/*    */   extends PropertyComputer {
/* 10 */   public HDUPCLS() { super("HDUPCLS", "Number of Classes in Same Hierarchy containing Common Duplication", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 14 */     GroupEntity siblingClasses = (GroupEntity)anEntity.contains("method group").getGroup("same hierarchy duplicated methods").belongsTo("class");
/* 15 */     return new ResultEntity(siblingClasses.distinct().size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\HDUPCLS.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */