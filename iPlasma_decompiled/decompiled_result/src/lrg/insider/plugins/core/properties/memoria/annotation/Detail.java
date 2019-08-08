/*    */ package classes.lrg.insider.plugins.core.properties.memoria.annotation;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Detail
/*    */   extends AbstractDetail
/*    */ {
/* 26 */   public Detail() { super("Detail", "Constructs a detailed HTML String to be shown in the browser.", "annotation", "string"); }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public ResultEntity compute(AbstractEntityInterface anAnnotation) { return new ResultEntity("Annotation " + anAnnotation.getName()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\annotation\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */