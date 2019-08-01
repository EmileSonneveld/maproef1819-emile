/*    */ package classes.lrg.insider.util;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.util.Visualization;
/*    */ import lrg.jMondrian.commands.AbstractEntityCommand;
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
/*    */ class null
/*    */   extends AbstractEntityCommand
/*    */ {
/* 27 */   null(String $anonymous0) { super($anonymous0); }
/*    */   public AbstractEntityInterface execute() {
/*    */     try {
/* 30 */       return (AbstractEntityInterface)this.receiver.getClass().getMethod(prop, new Class[0]).invoke(this.receiver, new Object[0]);
/* 31 */     } catch (Exception e) {
/* 32 */       System.out.println(e);
/* 33 */       throw new RuntimeException(String.valueOf(prop) + " method does not exist or it is improperly invoked");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\Visualization$3.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */