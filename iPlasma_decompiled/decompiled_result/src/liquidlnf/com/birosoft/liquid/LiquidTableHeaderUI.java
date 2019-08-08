/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import com.birosoft.liquid.skin.Skin;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.Enumeration;
/*     */ import javax.swing.CellRendererPane;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.event.MouseInputListener;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.TableHeaderUI;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableCellRenderer;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LiquidTableHeaderUI
/*     */   extends TableHeaderUI
/*     */ {
/*  41 */   private static Cursor resizeCursor = Cursor.getPredefinedCursor(11);
/*     */   protected static final int HEADER_HEIGHT = 22;
/*  43 */   private int columnSelected = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Skin skin;
/*     */ 
/*     */ 
/*     */   
/*     */   protected JTableHeader header;
/*     */ 
/*     */ 
/*     */   
/*     */   protected CellRendererPane rendererPane;
/*     */ 
/*     */ 
/*     */   
/*     */   protected MouseInputListener mouseInputListener;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   protected MouseInputListener createMouseInputListener() { return new MouseInputHandler(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public static ComponentUI createUI(JComponent h) { return new LiquidTableHeaderUI(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  77 */     this.header = (JTableHeader)c;
/*     */     
/*  79 */     this.rendererPane = new CellRendererPane();
/*  80 */     this.header.add(this.rendererPane);
/*     */     
/*  82 */     installDefaults();
/*  83 */     installListeners();
/*  84 */     installKeyboardActions();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   protected void installDefaults() { LookAndFeel.installColorsAndFont(this.header, "TableHeader.background", "TableHeader.foreground", "TableHeader.font"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 104 */     this.mouseInputListener = createMouseInputListener();
/*     */     
/* 106 */     this.header.addMouseListener(this.mouseInputListener);
/* 107 */     this.header.addMouseMotionListener(this.mouseInputListener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void installKeyboardActions() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void uninstallUI(JComponent c) {
/* 118 */     uninstallDefaults();
/* 119 */     uninstallListeners();
/* 120 */     uninstallKeyboardActions();
/*     */     
/* 122 */     this.header.remove(this.rendererPane);
/* 123 */     this.rendererPane = null;
/* 124 */     this.header = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {}
/*     */   
/*     */   protected void uninstallListeners() {
/* 131 */     this.header.removeMouseListener(this.mouseInputListener);
/* 132 */     this.header.removeMouseMotionListener(this.mouseInputListener);
/*     */     
/* 134 */     this.mouseInputListener = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void uninstallKeyboardActions() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 144 */     if (this.header.getColumnModel().getColumnCount() <= 0) {
/*     */       return;
/*     */     }
/*     */     
/* 148 */     boolean ltr = this.header.getComponentOrientation().isLeftToRight();
/*     */     
/* 150 */     Rectangle clip = g.getClipBounds();
/* 151 */     Point left = clip.getLocation();
/* 152 */     Point right = new Point(clip.x + clip.width - 1, clip.y);
/* 153 */     TableColumnModel cm = this.header.getColumnModel();
/* 154 */     int cMin = this.header.columnAtPoint(ltr ? left : right);
/* 155 */     int cMax = this.header.columnAtPoint(ltr ? right : left);
/*     */ 
/*     */     
/* 158 */     if (cMin == -1) {
/* 159 */       cMin = 0;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 164 */     if (cMax == -1) {
/* 165 */       cMax = cm.getColumnCount() - 1;
/*     */     }
/*     */     
/* 168 */     TableColumn draggedColumn = this.header.getDraggedColumn();
/*     */     
/* 170 */     int columnMargin = cm.getColumnMargin();
/* 171 */     Rectangle cellRect = this.header.getHeaderRect(cMin);
/*     */ 
/*     */     
/* 174 */     if (ltr) {
/* 175 */       for (int column = cMin; column <= cMax; column++) {
/* 176 */         TableColumn aColumn = cm.getColumn(column);
/* 177 */         int columnWidth = aColumn.getWidth();
/* 178 */         cellRect.width = columnWidth - columnMargin;
/*     */         
/* 180 */         if (aColumn != draggedColumn) {
/* 181 */           paintCell(g, cellRect, column);
/*     */         }
/*     */         
/* 184 */         cellRect.x += columnWidth;
/*     */       } 
/*     */     } else {
/* 187 */       TableColumn aColumn = cm.getColumn(cMin);
/*     */       
/* 189 */       if (aColumn != draggedColumn) {
/* 190 */         int columnWidth = aColumn.getWidth();
/* 191 */         cellRect.width = columnWidth - columnMargin;
/* 192 */         cellRect.x += columnMargin;
/* 193 */         paintCell(g, cellRect, cMin);
/*     */       } 
/*     */       
/* 196 */       for (int column = cMin + 1; column <= cMax; column++) {
/* 197 */         aColumn = cm.getColumn(column);
/* 198 */         int columnWidth = aColumn.getWidth();
/* 199 */         cellRect.width = columnWidth - columnMargin;
/* 200 */         cellRect.x -= columnWidth;
/*     */         
/* 202 */         if (aColumn != draggedColumn) {
/* 203 */           paintCell(g, cellRect, column);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 209 */     if (draggedColumn != null) {
/* 210 */       int draggedColumnIndex = viewIndexForColumn(draggedColumn);
/* 211 */       Rectangle draggedCellRect = this.header.getHeaderRect(draggedColumnIndex);
/*     */ 
/*     */       
/* 214 */       g.setColor(this.header.getParent().getBackground());
/* 215 */       g.fillRect(draggedCellRect.x, draggedCellRect.y, draggedCellRect.width, draggedCellRect.height);
/*     */ 
/*     */       
/* 218 */       draggedCellRect.x += this.header.getDraggedDistance();
/*     */ 
/*     */       
/* 221 */       g.setColor(this.header.getBackground());
/* 222 */       g.fillRect(draggedCellRect.x, draggedCellRect.y, draggedCellRect.width, draggedCellRect.height);
/*     */ 
/*     */       
/* 225 */       paintCell(g, draggedCellRect, draggedColumnIndex);
/*     */     } 
/*     */ 
/*     */     
/* 229 */     this.rendererPane.removeAll();
/*     */   }
/*     */   
/*     */   private Component getHeaderRenderer(int columnIndex) {
/* 233 */     TableColumn aColumn = this.header.getColumnModel().getColumn(columnIndex);
/* 234 */     TableCellRenderer renderer = aColumn.getHeaderRenderer();
/*     */     
/* 236 */     if (renderer == null) {
/* 237 */       renderer = this.header.getDefaultRenderer();
/*     */     }
/*     */     
/* 240 */     return renderer.getTableCellRendererComponent(this.header.getTable(), aColumn.getHeaderValue(), false, false, -1, columnIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   private void paintCell(Graphics g, Rectangle cellRect, int columnIndex) {
/* 245 */     int index = 0;
/* 246 */     Component component = getHeaderRenderer(columnIndex);
/*     */     
/* 248 */     if (columnIndex == this.columnSelected) {
/* 249 */       index = 1;
/*     */     }
/*     */     
/* 252 */     getSkin().draw(g, index, cellRect.x, cellRect.y, cellRect.width, cellRect.height);
/*     */     
/* 254 */     this.rendererPane.paintComponent(g, component, this.header, cellRect.x, cellRect.y, cellRect.width, cellRect.height, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private int viewIndexForColumn(TableColumn aColumn) {
/* 259 */     TableColumnModel cm = this.header.getColumnModel();
/*     */     
/* 261 */     for (int column = 0; column < cm.getColumnCount(); column++) {
/* 262 */       if (cm.getColumn(column) == aColumn) {
/* 263 */         return column;
/*     */       }
/*     */     } 
/*     */     
/* 267 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 306 */   private int getHeaderHeight() { return 22; }
/*     */ 
/*     */   
/*     */   private Dimension createHeaderSize(long width) {
/* 310 */     TableColumnModel columnModel = this.header.getColumnModel();
/*     */ 
/*     */     
/* 313 */     if (width > 2147483647L) {
/* 314 */       width = 2147483647L;
/*     */     }
/*     */     
/* 317 */     return new Dimension((int)width, getHeaderHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize(JComponent c) {
/* 325 */     long width = 0L;
/* 326 */     Enumeration enumeration = this.header.getColumnModel().getColumns();
/*     */     
/* 328 */     while (enumeration.hasMoreElements()) {
/* 329 */       TableColumn aColumn = (TableColumn)enumeration.nextElement();
/* 330 */       width += aColumn.getMinWidth();
/*     */     } 
/*     */     
/* 333 */     return createHeaderSize(width);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 343 */     long width = 0L;
/* 344 */     Enumeration enumeration = this.header.getColumnModel().getColumns();
/*     */     
/* 346 */     while (enumeration.hasMoreElements()) {
/* 347 */       TableColumn aColumn = (TableColumn)enumeration.nextElement();
/* 348 */       width += aColumn.getPreferredWidth();
/*     */     } 
/*     */     
/* 351 */     return createHeaderSize(width);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize(JComponent c) {
/* 359 */     long width = 0L;
/* 360 */     Enumeration enumeration = this.header.getColumnModel().getColumns();
/*     */     
/* 362 */     while (enumeration.hasMoreElements()) {
/* 363 */       TableColumn aColumn = (TableColumn)enumeration.nextElement();
/* 364 */       width += aColumn.getMaxWidth();
/*     */     } 
/*     */     
/* 367 */     return createHeaderSize(width);
/*     */   }
/*     */   
/*     */   public Skin getSkin() {
/* 371 */     if (this.skin == null) {
/* 372 */       this.skin = new Skin("toolbar.png", 8, 4, 13, 4, 10);
/*     */     }
/*     */     
/* 375 */     return this.skin;
/*     */   }
/*     */   
/*     */   public class MouseInputHandler
/*     */     implements MouseInputListener {
/*     */     private int mouseXOffset;
/*     */     
/*     */     public MouseInputHandler(LiquidTableHeaderUI this$0) {
/* 383 */       this.this$0 = this$0;
/*     */       
/* 385 */       this.otherCursor = resizeCursor;
/*     */     }
/*     */     private Cursor otherCursor; private final LiquidTableHeaderUI this$0;
/*     */     
/*     */     public void mouseClicked(MouseEvent e) {}
/*     */     
/* 391 */     private boolean canResize(TableColumn column) { return (column != null && this.this$0.header.getResizingAllowed() && column.getResizable()); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 396 */     private TableColumn getResizingColumn(Point p) { return getResizingColumn(p, this.this$0.header.columnAtPoint(p)); }
/*     */     
/*     */     private TableColumn getResizingColumn(Point p, int column) {
/*     */       int columnIndex;
/* 400 */       if (column == -1) {
/* 401 */         return null;
/*     */       }
/*     */       
/* 404 */       Rectangle r = this.this$0.header.getHeaderRect(column);
/* 405 */       r.grow(-3, 0);
/*     */       
/* 407 */       if (r.contains(p)) {
/* 408 */         return null;
/*     */       }
/*     */       
/* 411 */       int midPoint = r.x + r.width / 2;
/*     */ 
/*     */       
/* 414 */       if (this.this$0.header.getComponentOrientation().isLeftToRight()) {
/* 415 */         columnIndex = (p.x < midPoint) ? (column - 1) : column;
/*     */       } else {
/* 417 */         columnIndex = (p.x < midPoint) ? column : (column - 1);
/*     */       } 
/*     */       
/* 420 */       if (columnIndex == -1) {
/* 421 */         return null;
/*     */       }
/*     */       
/* 424 */       return this.this$0.header.getColumnModel().getColumn(columnIndex);
/*     */     }
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 428 */       this.this$0.header.setDraggedColumn(null);
/* 429 */       this.this$0.header.setResizingColumn(null);
/* 430 */       this.this$0.header.setDraggedDistance(0);
/*     */       
/* 432 */       Point p = e.getPoint();
/*     */ 
/*     */       
/* 435 */       TableColumnModel columnModel = this.this$0.header.getColumnModel();
/* 436 */       int index = this.this$0.header.columnAtPoint(p);
/*     */       
/* 438 */       if (index != -1) {
/*     */         
/* 440 */         TableColumn resizingColumn = getResizingColumn(p, index);
/*     */         
/* 442 */         if (canResize(resizingColumn)) {
/* 443 */           this.this$0.header.setResizingColumn(resizingColumn);
/*     */           
/* 445 */           if (this.this$0.header.getComponentOrientation().isLeftToRight()) {
/* 446 */             this.mouseXOffset = p.x - resizingColumn.getWidth();
/*     */           } else {
/* 448 */             this.mouseXOffset = p.x + resizingColumn.getWidth();
/*     */           } 
/* 450 */         } else if (this.this$0.header.getReorderingAllowed()) {
/* 451 */           TableColumn hitColumn = columnModel.getColumn(index);
/* 452 */           this.this$0.header.setDraggedColumn(hitColumn);
/* 453 */           this.mouseXOffset = p.x;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private void swapCursor() {
/* 459 */       Cursor tmp = this.this$0.header.getCursor();
/* 460 */       this.this$0.header.setCursor(this.otherCursor);
/* 461 */       this.otherCursor = tmp;
/*     */     }
/*     */     
/*     */     public void mouseMoved(MouseEvent e) {
/* 465 */       if (canResize(getResizingColumn(e.getPoint())) != ((this.this$0.header.getCursor() == resizeCursor))) {
/* 466 */         swapCursor();
/*     */       }
/*     */       
/* 469 */       Point p = e.getPoint();
/* 470 */       TableColumnModel columnModel = this.this$0.header.getColumnModel();
/* 471 */       int index = this.this$0.header.columnAtPoint(p);
/*     */       
/* 473 */       if (index != this.this$0.columnSelected) {
/* 474 */         this.this$0.columnSelected = index;
/* 475 */         this.this$0.header.repaint();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void mouseDragged(MouseEvent e) {
/* 480 */       int mouseX = e.getX();
/*     */       
/* 482 */       TableColumn resizingColumn = this.this$0.header.getResizingColumn();
/* 483 */       TableColumn draggedColumn = this.this$0.header.getDraggedColumn();
/*     */       
/* 485 */       boolean headerLeftToRight = this.this$0.header.getComponentOrientation().isLeftToRight();
/*     */ 
/*     */       
/* 488 */       if (resizingColumn != null) {
/* 489 */         int newWidth, oldWidth = resizingColumn.getWidth();
/*     */ 
/*     */         
/* 492 */         if (headerLeftToRight) {
/* 493 */           newWidth = mouseX - this.mouseXOffset;
/*     */         } else {
/* 495 */           newWidth = this.mouseXOffset - mouseX;
/*     */         } 
/*     */         
/* 498 */         resizingColumn.setWidth(newWidth);
/*     */         
/*     */         Container container;
/*     */         
/* 502 */         if (this.this$0.header.getParent() == null || (container = this.this$0.header.getParent().getParent()) == null || !(container instanceof JScrollPane)) {
/*     */           return;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 508 */         if (!container.getComponentOrientation().isLeftToRight() && !headerLeftToRight) {
/*     */           
/* 510 */           JTable table = this.this$0.header.getTable();
/*     */           
/* 512 */           if (table != null) {
/* 513 */             JViewport viewport = ((JScrollPane)container).getViewport();
/* 514 */             int viewportWidth = viewport.getWidth();
/* 515 */             int diff = newWidth - oldWidth;
/* 516 */             int newHeaderWidth = table.getWidth() + diff;
/*     */ 
/*     */             
/* 519 */             Dimension tableSize = table.getSize();
/* 520 */             tableSize.width += diff;
/* 521 */             table.setSize(tableSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 527 */             if (newHeaderWidth >= viewportWidth && table.getAutoResizeMode() == 0) {
/*     */               
/* 529 */               Point p = viewport.getViewPosition();
/* 530 */               p.x = Math.max(0, Math.min(newHeaderWidth - viewportWidth, p.x + diff));
/*     */ 
/*     */               
/* 533 */               viewport.setViewPosition(p);
/*     */ 
/*     */               
/* 536 */               this.mouseXOffset += diff;
/*     */             } 
/*     */           } 
/*     */         } 
/* 540 */       } else if (draggedColumn != null) {
/* 541 */         TableColumnModel cm = this.this$0.header.getColumnModel();
/* 542 */         int draggedDistance = mouseX - this.mouseXOffset;
/* 543 */         int direction = (draggedDistance < 0) ? -1 : 1;
/* 544 */         int columnIndex = this.this$0.viewIndexForColumn(draggedColumn);
/* 545 */         int newColumnIndex = columnIndex + (headerLeftToRight ? direction : -direction);
/*     */ 
/*     */         
/* 548 */         if (0 <= newColumnIndex && newColumnIndex < cm.getColumnCount()) {
/*     */           
/* 550 */           int width = cm.getColumn(newColumnIndex).getWidth();
/*     */           
/* 552 */           if (Math.abs(draggedDistance) > width / 2) {
/* 553 */             this.mouseXOffset += direction * width;
/* 554 */             this.this$0.header.setDraggedDistance(draggedDistance - direction * width);
/*     */             
/* 556 */             cm.moveColumn(columnIndex, newColumnIndex);
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */         
/* 562 */         setDraggedDistance(draggedDistance, columnIndex);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {
/* 567 */       setDraggedDistance(0, this.this$0.viewIndexForColumn(this.this$0.header.getDraggedColumn()));
/*     */       
/* 569 */       this.this$0.header.setResizingColumn(null);
/* 570 */       this.this$0.header.setDraggedColumn(null);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseEntered(MouseEvent e) {}
/*     */     
/*     */     public void mouseExited(MouseEvent e) {
/* 577 */       this.this$0.columnSelected = -1;
/* 578 */       this.this$0.header.repaint();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void setDraggedDistance(int draggedDistance, int column) {
/* 585 */       this.this$0.header.setDraggedDistance(draggedDistance);
/*     */       
/* 587 */       if (column != -1)
/* 588 */         this.this$0.header.getColumnModel().moveColumn(column, column); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidTableHeaderUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */