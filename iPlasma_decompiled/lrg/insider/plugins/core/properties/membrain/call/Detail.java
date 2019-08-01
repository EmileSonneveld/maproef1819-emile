/*    */ package classes.lrg.insider.plugins.core.properties.membrain.call;
/*    */ 
/*    */ import java.util.StringTokenizer;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Detail
/*    */   extends AbstractDetail
/*    */ {
/* 15 */   public Detail() { super("Detail", "The detailed information for a call", "membrain call", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 19 */     if (anEntity instanceof lrg.membrain.representation.instructionSet.Abstractions.MCall) {
/*    */       
/* 21 */       String text = "<h1>Intermediate representation </h1><hr><br>";
/* 22 */       text = String.valueOf(text) + "<table>";
/* 23 */       StringTokenizer strToken = new StringTokenizer(anEntity.toString(), " \t", false);
/* 24 */       text = String.valueOf(text) + "<tr>";
/* 25 */       while (strToken.hasMoreTokens()) {
/* 26 */         text = String.valueOf(text) + "<td valign=\"top\">";
/* 27 */         text = String.valueOf(text) + strToken.nextToken().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
/*    */       } 
/* 29 */       text = String.valueOf(text) + "</tr>";
/* 30 */       text = String.valueOf(text) + "</table>";
/* 31 */       return new ResultEntity(text);
/*    */     } 
/* 33 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\membrain\call\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */