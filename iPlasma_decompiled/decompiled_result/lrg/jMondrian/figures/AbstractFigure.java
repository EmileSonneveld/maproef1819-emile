/*    */ package classes.lrg.jMondrian.figures;
/*    */ 
/*    */ import lrg.jMondrian.figures.AbstractFigure;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractFigure
/*    */ {
/*    */   protected Object entity;
/*    */   
/* 28 */   public AbstractFigure(Object entity) { this.entity = entity; }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public Object getEntity() { return this.entity; }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 36 */     if (o instanceof AbstractFigure) {
/* 37 */       return this.entity.equals(((AbstractFigure)o).entity);
/*    */     }
/* 39 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\figures\AbstractFigure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */