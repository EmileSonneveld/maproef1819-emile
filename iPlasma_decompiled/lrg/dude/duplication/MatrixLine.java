/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MatrixLine
/*    */ {
/*    */   private String code;
/*    */   private Entity entity;
/*    */   private int realIndex;
/*    */   private boolean unique;
/*    */   
/*    */   public MatrixLine(String code, Entity entity, int realIndex) {
/* 16 */     this.unique = true;
/*    */ 
/*    */     
/* 19 */     this.code = code;
/* 20 */     this.entity = entity;
/* 21 */     this.realIndex = realIndex;
/*    */   }
/*    */ 
/*    */   
/* 25 */   public String getCode() { return this.code; }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public void setCode(String code) { this.code = code; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public Entity getEntity() { return this.entity; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public int getRealIndex() { return this.realIndex; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public String toString() { return "<" + this.entity.getName() + ",line:" + this.realIndex + ">\t" + this.code; }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public boolean isUnique() { return this.unique; }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public void setDuplicated() { this.unique = false; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\MatrixLine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */