/*    */ package classes.lrg.jMondrian.painters;
/*    */ 
/*    */ import lrg.jMondrian.commands.AbstractEntityCommand;
/*    */ import lrg.jMondrian.painters.AbstractEdgePainter;
/*    */ import lrg.jMondrian.painters.LineEdgePainter;
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
/*    */ public class LineEdgePainter
/*    */   extends AbstractEdgePainter
/*    */ {
/*    */   private boolean oriented;
/*    */   
/* 32 */   public LineEdgePainter(AbstractEntityCommand fromCommand, AbstractEntityCommand toCommand) { this(fromCommand, toCommand, false); }
/*    */ 
/*    */   
/*    */   public LineEdgePainter(AbstractEntityCommand fromCommand, AbstractEntityCommand toCommand, boolean oriented) {
/* 36 */     super(fromCommand, toCommand);
/* 37 */     this.oriented = oriented;
/*    */   }
/*    */   
/*    */   public void paint(ViewRendererInterface window, Object entity, double x1Bias, double y1Bias, double x2Bias, double y2Bias) {
/*    */     try {
/* 42 */       this.colorCommand.setReceiver(entity);
/* 43 */       this.fromCommand.setReceiver(entity);
/* 44 */       this.toCommand.setReceiver(entity);
/* 45 */       this.nameCommand.setReceiver(entity);
/* 46 */       double color = this.colorCommand.execute();
/* 47 */       window.getShapeFactory().addLine(entity, toString(), (int)x1Bias, (int)y1Bias, (int)x2Bias, (int)y2Bias, (int)color, this.oriented);
/* 48 */     } catch (lrg.jMondrian.util.CommandColor.InvisibleException e) {
/*    */       return;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\painters\LineEdgePainter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */