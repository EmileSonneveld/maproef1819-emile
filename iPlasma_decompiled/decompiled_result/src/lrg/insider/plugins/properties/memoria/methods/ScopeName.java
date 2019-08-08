/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
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
/*    */ public class ScopeName
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public ScopeName() { super("scope name", "", new String[] { "method", "global function" }, "string"); }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return new ResultEntity(anEntity.belongsTo("class").getName()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\ScopeName.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */