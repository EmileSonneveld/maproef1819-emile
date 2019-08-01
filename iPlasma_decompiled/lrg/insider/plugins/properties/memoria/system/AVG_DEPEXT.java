/*    */ package classes.lrg.insider.plugins.properties.memoria.system;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AVG_DEPEXT
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public AVG_DEPEXT() { super("AVG_DEPEXT", "Average Collaborator Classes per Method", "system", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return anEntity.getGroup("method group").applyFilter("model function").getProperty("FANOUTCLASS").aggregate("avg"); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\system\AVG_DEPEXT.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */