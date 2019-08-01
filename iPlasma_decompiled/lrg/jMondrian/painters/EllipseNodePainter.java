/*     */ package classes.lrg.jMondrian.painters;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import lrg.jMondrian.painters.AbstractNodePainter;
/*     */ import lrg.jMondrian.painters.EllipseNodePainter;
/*     */ import lrg.jMondrian.util.CommandColor;
/*     */ import lrg.jMondrian.view.ViewRendererInterface;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EllipseNodePainter
/*     */   extends AbstractNodePainter
/*     */ {
/*     */   private boolean withBorders;
/*     */   
/*  35 */   public EllipseNodePainter(boolean withBorders) { this(0.0D, 0.0D, withBorders); }
/*     */ 
/*     */   
/*     */   public EllipseNodePainter(double width, double height, boolean withBorders) {
/*     */     this.withBorders = true;
/*  40 */     this.withBorders = withBorders;
/*     */     
/*  42 */     this.nameCommand = new Object(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     this.widthCommand = new Object(this, width);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  54 */     this.heightCommand = new Object(this, height);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     this.xCommand = this.yCommand = new Object(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     this.textCommand = new Object(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     this.frameColorCommand = new Object(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     this.colorCommand = CommandColor.WHITE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(ViewRendererInterface window, Object entity, double x1Bias, double y1Bias, boolean last) {
/*  83 */     double color, frameColor = 0.0D;
/*  84 */     boolean invisibleBorder = false;
/*     */     
/*     */     try {
/*  87 */       this.colorCommand.setReceiver(entity);
/*  88 */       color = this.colorCommand.execute();
/*  89 */     } catch (lrg.jMondrian.util.CommandColor.InvisibleException e) {
/*     */       return;
/*     */     } 
/*     */     
/*     */     try {
/*  94 */       this.frameColorCommand.setReceiver(entity);
/*  95 */       frameColor = this.frameColorCommand.execute();
/*  96 */     } catch (lrg.jMondrian.util.CommandColor.InvisibleException e) {
/*  97 */       invisibleBorder = true;
/*     */     } 
/*     */     
/* 100 */     double x = x1Bias;
/* 101 */     double y = y1Bias;
/*     */     
/* 103 */     this.widthCommand.setReceiver(entity);
/* 104 */     double width = this.widthCommand.execute();
/*     */     
/* 106 */     this.heightCommand.setReceiver(entity);
/* 107 */     double height = this.heightCommand.execute();
/*     */     
/* 109 */     this.xCommand.setReceiver(entity);
/* 110 */     x += this.xCommand.execute();
/*     */     
/* 112 */     this.yCommand.setReceiver(entity);
/* 113 */     y += this.yCommand.execute();
/*     */     
/* 115 */     this.nameCommand.setReceiver(entity);
/*     */     
/* 117 */     if (this.withBorders) {
/* 118 */       if (!invisibleBorder) {
/* 119 */         window.getShapeFactory().addEllipse(entity, toString(), (int)x, (int)y, (int)width, (int)height, (int)color, (int)frameColor);
/*     */       } else {
/* 121 */         window.getShapeFactory().addEllipse(entity, toString(), (int)x, (int)y, (int)width, (int)height, (int)color, false);
/*     */       } 
/*     */     } else {
/* 124 */       window.getShapeFactory().addEllipse(entity, toString(), (int)x, (int)y, (int)width, (int)height, (int)color, false);
/*     */     } 
/*     */     
/* 127 */     this.textCommand.setReceiver(entity);
/* 128 */     window.getShapeFactory().addText(entity, toString(), this.textCommand.execute(), (int)x, (int)y - 5, Color.BLACK.getRGB());
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\painters\EllipseNodePainter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */