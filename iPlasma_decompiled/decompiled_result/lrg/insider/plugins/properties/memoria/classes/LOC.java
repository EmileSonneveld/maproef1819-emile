/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ public class LOC
/*    */   extends PropertyComputer
/*    */ {
/* 11 */   public LOC() { super("LOCC", "Lines of Code in Class", "class", "numeric"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 15 */     DataAbstraction aClass = (DataAbstraction)anEntity;
/* 16 */     return new ResultEntity((aClass.getLocation().getEndLine() - aClass.getLocation().getStartLine() + 1));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\LOC.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */