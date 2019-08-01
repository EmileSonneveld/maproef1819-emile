/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.dude.duplication.DuplicationList;
/*    */ 
/*    */ public class DUPSTRING
/*    */   extends PropertyComputer {
/* 10 */   public DUPSTRING() { super("DUPSTRING", "", "method", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 14 */     if (!(anEntity instanceof lrg.memoria.core.Method)) return null;
/*    */     
/* 16 */     ResultEntity aResult = anEntity.getProperty("#DUPLICATION#");
/*    */     
/* 18 */     if (aResult == null) return new ResultEntity("");
/*    */     
/* 20 */     DuplicationList aDudeList = (DuplicationList)aResult.getValue();
/*    */     
/* 22 */     return new ResultEntity(aDudeList.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\DUPSTRING.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */