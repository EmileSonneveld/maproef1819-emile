/*    */ package classes.lrg.insider.plugins.core.properties.memoria.inheritance;
/*    */ 
/*    */ import java.util.StringTokenizer;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Detail
/*    */   extends AbstractDetail
/*    */ {
/* 13 */   public Detail() { super("Detail", "The detailed information for an inheritance relation", "inheritance relation", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 18 */     if (anEntity instanceof lrg.memoria.core.InheritanceRelation) {
/*    */       
/* 20 */       String text = "<h1>Inheritance relation </h1><hr><br>";
/* 21 */       text = String.valueOf(text) + "<table>";
/* 22 */       StringTokenizer strToken = new StringTokenizer(anEntity.toString(), " \t", false);
/* 23 */       text = String.valueOf(text) + "<tr>";
/* 24 */       while (strToken.hasMoreTokens()) {
/* 25 */         text = String.valueOf(text) + "<td valign=\"top\">";
/* 26 */         text = String.valueOf(text) + strToken.nextToken().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
/*    */       } 
/* 28 */       text = String.valueOf(text) + "</tr>";
/* 29 */       text = String.valueOf(text) + "</table>";
/* 30 */       return new ResultEntity(text);
/*    */     } 
/*    */ 
/*    */     
/* 34 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\inheritance\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */