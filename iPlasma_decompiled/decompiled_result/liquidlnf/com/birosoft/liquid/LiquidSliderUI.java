/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import com.birosoft.liquid.skin.Skin;
/*     */ import com.birosoft.liquid.skin.SkinSimpleButtonIndexModel;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicSliderUI;
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
/*     */ public class LiquidSliderUI
/*     */   extends BasicSliderUI
/*     */ {
/*     */   private static Skin skinThumbHoriz;
/*     */   private static Skin skinThumbVert;
/*     */   private static Skin skinHorizSlider;
/*     */   private static Skin skinVertSlider;
/*     */   private Skin skinSlider;
/*  41 */   private SkinSimpleButtonIndexModel skinIndexModel = new SkinSimpleButtonIndexModel();
/*     */   
/*     */   protected boolean isRollover = false, wasRollover = false;
/*     */   
/*     */   protected boolean isDragging = false;
/*     */   
/*     */   protected BasicSliderUI.TrackListener trackListener;
/*     */ 
/*     */   
/*  50 */   public LiquidSliderUI() { super(null); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   protected BasicSliderUI.TrackListener createTrackListener(JSlider slider) { return new MyTrackListener(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected Dimension getThumbSize() { return getSkinThumb().getSize(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintThumb(Graphics g) {
/*  66 */     Rectangle knobBounds = this.thumbRect;
/*     */     
/*  68 */     int index = this.skinIndexModel.getIndexForState(this.slider.isEnabled(), this.isRollover, this.isDragging);
/*  69 */     getSkinThumb().drawCentered(g, index, knobBounds.x, knobBounds.y, knobBounds.width, knobBounds.height);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public static ComponentUI createUI(JComponent c) { return new LiquidSliderUI(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getTrackWidth() {
/*  84 */     double kIdealTrackWidth = 7.0D;
/*  85 */     double kIdealThumbHeight = 16.0D;
/*  86 */     double kWidthScalar = 0.4375D;
/*     */     
/*  88 */     if (this.slider.getOrientation() == 0) {
/*  89 */       return (int)(0.4375D * this.thumbRect.height);
/*     */     }
/*     */     
/*  92 */     return (int)(0.4375D * this.thumbRect.width);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getThumbOverhang() {
/*  98 */     if (this.slider.getOrientation() == 1)
/*     */     {
/* 100 */       return (int)(getThumbSize().getWidth() - getTrackWidth()) / 2;
/*     */     }
/*     */     
/* 103 */     return (int)(getThumbSize().getHeight() - getTrackWidth()) / 2;
/*     */   }
/*     */   
/*     */   class MyTrackListener
/*     */     extends BasicSliderUI.TrackListener
/*     */   {
/*     */     private final LiquidSliderUI this$0;
/*     */     
/* 111 */     MyTrackListener(LiquidSliderUI this$0) { super(this$0); this.this$0 = this$0; }
/*     */ 
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {
/* 115 */       super.mouseReleased(e);
/* 116 */       this.this$0.isDragging = false;
/* 117 */       this.this$0.slider.repaint();
/*     */     }
/*     */ 
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 122 */       super.mousePressed(e);
/* 123 */       if (this.this$0.thumbRect.contains(e.getX(), e.getY()))
/*     */       {
/* 125 */         this.this$0.isDragging = true;
/*     */       }
/* 127 */       this.this$0.slider.repaint();
/*     */     }
/*     */     
/*     */     public void mouseEntered(MouseEvent e) {
/* 131 */       this.this$0.isRollover = false;
/* 132 */       this.this$0.wasRollover = false;
/* 133 */       if (this.this$0.thumbRect.contains(e.getX(), e.getY()))
/*     */       {
/* 135 */         this.this$0.isRollover = true;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseExited(MouseEvent e) {
/* 141 */       this.this$0.isRollover = false;
/* 142 */       if (this.this$0.isRollover != this.this$0.wasRollover) {
/*     */         
/* 144 */         this.this$0.slider.repaint();
/* 145 */         this.this$0.wasRollover = this.this$0.isRollover;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseDragged(MouseEvent e) {
/* 151 */       if (this.this$0.thumbRect.contains(e.getX(), e.getY()))
/*     */       {
/* 153 */         this.this$0.isRollover = true;
/*     */       }
/* 155 */       super.mouseDragged(e);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseMoved(MouseEvent e) {
/* 160 */       if (this.this$0.thumbRect.contains(e.getX(), e.getY())) {
/*     */         
/* 162 */         this.this$0.isRollover = true;
/* 163 */         if (this.this$0.isRollover != this.this$0.wasRollover) {
/*     */           
/* 165 */           this.this$0.slider.repaint();
/* 166 */           this.this$0.wasRollover = this.this$0.isRollover;
/*     */         } 
/*     */       } else {
/*     */         
/* 170 */         this.this$0.isRollover = false;
/* 171 */         if (this.this$0.isRollover != this.this$0.wasRollover) {
/*     */           
/* 173 */           this.this$0.slider.repaint();
/* 174 */           this.this$0.wasRollover = this.this$0.isRollover;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintTrack(Graphics g) {
/* 182 */     Color trackColor = Color.red;
/*     */     
/* 184 */     boolean leftToRight = this.slider.getComponentOrientation().isLeftToRight();
/*     */     
/* 186 */     g.translate(this.trackRect.x, this.trackRect.y);
/*     */     
/* 188 */     int trackLeft = 0;
/* 189 */     int trackTop = 0;
/* 190 */     int trackRight = 0;
/* 191 */     int trackBottom = 0;
/*     */ 
/*     */     
/* 194 */     if (this.slider.getOrientation() == 0) {
/*     */       
/* 196 */       trackBottom = this.trackRect.height - 1 - getThumbOverhang();
/* 197 */       trackTop = trackBottom - getTrackWidth() - 1;
/* 198 */       trackRight = this.trackRect.width - 1;
/*     */       
/* 200 */       int h = (trackBottom - trackTop - getSkinHorizSlider().getVsize()) / 2;
/* 201 */       getSkinHorizSlider().draw(g, 0, trackLeft, trackTop + h, trackRight - trackLeft, getSkinHorizSlider().getVsize());
/*     */     }
/*     */     else {
/*     */       
/* 205 */       if (leftToRight) {
/*     */         
/* 207 */         trackLeft = this.trackRect.width - getThumbOverhang() - getTrackWidth();
/* 208 */         trackRight = this.trackRect.width - getThumbOverhang() - 1;
/*     */       } else {
/*     */         
/* 211 */         trackLeft = getThumbOverhang();
/* 212 */         trackRight = getThumbOverhang() + getTrackWidth() - 1;
/*     */       } 
/* 214 */       trackBottom = this.trackRect.height - 1;
/*     */       
/* 216 */       int w = (trackRight - trackLeft - getSkinVertSlider().getHsize()) / 2;
/* 217 */       getSkinVertSlider().draw(g, 0, trackLeft + w, trackTop, getSkinVertSlider().getHsize(), trackBottom - trackTop);
/*     */     } 
/*     */     
/* 220 */     g.translate(-this.trackRect.x, -this.trackRect.y);
/*     */   }
/*     */   
/*     */   protected void paintMinorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x) {
/* 224 */     g.setColor(LiquidLookAndFeel.getDarkControl());
/* 225 */     g.drawLine(x, 0, x, tickBounds.height / 2 - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintMajorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x) {
/* 230 */     g.setColor(LiquidLookAndFeel.getDarkControl());
/* 231 */     g.drawLine(x, 0, x, tickBounds.height - 2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintMinorTickForVertSlider(Graphics g, Rectangle tickBounds, int y) {
/* 236 */     g.setColor(LiquidLookAndFeel.getDarkControl());
/* 237 */     g.drawLine(0, y, tickBounds.width / 2 - 1, y);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintMajorTickForVertSlider(Graphics g, Rectangle tickBounds, int y) {
/* 242 */     g.setColor(LiquidLookAndFeel.getDarkControl());
/* 243 */     g.drawLine(0, y, tickBounds.width - 2, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Skin getSkinHorizSlider() {
/* 253 */     if (skinHorizSlider == null)
/* 254 */       skinHorizSlider = new Skin("sliderhorizbackground.png", 1, 6, 0, 6, 0); 
/* 255 */     return skinHorizSlider;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Skin getSkinThumbHoriz() {
/* 264 */     if (skinThumbHoriz == null)
/* 265 */       skinThumbHoriz = new Skin("sliderhoriz.png", 4, 0); 
/* 266 */     return skinThumbHoriz;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Skin getSkinThumbVert() {
/* 275 */     if (skinThumbVert == null)
/* 276 */       skinThumbVert = new Skin("slidervert.png", 4, 0); 
/* 277 */     return skinThumbVert;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Skin getSkinVertSlider() {
/* 286 */     if (skinVertSlider == null)
/* 287 */       skinVertSlider = new Skin("slidervertbackground.png", 1, 0, 6, 0, 6); 
/* 288 */     return skinVertSlider;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Skin getSkinThumb() {
/* 297 */     if (this.skinSlider == null)
/* 298 */       this.skinSlider = (this.slider.getOrientation() == 0) ? getSkinThumbHoriz() : getSkinThumbVert(); 
/* 299 */     return this.skinSlider;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidSliderUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */