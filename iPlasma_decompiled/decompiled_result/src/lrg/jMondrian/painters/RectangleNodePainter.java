/*     */ package classes.lrg.jMondrian.painters;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import lrg.jMondrian.painters.AbstractNodePainter;
/*     */ import lrg.jMondrian.painters.RectangleNodePainter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RectangleNodePainter
/*     */   extends AbstractNodePainter
/*     */ {
/*     */   private boolean withBorders;
/*     */   private boolean cumulativeOutline;
/*     */   private List<Rectangle> list;
/*     */   
/*  47 */   public RectangleNodePainter(boolean withBorders) { this(0.0D, 0.0D, withBorders); }
/*     */ 
/*     */   
/*     */   public RectangleNodePainter(double width, double height, boolean withBorders, boolean cumulativeOutline) {
/*  51 */     this(width, height, withBorders);
/*  52 */     this.cumulativeOutline = cumulativeOutline;
/*     */   }
/*     */   
/*     */   public RectangleNodePainter(double width, double height, boolean withBorders) {
/*     */     this.list = new ArrayList();
/*  57 */     this.withBorders = withBorders;
/*  58 */     this.cumulativeOutline = false;
/*     */     
/*  60 */     this.nameCommand = new Object(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     this.widthCommand = new Object(this, width);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     this.heightCommand = new Object(this, height);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     this.xCommand = this.yCommand = new Object(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     this.textCommand = new Object(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     this.frameColorCommand = new Object(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     this.colorCommand = CommandColor.WHITE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(ViewRendererInterface window, Object entity, double x1Bias, double y1Bias, boolean last) {
/* 101 */     double color, frameColor = 0.0D;
/* 102 */     boolean invisibleBorder = false;
/*     */     
/*     */     try {
/* 105 */       this.colorCommand.setReceiver(entity);
/* 106 */       color = this.colorCommand.execute();
/* 107 */     } catch (lrg.jMondrian.util.CommandColor.InvisibleException e) {
/*     */       return;
/*     */     } 
/*     */     
/*     */     try {
/* 112 */       this.frameColorCommand.setReceiver(entity);
/* 113 */       frameColor = this.frameColorCommand.execute();
/* 114 */     } catch (lrg.jMondrian.util.CommandColor.InvisibleException e) {
/* 115 */       invisibleBorder = true;
/*     */     } 
/*     */     
/* 118 */     double x = x1Bias;
/* 119 */     double y = y1Bias;
/*     */     
/* 121 */     this.widthCommand.setReceiver(entity);
/* 122 */     double width = this.widthCommand.execute();
/*     */     
/* 124 */     this.heightCommand.setReceiver(entity);
/* 125 */     double height = this.heightCommand.execute();
/*     */     
/* 127 */     this.xCommand.setReceiver(entity);
/* 128 */     x += this.xCommand.execute();
/*     */     
/* 130 */     this.yCommand.setReceiver(entity);
/* 131 */     y += this.yCommand.execute();
/*     */     
/* 133 */     this.nameCommand.setReceiver(entity);
/*     */     
/* 135 */     if (this.withBorders) {
/* 136 */       if (!invisibleBorder) {
/* 137 */         window.getShapeFactory().addRectangle(entity, toString(), (int)x, (int)y, (int)width, (int)height, (int)color, (int)frameColor);
/*     */       } else {
/* 139 */         window.getShapeFactory().addRectangle(entity, toString(), (int)x, (int)y, (int)width, (int)height, (int)color, false);
/*     */       } 
/*     */     } else {
/* 142 */       window.getShapeFactory().addRectangle(entity, toString(), (int)x, (int)y, (int)width, (int)height, (int)color, false);
/*     */     } 
/*     */     
/* 145 */     this.textCommand.setReceiver(entity);
/* 146 */     window.getShapeFactory().addText(entity, toString(), this.textCommand.execute(), (int)x + 5, (int)y + 12, Color.BLACK.getRGB());
/*     */     
/* 148 */     if (this.cumulativeOutline) {
/* 149 */       this.list.add(new Rectangle((int)x, (int)y, (int)(x + width), (int)(y + height)));
/*     */     }
/*     */     
/* 152 */     if (this.cumulativeOutline && last) {
/* 153 */       List<Rectangle> tmpOrder = new ArrayList<Rectangle>();
/* 154 */       List<Integer> px = new LinkedList<Integer>();
/* 155 */       List<Integer> py = new LinkedList<Integer>();
/* 156 */       tmpOrder.addAll(this.list);
/* 157 */       Collections.sort(tmpOrder, new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 166 */       for (int i = 0; i < tmpOrder.size(); i++) {
/* 167 */         if (i == 0) {
/* 168 */           px.add(Integer.valueOf(((Rectangle)tmpOrder.get(0)).x1));
/* 169 */           py.add(Integer.valueOf(((Rectangle)tmpOrder.get(0)).y2 + 1));
/* 170 */           px.add(Integer.valueOf(((Rectangle)tmpOrder.get(0)).x1));
/* 171 */           py.add(Integer.valueOf(((Rectangle)tmpOrder.get(0)).y1));
/* 172 */         } else if (((Rectangle)tmpOrder.get(i - 1)).y1 != ((Rectangle)tmpOrder.get(i)).y1) {
/* 173 */           if (((Rectangle)tmpOrder.get(i - 1)).y2 + 1 != ((Rectangle)tmpOrder.get(i)).y1) {
/* 174 */             px.add(Integer.valueOf(((Rectangle)tmpOrder.get(i - 1)).x2));
/* 175 */             py.add((Integer)py.get(py.size() - 1));
/* 176 */             px.add(Integer.valueOf(((Rectangle)tmpOrder.get(i - 1)).x2));
/* 177 */             py.add(Integer.valueOf(((Rectangle)tmpOrder.get(i - 1)).y2 + 1));
/* 178 */             window.getShapeFactory().addPolyLine(null, null, px, py);
/* 179 */             px.clear();
/* 180 */             py.clear();
/* 181 */             px.add(Integer.valueOf(((Rectangle)tmpOrder.get(i)).x1));
/* 182 */             py.add(Integer.valueOf(((Rectangle)tmpOrder.get(i)).y2 + 1));
/* 183 */             px.add(Integer.valueOf(((Rectangle)tmpOrder.get(i)).x1));
/* 184 */             py.add(Integer.valueOf(((Rectangle)tmpOrder.get(i)).y1));
/*     */           } else {
/* 186 */             int j = i + 1;
/* 187 */             for (; j < tmpOrder.size() && ((Rectangle)tmpOrder.get(j)).y1 == ((Rectangle)tmpOrder.get(i)).y1; j++);
/* 188 */             if (j - 1 < tmpOrder.size() && (((Rectangle)tmpOrder.get(j - 1)).x2 < ((Integer)px.get(0)).intValue() || ((Rectangle)tmpOrder.get(i - 1)).x2 < ((Rectangle)tmpOrder.get(i)).x1)) {
/* 189 */               px.add(Integer.valueOf(((Rectangle)tmpOrder.get(i - 1)).x2));
/* 190 */               py.add((Integer)py.get(py.size() - 1));
/* 191 */               px.add(Integer.valueOf(((Rectangle)tmpOrder.get(i - 1)).x2));
/* 192 */               py.add(Integer.valueOf(((Rectangle)tmpOrder.get(i - 1)).y2 + 1));
/* 193 */               window.getShapeFactory().addPolyLine(null, null, px, py);
/* 194 */               px.clear();
/* 195 */               py.clear();
/* 196 */               px.add(Integer.valueOf(((Rectangle)tmpOrder.get(i)).x1));
/* 197 */               py.add(Integer.valueOf(((Rectangle)tmpOrder.get(i)).y2 + 1));
/* 198 */               px.add(Integer.valueOf(((Rectangle)tmpOrder.get(i)).x1));
/* 199 */               py.add(Integer.valueOf(((Rectangle)tmpOrder.get(i)).y1));
/*     */             } else {
/* 201 */               px.add(Integer.valueOf(((Rectangle)tmpOrder.get(i - 1)).x2));
/* 202 */               py.add((Integer)py.get(py.size() - 1));
/* 203 */               px.add(Integer.valueOf(((Rectangle)tmpOrder.get(i - 1)).x2));
/* 204 */               py.add(Integer.valueOf(((Rectangle)tmpOrder.get(i - 1)).y2 + 1));
/* 205 */               px.add(0, Integer.valueOf(((Rectangle)tmpOrder.get(i)).x1));
/* 206 */               py.add(0, Integer.valueOf(((Rectangle)tmpOrder.get(i)).y1));
/* 207 */               px.add(0, Integer.valueOf(((Rectangle)tmpOrder.get(i)).x1));
/* 208 */               py.add(0, Integer.valueOf(((Rectangle)tmpOrder.get(i)).y2 + 1));
/*     */             } 
/*     */           } 
/*     */         } 
/* 212 */         if (i == tmpOrder.size() - 1) {
/* 213 */           px.add(0, Integer.valueOf(((Rectangle)tmpOrder.get(i)).x2));
/* 214 */           py.add(0, Integer.valueOf(((Rectangle)tmpOrder.get(i)).y2 + 1));
/* 215 */           px.add(0, Integer.valueOf(((Rectangle)tmpOrder.get(i)).x2));
/* 216 */           py.add(0, Integer.valueOf(((Rectangle)tmpOrder.get(i)).y1));
/*     */         } 
/*     */       } 
/* 219 */       window.getShapeFactory().addPolyLine(null, null, px, py);
/* 220 */       this.list.clear();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\painters\RectangleNodePainter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */