/*    */ package classes.lrg.insider.plugins.core.properties.memoria.packages;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Package;
/*    */ 
/*    */ public class Name
/*    */   extends PropertyComputer
/*    */ {
/* 11 */   public Name() { super("Name", "The name of the package", "package", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 16 */     if (!(anEntity instanceof Package)) {
/* 17 */       return null;
/*    */     }
/* 19 */     return new ResultEntity(((Package)anEntity).getName());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\packages\Name.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */