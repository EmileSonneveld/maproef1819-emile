/*    */ package lrg.memoria.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnnotationPropertyValuePair
/*    */   extends ModelElement
/*    */ {
/*    */   private AnnotationProperty ap;
/*    */   private String value;
/*    */   
/*    */   public void accept(ModelVisitor v) {}
/*    */   
/* 15 */   public AnnotationProperty getAp() { return this.ap; }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public void setAp(AnnotationProperty ap) { this.ap = ap; }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public String getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public void setValue(String value) { this.value = value; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\AnnotationPropertyValuePair.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */