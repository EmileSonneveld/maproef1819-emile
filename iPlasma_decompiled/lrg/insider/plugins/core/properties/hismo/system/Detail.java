/*    */ package classes.lrg.insider.plugins.core.properties.hismo.system;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.hismo.HismoDetail;
/*    */ import lrg.insider.plugins.core.properties.hismo.system.Detail;
/*    */ import lrg.memoria.hismo.core.SystemHistory;
/*    */ 
/*    */ public class Detail
/*    */   extends HismoDetail
/*    */ {
/* 12 */   public Detail() { super("Detail", "Constructs a detailed HTML String to be shown in the browser.", "system history", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 17 */     if (anEntity instanceof SystemHistory) {
/*    */       
/* 19 */       SystemHistory aSystemHistory = (SystemHistory)anEntity;
/* 20 */       String text = "<h1>System History Detail: " + linkTo(aSystemHistory) + "</h1><hr><br>";
/* 21 */       text = String.valueOf(text) + computeHismoDetail(aSystemHistory);
/*    */ 
/*    */       
/* 24 */       text = String.valueOf(text) + "<br> Number of namespace histories: " + aSystemHistory.getNamespaceHistories().size() + "<br>";
/* 25 */       text = String.valueOf(text) + bulletedLinkList(aSystemHistory.getNamespaceHistories().getHistoriesCollection());
/* 26 */       return new ResultEntity(text);
/*    */     } 
/*    */     
/* 29 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\hismo\system\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */