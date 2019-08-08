/*    */ package classes.lrg.insider.plugins.core.properties.hismo.namespaces;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.hismo.HismoDetail;
/*    */ import lrg.insider.plugins.core.properties.hismo.namespaces.Detail;
/*    */ import lrg.memoria.hismo.core.NamespaceHistory;
/*    */ 
/*    */ public class Detail
/*    */   extends HismoDetail
/*    */ {
/* 12 */   public Detail() { super("Detail", "Constructs a detailed HTML String to be shown in the browser.", "namespace history", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 17 */     if (anEntity instanceof NamespaceHistory) {
/*    */       
/* 19 */       NamespaceHistory aNamespaceHistory = (NamespaceHistory)anEntity;
/* 20 */       String text = "<h1>Namespace Detail: " + linkTo(aNamespaceHistory) + "</h1><hr><br>";
/* 21 */       text = String.valueOf(text) + computeHismoDetail(aNamespaceHistory);
/* 22 */       text = String.valueOf(text) + "Number of class histories: " + aNamespaceHistory.getClassHistories().size() + "<br>";
/* 23 */       text = String.valueOf(text) + "Class Histories: <br>";
/* 24 */       text = String.valueOf(text) + bulletedLinkList(aNamespaceHistory.getClassHistories().getHistoriesCollection());
/* 25 */       return new ResultEntity(text);
/*    */     } 
/*    */     
/* 28 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\hismo\namespaces\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */