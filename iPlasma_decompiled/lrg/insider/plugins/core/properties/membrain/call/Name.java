/*    */ package classes.lrg.insider.plugins.core.properties.membrain.call;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.membrain.representation.instructionSet.Abstractions.MCall;
/*    */ 
/*    */ public class Name
/*    */   extends PropertyComputer
/*    */ {
/* 11 */   public Name() { super("Name", "The name of the call", "membrain call", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 15 */     if (!(anEntity instanceof MCall)) {
/* 16 */       return null;
/*    */     }
/* 18 */     return new ResultEntity(((MCall)anEntity).produceName());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\membrain\call\Name.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */