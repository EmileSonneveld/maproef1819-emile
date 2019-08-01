/*    */ package classes.lrg.insider.plugins.core.properties.hismo.functions;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.hismo.HismoDetail;
/*    */ import lrg.insider.plugins.core.properties.hismo.functions.Detail;
/*    */ import lrg.memoria.core.Function;
/*    */ import lrg.memoria.hismo.core.FunctionHistory;
/*    */ import lrg.memoria.hismo.core.GlobalFunctionHistory;
/*    */ import lrg.memoria.hismo.core.MethodHistory;
/*    */ 
/*    */ public class Detail extends HismoDetail {
/* 13 */   public Detail() { super("Detail", "Constructs a detailed HTML String to be shown in the browser.", new String[] { "method history", "global function history" }, "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof FunctionHistory)) return null;
/*    */     
/* 19 */     FunctionHistory aFunctionHistory = (FunctionHistory)anEntity;
/*    */     
/* 21 */     if (aFunctionHistory instanceof MethodHistory) {
/* 22 */       text = "<h1>Method History Detail: ";
/*    */     } else {
/* 24 */       text = "<h1>Global Function History Detail: ";
/* 25 */     }  String text = String.valueOf(text) + linkTo(aFunctionHistory) + "</h1><hr><br>";
/* 26 */     if (aFunctionHistory instanceof MethodHistory) {
/* 27 */       text = String.valueOf(text) + "Belongs to Class History:" + linkTo(((MethodHistory)aFunctionHistory).getClassHistory()) + "<br>";
/*    */     } else {
/* 29 */       text = String.valueOf(text) + "Belongs to Namespace History:" + linkTo(((GlobalFunctionHistory)aFunctionHistory).getNamespaceHistory()) + "<br>";
/* 30 */     }  text = String.valueOf(text) + "Number of parameters in first version: " + ((Function)aFunctionHistory.getFirstVersion()).getParameterList().size() + "<br>";
/* 31 */     text = String.valueOf(text) + "Number of parameters in last version: " + ((Function)aFunctionHistory.getLastVersion()).getParameterList().size() + "<br>";
/* 32 */     computeHismoDetail(aFunctionHistory);
/* 33 */     text = String.valueOf(text) + computeLocationDetail(aFunctionHistory);
/* 34 */     return new ResultEntity(text);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\hismo\functions\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */