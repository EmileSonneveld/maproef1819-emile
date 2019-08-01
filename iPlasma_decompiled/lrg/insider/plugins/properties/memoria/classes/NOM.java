/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ 
/*    */ public class NOM
/*    */   extends PropertyComputer
/*    */ {
/* 12 */   public NOM() { super("NOM", "Number of Methods", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof lrg.memoria.core.Class)) {
/* 18 */       return null;
/*    */     }
/* 20 */     return new ResultEntity(((DataAbstraction)anEntity).getMethodList().size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NOM.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */