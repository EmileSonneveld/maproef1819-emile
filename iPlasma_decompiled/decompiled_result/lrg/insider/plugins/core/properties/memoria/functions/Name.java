/*    */ package classes.lrg.insider.plugins.core.properties.memoria.functions;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Function;
/*    */ 
/*    */ public class Name
/*    */   extends PropertyComputer {
/* 10 */   public Name() { super("Name", "", new String[] { "method", "global function" }, "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 15 */     if (!(anEntity instanceof Function)) {
/* 16 */       return null;
/*    */     }
/* 18 */     return new ResultEntity(((Function)anEntity).getName());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\functions\Name.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */