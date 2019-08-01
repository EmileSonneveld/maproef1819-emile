/*     */ package classes.lrg.jMondrian.view;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.FileOutputStream;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.KeyStroke;
/*     */ import lrg.jMondrian.util.MenuReaction;
/*     */ import lrg.jMondrian.view.ShapeElementFactory;
/*     */ import lrg.jMondrian.view.ViewRenderer;
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
/*     */ public class ViewRenderer
/*     */   extends JPanel
/*     */   implements ActionListener, ViewRendererInterface
/*     */ {
/*     */   private ShapeElementFactory factory;
/*     */   private double zoom;
/*     */   private String title;
/*     */   private static MenuReaction common;
/*     */   
/*  50 */   public static void setMenuReaction(MenuReaction listener) { common = listener; }
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
/* 251 */   public ShapeElementFactory getShapeFactory() { return this.factory; }
/*     */ 
/*     */ 
/*     */   
/* 255 */   public void setPreferredSize(int width, int height) { setPreferredSize(new Dimension(width, height)); }
/*     */   public ViewRenderer(String title) {
/*     */     this.factory = new ShapeJavaFactory(this, null);
/*     */     this.zoom = 1.0D;
/* 259 */     this.title = title;
/*     */   }
/*     */ 
/*     */   
/* 263 */   public ViewRenderer() { this("lrg.jMondrian"); }
/*     */ 
/*     */ 
/*     */   
/* 267 */   public void update(Graphics g) { this.factory.update(g); }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/* 271 */     update(g);
/* 272 */     setPreferredSize(new Dimension(getWidth(), getHeight()));
/* 273 */     revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void open() {
/* 278 */     JDialog.setDefaultLookAndFeelDecorated(true);
/* 279 */     JFrame.setDefaultLookAndFeelDecorated(true);
/*     */     
/* 281 */     JFrame f = new JFrame(this.title);
/* 282 */     JMenuBar menuBar = new JMenuBar();
/* 283 */     JMenu menu = new JMenu("File");
/* 284 */     JMenuItem item = new JMenuItem("Save");
/* 285 */     item.addActionListener(this);
/* 286 */     item.setAccelerator(KeyStroke.getKeyStroke(83, 2));
/* 287 */     menu.add(item);
/* 288 */     item = new JMenuItem("Close");
/* 289 */     item.addActionListener(new Object(this, f));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 294 */     item.setAccelerator(KeyStroke.getKeyStroke(67, 2));
/* 295 */     menu.add(item);
/* 296 */     menuBar.add(menu);
/* 297 */     menu = new JMenu("Zoom");
/* 298 */     item = new JMenuItem("Zoom In");
/* 299 */     item.setAccelerator(KeyStroke.getKeyStroke('+'));
/* 300 */     item.addActionListener(this);
/* 301 */     menu.add(item);
/* 302 */     item = new JMenuItem("Zoom Out");
/* 303 */     item.setAccelerator(KeyStroke.getKeyStroke('-'));
/* 304 */     item.addActionListener(this);
/* 305 */     menu.add(item);
/* 306 */     menuBar.add(menu);
/*     */     
/* 308 */     f.addWindowListener(new Object(this, f));
/*     */ 
/*     */ 
/*     */     
/* 312 */     JScrollPane scroll = new JScrollPane(this);
/* 313 */     JPanel statusBar = new JPanel(new FlowLayout());
/* 314 */     JLabel statusText = new JLabel(this.title);
/* 315 */     statusBar.add(statusText);
/* 316 */     JPanel content = new JPanel(new BorderLayout());
/*     */     
/* 318 */     if (common != null) {
/* 319 */       addMouseListener(new Object(this));
/*     */     }
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
/* 333 */     addMouseMotionListener(new Object(this, statusText));
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
/* 345 */     content.setOpaque(true);
/* 346 */     content.add(scroll, "Center");
/* 347 */     content.add(statusBar, "South");
/* 348 */     f.setPreferredSize(new Dimension(800, 500));
/* 349 */     f.setJMenuBar(menuBar);
/* 350 */     f.setContentPane(content);
/* 351 */     f.pack();
/* 352 */     f.setLocation(25, 25);
/* 353 */     f.setVisible(true);
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 357 */     JMenuItem item = (JMenuItem)e.getSource();
/* 358 */     if (item.getText().equals("Zoom In")) {
/* 359 */       this.zoom += 0.2D;
/* 360 */       setPreferredSize(new Dimension((int)(getWidth() * 1.2D), (int)(getHeight() * 1.2D)));
/* 361 */       revalidate();
/* 362 */       repaint();
/*     */     } 
/* 364 */     if (item.getText().equals("Zoom Out") && 
/* 365 */       this.zoom >= 0.4D) {
/* 366 */       this.zoom -= 0.2D;
/* 367 */       setPreferredSize(new Dimension((int)(getWidth() / 1.2D), (int)(getHeight() / 1.2D)));
/* 368 */       revalidate();
/* 369 */       repaint();
/*     */     } 
/*     */     
/* 372 */     if (item.getText().equals("Save"))
/*     */       try {
/* 374 */         this.title = this.title.replaceAll("/", "_");
/* 375 */         FileOutputStream out = new FileOutputStream("../../results/" + this.title + ".png");
/* 376 */         BufferedImage img = new BufferedImage(getWidth(), getHeight(), 1);
/* 377 */         Graphics2D g = img.createGraphics();
/* 378 */         g.setClip(0, 0, getWidth(), getHeight());
/* 379 */         g.setColor(Color.WHITE);
/* 380 */         g.fillRect(0, 0, getWidth(), getHeight());
/* 381 */         paint(g);
/* 382 */         g.dispose();
/* 383 */         ImageIO.write(img, "png", out);
/* 384 */         out.close();
/* 385 */       } catch (Exception ex) {
/* 386 */         System.out.println("jMondrian : Error saving the image - " + ex);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\view\ViewRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */