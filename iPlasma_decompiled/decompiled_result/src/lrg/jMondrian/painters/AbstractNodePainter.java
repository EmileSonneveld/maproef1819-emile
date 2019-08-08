/*     */ package classes.lrg.jMondrian.painters;
/*     */ 
/*     */ import lrg.jMondrian.commands.AbstractNumericalCommand;
/*     */ import lrg.jMondrian.commands.AbstractStringCommand;
/*     */ import lrg.jMondrian.painters.AbstractNodePainter;
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
/*     */ public abstract class AbstractNodePainter
/*     */ {
/*     */   protected AbstractNumericalCommand widthCommand;
/*     */   protected AbstractNumericalCommand heightCommand;
/*     */   protected AbstractNumericalCommand colorCommand;
/*     */   protected AbstractNumericalCommand xCommand;
/*     */   protected AbstractNumericalCommand yCommand;
/*     */   protected AbstractStringCommand textCommand;
/*     */   protected AbstractStringCommand nameCommand;
/*     */   protected AbstractNumericalCommand frameColorCommand;
/*     */   
/*     */   public AbstractNodePainter width(AbstractNumericalCommand widthCommand) {
/*  39 */     this.widthCommand = widthCommand;
/*  40 */     return this;
/*     */   }
/*     */   
/*     */   public double getWidth(Object entity) {
/*  44 */     this.widthCommand.setReceiver(entity);
/*  45 */     return this.widthCommand.execute();
/*     */   }
/*     */   
/*     */   public AbstractNodePainter height(AbstractNumericalCommand heightCommand) {
/*  49 */     this.heightCommand = heightCommand;
/*  50 */     return this;
/*     */   }
/*     */   
/*     */   public double getHeight(Object entity) {
/*  54 */     this.heightCommand.setReceiver(entity);
/*  55 */     return this.heightCommand.execute();
/*     */   }
/*     */   
/*     */   public AbstractNodePainter color(AbstractNumericalCommand colorCommand) {
/*  59 */     this.colorCommand = colorCommand;
/*  60 */     return this;
/*     */   }
/*     */   
/*     */   public AbstractNodePainter frameColor(AbstractNumericalCommand colorCommand) {
/*  64 */     this.frameColorCommand = colorCommand;
/*  65 */     return this;
/*     */   }
/*     */   
/*     */   public AbstractNodePainter x(AbstractNumericalCommand xCommand) {
/*  69 */     this.xCommand = xCommand;
/*  70 */     return this;
/*     */   }
/*     */   
/*     */   public AbstractNodePainter y(AbstractNumericalCommand yCommand) {
/*  74 */     this.yCommand = yCommand;
/*  75 */     return this;
/*     */   }
/*     */   
/*     */   public double getX(Object entity) {
/*  79 */     this.xCommand.setReceiver(entity);
/*  80 */     return this.xCommand.execute();
/*     */   }
/*     */   
/*     */   public double getY(Object entity) {
/*  84 */     this.yCommand.setReceiver(entity);
/*  85 */     return this.yCommand.execute();
/*     */   }
/*     */   
/*     */   public AbstractNodePainter label(AbstractStringCommand textCommand) {
/*  89 */     this.textCommand = textCommand;
/*  90 */     return this;
/*     */   }
/*     */   
/*     */   public AbstractNodePainter name(AbstractStringCommand nameCommand) {
/*  94 */     this.nameCommand = nameCommand;
/*  95 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  99 */     String desc = "";
/* 100 */     if (!this.nameCommand.toString().equals("")) {
/* 101 */       desc = String.valueOf(desc) + "[" + this.nameCommand.toString() + "] ";
/*     */     }
/* 103 */     if (!this.xCommand.toString().equals("")) {
/* 104 */       desc = String.valueOf(desc) + "x[" + this.xCommand.toString() + "] ";
/*     */     }
/* 106 */     if (!this.yCommand.toString().equals("")) {
/* 107 */       desc = String.valueOf(desc) + "y[" + this.yCommand.toString() + "] ";
/*     */     }
/* 109 */     if (!this.widthCommand.toString().equals("")) {
/* 110 */       desc = String.valueOf(desc) + "width[" + this.widthCommand.toString() + "] ";
/*     */     }
/* 112 */     if (!this.heightCommand.toString().equals("")) {
/* 113 */       desc = String.valueOf(desc) + "height[" + this.heightCommand.toString() + "] ";
/*     */     }
/* 115 */     if (!this.colorCommand.toString().equals("")) {
/* 116 */       desc = String.valueOf(desc) + "color[" + this.colorCommand.toString() + "] ";
/*     */     }
/* 118 */     return desc;
/*     */   }
/*     */   
/*     */   public abstract void paint(ViewRendererInterface paramViewRendererInterface, Object paramObject, double paramDouble1, double paramDouble2, boolean paramBoolean);
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\painters\AbstractNodePainter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */