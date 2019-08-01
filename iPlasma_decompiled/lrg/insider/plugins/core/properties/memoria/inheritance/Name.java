/*    */ package classes.lrg.insider.plugins.core.properties.memoria.inheritance;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.InheritanceRelation;
/*    */ 
/*    */ public class Name
/*    */   extends PropertyComputer
/*    */ {
/* 11 */   public Name() { super("Name", "The name of the inheritance relation", "inheritance relation", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 15 */     if (!(anEntity instanceof InheritanceRelation)) {
/* 16 */       return null;
/*    */     }
/* 18 */     InheritanceRelation rel = (InheritanceRelation)anEntity;
/* 19 */     return new ResultEntity(rel.getSuperClass().getProperty("Name") + "<->" + rel.getSubClass().getProperty("Name"));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\inheritance\Name.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */