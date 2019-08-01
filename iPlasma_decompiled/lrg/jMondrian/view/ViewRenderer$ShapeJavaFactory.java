/*     */ package classes.lrg.jMondrian.view;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import lrg.jMondrian.view.ShapeElementFactory;
/*     */ import lrg.jMondrian.view.ViewRenderer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class ShapeJavaFactory
/*     */   implements ShapeElementFactory
/*     */ {
/*  55 */   private List<Shape> shapes = new ArrayList();
/*  56 */   private List<Color> colors = new ArrayList();
/*  57 */   private List<String> labels = new ArrayList();
/*  58 */   private List<Integer> xLabel = new ArrayList();
/*  59 */   private List<Integer> yLabel = new ArrayList();
/*  60 */   private List<Object> m = new ArrayList();
/*  61 */   private List<String> d = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRectangle(Object ent, String descr, int x1, int y1, int width, int heigth, int color, boolean border) {
/*  66 */     this.shapes.add(new Rectangle2D.Double(x1, y1, width, heigth));
/*  67 */     this.colors.add(new Color(color));
/*  68 */     this.m.add(ent);
/*  69 */     this.d.add(descr);
/*  70 */     if (border) {
/*  71 */       this.shapes.add(new Line2D.Double(x1, y1, (x1 + width), y1));
/*  72 */       this.colors.add(Color.BLACK);
/*  73 */       this.m.add(null);
/*  74 */       this.d.add(null);
/*  75 */       this.shapes.add(new Line2D.Double(x1, y1, x1, (y1 + heigth)));
/*  76 */       this.colors.add(Color.BLACK);
/*  77 */       this.m.add(null);
/*  78 */       this.d.add(null);
/*  79 */       this.shapes.add(new Line2D.Double((x1 + width), y1, (x1 + width), (y1 + heigth)));
/*  80 */       this.colors.add(Color.BLACK);
/*  81 */       this.m.add(null);
/*  82 */       this.d.add(null);
/*  83 */       this.shapes.add(new Line2D.Double(x1, (y1 + heigth), (x1 + width), (y1 + heigth)));
/*  84 */       this.colors.add(Color.BLACK);
/*  85 */       this.m.add(null);
/*  86 */       this.d.add(null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addRectangle(Object ent, String descr, int x1, int y1, int width, int heigth, int color, int frameColor) {
/*  91 */     this.shapes.add(new Rectangle2D.Double(x1, y1, width, heigth));
/*  92 */     this.colors.add(new Color(color));
/*  93 */     this.m.add(ent);
/*  94 */     this.d.add(descr);
/*  95 */     this.shapes.add(new Line2D.Double(x1, y1, (x1 + width), y1));
/*  96 */     this.colors.add(new Color(frameColor));
/*  97 */     this.m.add(null);
/*  98 */     this.d.add(null);
/*  99 */     this.shapes.add(new Line2D.Double(x1, y1, x1, (y1 + heigth)));
/* 100 */     this.colors.add(new Color(frameColor));
/* 101 */     this.m.add(null);
/* 102 */     this.d.add(null);
/* 103 */     this.shapes.add(new Line2D.Double((x1 + width), y1, (x1 + width), (y1 + heigth)));
/* 104 */     this.colors.add(new Color(frameColor));
/* 105 */     this.m.add(null);
/* 106 */     this.d.add(null);
/* 107 */     this.shapes.add(new Line2D.Double(x1, (y1 + heigth), (x1 + width), (y1 + heigth)));
/* 108 */     this.colors.add(new Color(frameColor));
/* 109 */     this.m.add(null);
/* 110 */     this.d.add(null);
/*     */   }
/*     */   
/*     */   public void addEllipse(Object ent, String descr, int x1, int y1, int width, int heigth, int color, boolean border) {
/* 114 */     if (border) {
/* 115 */       this.shapes.add(new Ellipse2D.Double(x1, y1, width, heigth));
/* 116 */       this.colors.add(Color.BLACK);
/* 117 */       this.m.add(null);
/* 118 */       this.d.add(null);
/*     */     } 
/* 120 */     this.shapes.add(new Ellipse2D.Double((x1 + 1), (y1 + 1), (width - 2), (heigth - 2)));
/* 121 */     this.colors.add(new Color(color));
/* 122 */     this.m.add(ent);
/* 123 */     this.d.add(descr);
/*     */   }
/*     */   
/*     */   public void addEllipse(Object ent, String descr, int x1, int y1, int width, int heigth, int color, int frameColor) {
/* 127 */     this.shapes.add(new Ellipse2D.Double(x1, y1, width, heigth));
/* 128 */     this.colors.add(new Color(frameColor));
/* 129 */     this.m.add(null);
/* 130 */     this.d.add(null);
/* 131 */     this.shapes.add(new Ellipse2D.Double((x1 + 1), (y1 + 1), (width - 2), (heigth - 2)));
/* 132 */     this.colors.add(new Color(color));
/* 133 */     this.m.add(ent);
/* 134 */     this.d.add(descr);
/*     */   }
/*     */   
/*     */   public void addLine(Object ent, String descr, int x1, int y1, int x2, int y2, int color, boolean oriented) {
/* 138 */     if (oriented) {
/* 139 */       double epsilon = 0.5235987755982988D;
/* 140 */       double dist = 10.0D;
/*     */       
/* 142 */       if (x2 - x1 != 0 || y2 - y1 != 0) {
/* 143 */         double angleWithOY; if (x2 - x1 == 0)
/* 144 */         { if (y2 - y1 > 0) { angleWithOY = 0.0D; }
/* 145 */           else { angleWithOY = Math.PI; }  }
/* 146 */         else if (y2 - y1 == 0)
/* 147 */         { if (x2 - x1 > 0) { angleWithOY = 1.5707963267948966D; }
/* 148 */           else { angleWithOY = 4.71238898038469D; }
/*     */            }
/* 150 */         else { angleWithOY = Math.atan((x2 - x1) / (y2 - y1));
/* 151 */           if (y2 - y1 < 0) {
/* 152 */             angleWithOY += Math.PI;
/*     */           } }
/*     */         
/* 155 */         double xa = x2 - dist / Math.cos(epsilon) * Math.cos(1.5707963267948966D - angleWithOY - epsilon);
/* 156 */         double ya = y2 - dist / Math.cos(epsilon) * Math.sin(1.5707963267948966D - angleWithOY - epsilon);
/* 157 */         double xap = x2 - dist / Math.cos(epsilon) * Math.cos(1.5707963267948966D - angleWithOY + epsilon);
/* 158 */         double yap = y2 - dist / Math.cos(epsilon) * Math.sin(1.5707963267948966D - angleWithOY + epsilon);
/* 159 */         this.shapes.add(new Line2D.Double(xa, ya, x2, y2));
/* 160 */         this.shapes.add(new Line2D.Double(xap, yap, x2, y2));
/* 161 */         this.colors.add(new Color(color));
/* 162 */         this.colors.add(new Color(color));
/* 163 */         this.m.add(ent);
/* 164 */         this.m.add(ent);
/* 165 */         this.d.add(descr);
/* 166 */         this.d.add(descr);
/*     */       } 
/*     */     } 
/* 169 */     this.shapes.add(new Line2D.Double(x1, y1, x2, y2));
/* 170 */     this.colors.add(new Color(color));
/* 171 */     this.m.add(ent);
/* 172 */     this.d.add(descr);
/*     */   }
/*     */   
/*     */   public void addPolyLine(Object ent, String descr, List<Integer> x, List<Integer> y) {
/* 176 */     for (int i = 1; i < x.size(); i++) {
/* 177 */       this.shapes.add(new Line2D.Double(((Integer)x.get(i - 1)).intValue(), ((Integer)y.get(i - 1)).intValue(), ((Integer)x.get(i)).intValue(), ((Integer)y.get(i)).intValue()));
/* 178 */       this.colors.add(Color.LIGHT_GRAY);
/* 179 */       this.m.add(ent);
/* 180 */       this.d.add(descr);
/*     */     } 
/* 182 */     this.shapes.add(new Line2D.Double(((Integer)x.get(0)).intValue(), ((Integer)y.get(0)).intValue(), ((Integer)x.get(x.size() - 1)).intValue(), ((Integer)y.get(y.size() - 1)).intValue()));
/* 183 */     this.colors.add(Color.LIGHT_GRAY);
/* 184 */     this.m.add(ent);
/* 185 */     this.d.add(descr);
/*     */   }
/*     */   
/*     */   public void addText(Object ent, String descr, String text, int x1, int y1, int color) {
/* 189 */     this.labels.add(text);
/* 190 */     this.xLabel.add(Integer.valueOf(x1));
/* 191 */     this.yLabel.add(Integer.valueOf(y1));
/*     */   }
/*     */   
/*     */   public void update(Graphics g) {
/* 195 */     Graphics2D g2 = (Graphics2D)g;
/* 196 */     Iterator<Shape> it = this.shapes.iterator();
/* 197 */     Iterator<Color> it1 = this.colors.iterator();
/*     */     
/* 199 */     AffineTransform transf = new AffineTransform(ViewRenderer.access$0(ViewRenderer.this), 0.0D, 0.0D, ViewRenderer.access$0(ViewRenderer.this), 0.0D, 0.0D);
/*     */     
/* 201 */     g2.setColor(Color.WHITE);
/* 202 */     g2.fillRect(0, 0, ViewRenderer.this.getWidth(), ViewRenderer.this.getHeight());
/* 203 */     while (it.hasNext()) {
/* 204 */       Shape s = transf.createTransformedShape((Shape)it.next());
/* 205 */       Color c = (Color)it1.next();
/* 206 */       g2.setPaint(c);
/* 207 */       g2.draw(s);
/* 208 */       g2.setPaint(c);
/* 209 */       g2.fill(s);
/*     */     } 
/*     */     
/* 212 */     g2.setPaint(Color.BLACK);
/* 213 */     Iterator<String> it2 = this.labels.iterator();
/* 214 */     double[] point = new double[2];
/* 215 */     double[] trans = new double[2];
/* 216 */     int i = 0;
/* 217 */     while (it2.hasNext()) {
/* 218 */       point[0] = ((Integer)this.xLabel.get(i)).intValue();
/* 219 */       point[1] = ((Integer)this.yLabel.get(i)).intValue();
/* 220 */       transf.transform(point, 0, trans, 0, 1);
/* 221 */       g2.drawString((String)it2.next(), (int)trans[0], (int)trans[1]);
/* 222 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String findStatusInformation(int x, int y) {
/* 227 */     AffineTransform transf = new AffineTransform(ViewRenderer.access$0(ViewRenderer.this), 0.0D, 0.0D, ViewRenderer.access$0(ViewRenderer.this), 0.0D, 0.0D);
/* 228 */     for (int i = this.shapes.size() - 1; i >= 0; i--) {
/* 229 */       Shape newShape = transf.createTransformedShape((Shape)this.shapes.get(i));
/* 230 */       if (newShape.contains(x, y, 1.0D, 1.0D) && this.m.get(i) != null) {
/* 231 */         return (String)this.d.get(i);
/*     */       }
/*     */     } 
/* 234 */     return "";
/*     */   }
/*     */   
/*     */   public Object findEntity(int x, int y) {
/* 238 */     AffineTransform transf = new AffineTransform(ViewRenderer.access$0(ViewRenderer.this), 0.0D, 0.0D, ViewRenderer.access$0(ViewRenderer.this), 0.0D, 0.0D);
/* 239 */     for (int i = this.shapes.size() - 1; i >= 0; i--) {
/* 240 */       Shape newShape = transf.createTransformedShape((Shape)this.shapes.get(i));
/* 241 */       if (newShape.contains(x, y, 1.0D, 1.0D) && this.m.get(i) != null) {
/* 242 */         return this.m.get(i);
/*     */       }
/*     */     } 
/* 245 */     return null;
/*     */   }
/*     */   
/*     */   private ShapeJavaFactory() {}
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\view\ViewRenderer$ShapeJavaFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */