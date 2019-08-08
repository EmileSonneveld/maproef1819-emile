/*    */ package classes.lrg.insider.plugins.core.properties.memoria.namespaces;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*    */ import lrg.insider.plugins.core.properties.memoria.namespaces.Detail;
/*    */ import lrg.memoria.core.Namespace;
/*    */ import lrg.memoria.core.UnnamedNamespace;
/*    */ 
/*    */ public class Detail
/*    */   extends AbstractDetail
/*    */ {
/* 13 */   public Detail() { super("Detail", "Constructs a detailed HTML String to be shown in the browser.", "namespace", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 18 */     if (anEntity instanceof Namespace) {
/*    */       
/* 20 */       Namespace aNamespace = (Namespace)anEntity;
/* 21 */       String text = "<h1>Namespace Detail: " + linkTo(aNamespace) + "</h1><hr><br>";
/* 22 */       if (aNamespace instanceof UnnamedNamespace)
/* 23 */         text = String.valueOf(text) + "Unnamed Namespace from file: " + ((UnnamedNamespace)aNamespace).getFile().getFullName() + "<br>"; 
/* 24 */       text = String.valueOf(text) + "Statute: " + aNamespace.getStatute() + "<br>";
/* 25 */       text = String.valueOf(text) + "Number of classes: " + aNamespace.getAbstractDataTypes().size() + "<br>";
/* 26 */       text = String.valueOf(text) + "Types: <br>";
/* 27 */       text = String.valueOf(text) + bulletedLinkList(aNamespace.getAllTypesList());
/* 28 */       return new ResultEntity(text);
/*    */     } 
/*    */     
/* 31 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\namespaces\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */