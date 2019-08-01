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
/*    */ 
/*    */ class null
/*    */   extends AbstractEntityCommand
/*    */ {
/* 48 */   null(String $anonymous0) { super($anonymous0); }
/*    */   public AbstractEntityInterface execute() {
/*    */     try {
/* 51 */       command.setReceiver(this.receiver);
/* 52 */       AbstractEntityInterface tmp = (AbstractEntityInterface)command.execute();
/* 53 */       return (AbstractEntityInterface)tmp.getClass().getMethod(prop, new Class[0]).invoke(tmp, new Object[0]);
/* 54 */     } catch (Exception e) {
/* 55 */       System.out.println(e);
/* 56 */       throw new RuntimeException(String.valueOf(prop) + " method does not exist or it is improperly invoked");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\Visualization$5.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */