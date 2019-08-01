/*    */ package classes.lrg.insider.plugins.core.properties.memoria.subsystems;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ 
/*    */ public class Name
/*    */   extends PropertyComputer
/*    */ {
/* 11 */   public Name() { super("Name", "The name of the subsystem", "subsystem", "string"); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 16 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return new ResultEntity(anEntity.getName()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\subsystems\Name.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */