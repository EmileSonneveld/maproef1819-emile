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
/*    */ 
/*    */ 
/*    */ public class Name
/*    */   extends PropertyComputer
/*    */ {
/* 18 */   public Name() { super("Name", "The name of the component", "component", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 23 */     if (!(anEntity instanceof Component)) {
/* 24 */       return null;
/*    */     }
/* 26 */     Component anComponent = (Component)anEntity;
/* 27 */     return new ResultEntity(anComponent.getName());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\component\Name.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */