/*    */ package classes.lrg.insider.plugins.core.properties.hismo.variables;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.hismo.HismoDetail;
/*    */ import lrg.insider.plugins.core.properties.hismo.variables.Detail;
/*    */ import lrg.memoria.hismo.core.AttributeHistory;
/*    */ import lrg.memoria.hismo.core.GlobalVariableHistory;
/*    */ import lrg.memoria.hismo.core.VariableHistory;
/*    */ 
/*    */ public class Detail extends HismoDetail {
/* 12 */   public Detail() { super("Detail", "HTML Detail", new String[] { "global variable history", "attribute history" }, "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 16 */     if (!(anEntity instanceof VariableHistory)) return null;
/*    */     
/* 18 */     VariableHistory aVariableHistory = (VariableHistory)anEntity;
/* 19 */     String text = "<h1>" + printKind(aVariableHistory) + " Detail: " + linkTo(aVariableHistory) + "</h1><hr><br>";
/* 20 */     if (aVariableHistory instanceof AttributeHistory) {
/* 21 */       text = String.valueOf(text) + "Belongs to Class History:" + linkTo(((AttributeHistory)aVariableHistory).getClassHistory()) + "<br>";
/*    */     } else {
/* 23 */       text = String.valueOf(text) + "Belongs to Namespace History:" + linkTo(((GlobalVariableHistory)aVariableHistory).getNamespaceHistory()) + "<br>";
/* 24 */     }  text = String.valueOf(text) + computeHismoDetail(aVariableHistory);
/* 25 */     text = String.valueOf(text) + computeLocationDetail(aVariableHistory);
/* 26 */     return new ResultEntity(text);
/*    */   }
/*    */   
/*    */   protected String printKind(VariableHistory aVariable) {
/* 30 */     if (aVariable instanceof GlobalVariableHistory)
/* 31 */       return "Global Variable History"; 
/* 32 */     if (aVariable instanceof AttributeHistory)
/* 33 */       return "Attribute History"; 
/* 34 */     return "Variable History";
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\hismo\variables\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */