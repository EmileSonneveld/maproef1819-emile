/*    */ package classes.lrg.insider.plugins.core.properties.memoria.subsystems;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Package;
/*    */ import lrg.memoria.core.Subsystem;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Scope
/*    */   extends PropertyComputer
/*    */ {
/* 18 */   public Scope() { super("scope", "The scope of the component", "subsystem", "entity"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 22 */     Package firstPackage = (Package)((Subsystem)anEntity).getPackages().get(0);
/* 23 */     return new ResultEntity(firstPackage.getSystem());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\subsystems\Scope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */