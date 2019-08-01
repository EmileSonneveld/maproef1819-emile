/*    */ package classes.lrg.insider.plugins.core.properties.memoria.component;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Scope
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public Scope() { super("scope", "The scope of the component", "component", "entity"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 20 */     if (!(anEntity instanceof Component)) {
/* 21 */       return null;
/*    */     }
/* 23 */     return new ResultEntity(((Component)anEntity).getSystem());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\component\Scope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */