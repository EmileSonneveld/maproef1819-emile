/*    */ package classes.lrg.insider.plugins.core.properties.memoria.annotationinstance;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*    */ import lrg.insider.plugins.core.properties.memoria.annotationinstance.Detail;
/*    */ import lrg.memoria.core.AnnotationInstance;
/*    */ import lrg.memoria.core.AnnotationPropertyValuePair;
/*    */ import lrg.memoria.core.ModelElementList;
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
/* 29 */   public Detail() { super("Detail", "Constructs a detailed HTML String to be shown in the browser.", "annotation instance", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anAnnotationInstance) {
/* 33 */     String text = "";
/*    */     
/* 35 */     AnnotationInstance theInstance = (AnnotationInstance)anAnnotationInstance;
/*    */     
/* 37 */     ModelElementList<AnnotationPropertyValuePair> listOfProperties = theInstance.getPropertyValuePairs();
/*    */     
/* 39 */     text = String.valueOf(text) + "Annotation instance " + theInstance.getName() + " for " + linkTo(theInstance.getAnnotatedElement());
/*    */     
/* 41 */     ArrayList<String> thePropertyStrings = new ArrayList<String>();
/* 42 */     for (AnnotationPropertyValuePair aPropertyPair : listOfProperties) {
/* 43 */       thePropertyStrings.add(String.valueOf(aPropertyPair.getAp().getName()) + " = " + aPropertyPair.getValue());
/*    */     }
/* 45 */     text = String.valueOf(text) + bulletedList(thePropertyStrings);
/*    */     
/* 47 */     text = String.valueOf(text) + theInstance.getAnnotation().toString();
/*    */     
/* 49 */     return new ResultEntity(text);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\annotationinstance\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */