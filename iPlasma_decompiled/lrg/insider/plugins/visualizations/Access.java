/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.plugins.visualizations.Access;
/*    */ 
/*    */ public class Access {
/*    */   private AbstractEntityInterface method;
/*    */   
/*    */   public Access(AbstractEntityInterface method, AbstractEntityInterface attribute) {
/* 10 */     this.method = method;
/* 11 */     this.attribute = attribute;
/*    */   }
/*    */   
/*    */   private AbstractEntityInterface attribute;
/*    */   
/* 16 */   public AbstractEntityInterface getMethod() { return this.method; }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public AbstractEntityInterface getAttribute() { return this.attribute; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\Access.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */