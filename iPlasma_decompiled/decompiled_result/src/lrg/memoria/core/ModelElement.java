/*    */ package lrg.memoria.core;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ModelElement
/*    */   extends AbstractEntity
/*    */   implements Serializable, ModelElementRoot
/*    */ {
/*    */   protected boolean restored = false;
/* 18 */   private Long elementID = ModelElementsRepository.getCurrentModelElementsRepository().addElement(this);
/*    */ 
/*    */ 
/*    */   
/* 22 */   public void setElementID(Long elementID) { this.elementID = elementID; }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public Long getElementID() { return this.elementID; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   boolean restore() {
/* 33 */     if (this.restored) {
/* 34 */       return false;
/*    */     }
/* 36 */     return this.restored = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\ModelElement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */