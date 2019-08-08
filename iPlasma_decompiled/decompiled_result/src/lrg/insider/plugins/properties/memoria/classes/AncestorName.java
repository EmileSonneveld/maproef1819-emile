/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class AncestorName
/*    */   extends PropertyComputer {
/* 10 */   public AncestorName() { super("ancestor name", "The name of the (first) ancestor of the class", "class", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 14 */     if (!(anEntity instanceof lrg.memoria.core.Class)) {
/* 15 */       return null;
/*    */     }
/* 17 */     GroupEntity grp = anEntity.getGroup("base classes");
/* 18 */     if (grp.size() == 0)
/* 19 */       return new ResultEntity(""); 
/* 20 */     return new ResultEntity(grp.getElementAt(0).getName());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\AncestorName.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */