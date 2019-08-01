/*    */ package classes.lrg.insider.plugins.core.properties.membrain.basicblock;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.core.properties.membrain.basicblock.Scope;
/*    */ import lrg.membrain.representation.BasicBlock;
/*    */ import lrg.memoria.core.Function;
/*    */ 
/*    */ public class Scope
/*    */   extends PropertyComputer {
/* 13 */   private static HashMap map = new HashMap();
/*    */ 
/*    */   
/* 16 */   public static void registerScopeProperty(BasicBlock b, Function scope) { map.put(b, scope); }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public Scope() { super("scope", "The scope of the basic block", "basic block", "entity"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 25 */     if (!(anEntity instanceof BasicBlock)) {
/* 26 */       return null;
/*    */     }
/* 28 */     return new ResultEntity(map.get(anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\membrain\basicblock\Scope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */