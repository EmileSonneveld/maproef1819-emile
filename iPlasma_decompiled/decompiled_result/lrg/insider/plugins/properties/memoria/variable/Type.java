/*    */ package classes.lrg.insider.plugins.properties.memoria.variable;
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
/*    */ 
/*    */ 
/*    */ public class Type
/*    */   extends PropertyComputer
/*    */ {
/* 18 */   public Type() { super("Type", "Type of the variable", new String[] { "global variable", "attribute", "local variable", "parameter" }, "string"); }
/*    */ 
/*    */   
/* 21 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return new ResultEntity(anEntity.getGroup("type of variable").getElementAt(0).getName()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\variable\Type.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */