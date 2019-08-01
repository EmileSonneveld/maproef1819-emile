/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CodeFragment
/*    */ {
/*    */   private Entity entity;
/*    */   private long beginLine;
/*    */   private long endLine;
/*    */   
/*    */   public CodeFragment(Entity entity, long beginLine, long endLine) {
/* 17 */     this.entity = entity;
/* 18 */     this.beginLine = beginLine;
/* 19 */     this.endLine = endLine;
/*    */   }
/*    */ 
/*    */   
/* 23 */   public Entity getEntity() { return this.entity; }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public String getEntityName() { return this.entity.getName(); }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public long getBeginLine() { return this.beginLine; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public long getEndLine() { return this.endLine; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public int getLength() { return (int)(this.endLine - this.beginLine + 1L); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\CodeFragment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */