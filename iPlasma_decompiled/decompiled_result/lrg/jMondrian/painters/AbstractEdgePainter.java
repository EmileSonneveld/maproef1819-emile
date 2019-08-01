/*    */ package classes.lrg.jMondrian.painters;
/*    */ 
/*    */ import lrg.jMondrian.commands.AbstractEntityCommand;
/*    */ import lrg.jMondrian.commands.AbstractNumericalCommand;
/*    */ import lrg.jMondrian.commands.AbstractStringCommand;
/*    */ import lrg.jMondrian.painters.AbstractEdgePainter;
/*    */ import lrg.jMondrian.util.CommandColor;
/*    */ import lrg.jMondrian.view.ViewRendererInterface;
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
/*    */ public abstract class AbstractEdgePainter
/*    */ {
/*    */   protected AbstractEntityCommand fromCommand;
/*    */   protected AbstractEntityCommand toCommand;
/*    */   protected AbstractNumericalCommand colorCommand;
/*    */   protected AbstractStringCommand nameCommand;
/*    */   
/*    */   public AbstractEdgePainter(AbstractEntityCommand fromCommand, AbstractEntityCommand toCommand) {
/* 37 */     this.fromCommand = fromCommand;
/* 38 */     this.toCommand = toCommand;
/* 39 */     this.colorCommand = CommandColor.BLACK;
/* 40 */     this.nameCommand = new Object(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractEdgePainter color(AbstractNumericalCommand colorCommand) {
/* 48 */     this.colorCommand = colorCommand;
/* 49 */     return this;
/*    */   }
/*    */   
/*    */   public AbstractEdgePainter name(AbstractStringCommand nameCommand) {
/* 53 */     this.nameCommand = nameCommand;
/* 54 */     return this;
/*    */   }
/*    */   
/*    */   public abstract void paint(ViewRendererInterface paramViewRendererInterface, Object paramObject, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);
/*    */   
/*    */   public final Object getFrom(Object entity) {
/* 60 */     this.fromCommand.setReceiver(entity);
/* 61 */     return this.fromCommand.execute();
/*    */   }
/*    */   
/*    */   public final Object getTo(Object entity) {
/* 65 */     this.toCommand.setReceiver(entity);
/* 66 */     return this.toCommand.execute();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 70 */     String desc = "";
/* 71 */     if (!this.nameCommand.toString().equals("")) {
/* 72 */       desc = String.valueOf(desc) + "[" + this.nameCommand.toString() + "] ";
/*    */     }
/* 74 */     if (!this.fromCommand.toString().equals("")) {
/* 75 */       desc = String.valueOf(desc) + "from[" + this.fromCommand.toString() + "] ";
/*    */     }
/* 77 */     if (!this.toCommand.toString().equals("")) {
/* 78 */       desc = String.valueOf(desc) + "to[" + this.toCommand.toString() + "] ";
/*    */     }
/* 80 */     if (!this.colorCommand.toString().equals("")) {
/* 81 */       desc = String.valueOf(desc) + "color[" + this.colorCommand.toString() + "] ";
/*    */     }
/* 83 */     return desc;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\painters\AbstractEdgePainter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */