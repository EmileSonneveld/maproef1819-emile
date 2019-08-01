/*    */ package classes.lrg.insider.plugins.core.properties.hismo.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.hismo.HismoDetail;
/*    */ import lrg.insider.plugins.core.properties.hismo.classes.Detail;
/*    */ import lrg.memoria.hismo.core.ClassHistory;
/*    */ 
/*    */ public class Detail
/*    */   extends HismoDetail
/*    */ {
/* 12 */   public Detail() { super("Detail", "The detailed information for a class history.", "class history", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 16 */     if (anEntity instanceof ClassHistory) {
/*    */       
/* 18 */       ClassHistory aClassHistory = (ClassHistory)anEntity;
/* 19 */       String text = "<h1>Class Detail: " + linkTo(aClassHistory) + "</h1><hr><br>";
/* 20 */       text = String.valueOf(text) + "Belongs To: " + linkTo(aClassHistory.getNamespaceHistory()) + "<br>";
/* 21 */       text = String.valueOf(text) + computeHismoDetail(aClassHistory);
/* 22 */       text = String.valueOf(text) + computeLocationDetail(aClassHistory);
/* 23 */       text = String.valueOf(text) + "Number of method histories: " + aClassHistory.getMethodHistories().size() + "<br>";
/* 24 */       text = String.valueOf(text) + bulletedLinkList(aClassHistory.getMethodHistories().getHistoriesCollection());
/* 25 */       text = String.valueOf(text) + "Number of attribute histories: " + aClassHistory.getAttributeHistories().size() + "<br>";
/* 26 */       text = String.valueOf(text) + bulletedLinkList(aClassHistory.getAttributeHistories().getHistoriesCollection());
/*    */       
/* 28 */       return new ResultEntity(text);
/*    */     } 
/*    */     
/* 31 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\hismo\classes\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */