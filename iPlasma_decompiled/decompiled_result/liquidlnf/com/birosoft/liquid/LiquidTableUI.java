/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicTableUI;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LiquidTableUI
/*     */   extends BasicTableUI
/*     */ {
/*     */   private Color defaultBackground;
/*     */   
/*  52 */   public static ComponentUI createUI(JComponent c) { return new LiquidTableUI(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public void installUI(JComponent c) { super.installUI(c); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public void uninstallUI(JComponent c) { super.uninstallUI(c); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/*  73 */     if (this.table.getRowCount() <= 0 || this.table.getColumnCount() <= 0) {
/*     */       return;
/*     */     }
/*     */     
/*  77 */     if (LiquidLookAndFeel.defaultRowBackgroundMode & ((this.defaultBackground == null) ? 1 : 0)) {
/*     */       
/*  79 */       this.defaultBackground = this.table.getBackground();
/*  80 */       this.table.setIntercellSpacing(new Dimension());
/*     */     } 
/*     */     
/*  83 */     Rectangle clip = g.getClipBounds();
/*  84 */     Point upperLeft = clip.getLocation();
/*  85 */     Point lowerRight = new Point(clip.x + clip.width - 1, clip.y + clip.height - 1);
/*     */     
/*  87 */     int rMin = this.table.rowAtPoint(upperLeft);
/*  88 */     int rMax = this.table.rowAtPoint(lowerRight);
/*     */ 
/*     */     
/*  91 */     if (rMin == -1) {
/*  92 */       rMin = 0;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  97 */     if (rMax == -1) {
/*  98 */       rMax = this.table.getRowCount() - 1;
/*     */     }
/*     */     
/* 101 */     boolean ltr = this.table.getComponentOrientation().isLeftToRight();
/* 102 */     int cMin = this.table.columnAtPoint(ltr ? upperLeft : lowerRight);
/* 103 */     int cMax = this.table.columnAtPoint(ltr ? lowerRight : upperLeft);
/*     */ 
/*     */     
/* 106 */     if (cMin == -1) {
/* 107 */       cMin = 0;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 112 */     if (cMax == -1) {
/* 113 */       cMax = this.table.getColumnCount() - 1;
/*     */     }
/*     */ 
/*     */     
/* 117 */     if (!LiquidLookAndFeel.defaultRowBackgroundMode) {
/* 118 */       paintGrid(g, rMin, rMax, cMin, cMax);
/*     */     }
/*     */ 
/*     */     
/* 122 */     paintCells(g, rMin, rMax, cMin, cMax);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void paintGrid(Graphics g, int rMin, int rMax, int cMin, int cMax) {
/* 133 */     g.setColor(this.table.getGridColor());
/*     */     
/* 135 */     Rectangle minCell = this.table.getCellRect(rMin, cMin, true);
/* 136 */     Rectangle maxCell = this.table.getCellRect(rMax, cMax, true);
/* 137 */     Rectangle damagedArea = minCell.union(maxCell);
/*     */     
/* 139 */     if (this.table.getShowHorizontalLines()) {
/* 140 */       int tableWidth = damagedArea.x + damagedArea.width;
/* 141 */       int y = damagedArea.y;
/*     */       
/* 143 */       for (int row = rMin; row <= rMax; row++) {
/* 144 */         y += this.table.getRowHeight(row);
/* 145 */         g.drawLine(damagedArea.x, y - 1, tableWidth - 1, y - 1);
/*     */       } 
/*     */     } 
/*     */     
/* 149 */     if (this.table.getShowVerticalLines()) {
/* 150 */       TableColumnModel cm = this.table.getColumnModel();
/* 151 */       int tableHeight = damagedArea.y + damagedArea.height;
/*     */ 
/*     */       
/* 154 */       if (this.table.getComponentOrientation().isLeftToRight()) {
/* 155 */         int x = damagedArea.x;
/*     */         
/* 157 */         for (int column = cMin; column <= cMax; column++) {
/* 158 */           int w = cm.getColumn(column).getWidth();
/* 159 */           x += w;
/* 160 */           g.drawLine(x - 1, 0, x - 1, tableHeight - 1);
/*     */         } 
/*     */       } else {
/* 163 */         int x = damagedArea.x + damagedArea.width;
/*     */         
/* 165 */         for (int column = cMin; column < cMax; column++) {
/* 166 */           int w = cm.getColumn(column).getWidth();
/* 167 */           x -= w;
/* 168 */           g.drawLine(x - 1, 0, x - 1, tableHeight - 1);
/*     */         } 
/*     */         
/* 171 */         x -= cm.getColumn(cMax).getWidth();
/* 172 */         g.drawLine(x, 0, x, tableHeight - 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int viewIndexForColumn(TableColumn aColumn) {
/* 178 */     TableColumnModel cm = this.table.getColumnModel();
/*     */     
/* 180 */     for (int column = 0; column < cm.getColumnCount(); column++) {
/* 181 */       if (cm.getColumn(column) == aColumn) {
/* 182 */         return column;
/*     */       }
/*     */     } 
/*     */     
/* 186 */     return -1;
/*     */   }
/*     */   
/*     */   private void paintCells(Graphics g, int rMin, int rMax, int cMin, int cMax) {
/* 190 */     JTableHeader header = this.table.getTableHeader();
/* 191 */     TableColumn draggedColumn = (header == null) ? null : header.getDraggedColumn();
/*     */ 
/*     */     
/* 194 */     TableColumnModel cm = this.table.getColumnModel();
/* 195 */     int columnMargin = cm.getColumnMargin();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 201 */     if (this.table.getComponentOrientation().isLeftToRight()) {
/* 202 */       for (int row = rMin; row <= rMax; row++) {
/* 203 */         Rectangle cellRect = this.table.getCellRect(row, cMin, false);
/*     */         
/* 205 */         for (int column = cMin; column <= cMax; column++) {
/* 206 */           TableColumn aColumn = cm.getColumn(column);
/* 207 */           int columnWidth = aColumn.getWidth();
/* 208 */           cellRect.width = columnWidth - columnMargin;
/*     */           
/* 210 */           if (aColumn != draggedColumn) {
/* 211 */             paintCell(g, cellRect, row, column);
/*     */           }
/*     */           
/* 214 */           cellRect.x += columnWidth;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 218 */       for (int row = rMin; row <= rMax; row++) {
/* 219 */         Rectangle cellRect = this.table.getCellRect(row, cMin, false);
/* 220 */         TableColumn aColumn = cm.getColumn(cMin);
/*     */         
/* 222 */         if (aColumn != draggedColumn) {
/* 223 */           int columnWidth = aColumn.getWidth();
/* 224 */           cellRect.width = columnWidth - columnMargin;
/* 225 */           paintCell(g, cellRect, row, cMin);
/*     */         } 
/*     */         
/* 228 */         for (int column = cMin + 1; column <= cMax; column++) {
/* 229 */           aColumn = cm.getColumn(column);
/* 230 */           int columnWidth = aColumn.getWidth();
/* 231 */           cellRect.width = columnWidth - columnMargin;
/* 232 */           cellRect.x -= columnWidth;
/*     */           
/* 234 */           if (aColumn != draggedColumn) {
/* 235 */             paintCell(g, cellRect, row, column);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 242 */     if (draggedColumn != null) {
/* 243 */       paintDraggedArea(g, rMin, rMax, draggedColumn, header.getDraggedDistance());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 248 */     this.rendererPane.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   private void paintDraggedArea(Graphics g, int rMin, int rMax, TableColumn draggedColumn, int distance) {
/* 253 */     int draggedColumnIndex = viewIndexForColumn(draggedColumn);
/*     */     
/* 255 */     Rectangle minCell = this.table.getCellRect(rMin, draggedColumnIndex, true);
/* 256 */     Rectangle maxCell = this.table.getCellRect(rMax, draggedColumnIndex, true);
/*     */     
/* 258 */     Rectangle vacatedColumnRect = minCell.union(maxCell);
/*     */ 
/*     */     
/* 261 */     g.setColor(this.table.getParent().getBackground());
/* 262 */     g.fillRect(vacatedColumnRect.x, vacatedColumnRect.y, vacatedColumnRect.width, vacatedColumnRect.height);
/*     */ 
/*     */ 
/*     */     
/* 266 */     vacatedColumnRect.x += distance;
/*     */ 
/*     */     
/* 269 */     g.setColor(this.table.getBackground());
/* 270 */     g.fillRect(vacatedColumnRect.x, vacatedColumnRect.y, vacatedColumnRect.width, vacatedColumnRect.height);
/*     */ 
/*     */ 
/*     */     
/* 274 */     if (this.table.getShowVerticalLines()) {
/* 275 */       g.setColor(this.table.getGridColor());
/*     */       
/* 277 */       int x1 = vacatedColumnRect.x;
/* 278 */       int y1 = vacatedColumnRect.y;
/* 279 */       int x2 = x1 + vacatedColumnRect.width - 1;
/* 280 */       int y2 = y1 + vacatedColumnRect.height - 1;
/*     */ 
/*     */       
/* 283 */       g.drawLine(x1 - 1, y1, x1 - 1, y2);
/*     */ 
/*     */       
/* 286 */       g.drawLine(x2, y1, x2, y2);
/*     */     } 
/*     */     
/* 289 */     for (int row = rMin; row <= rMax; row++) {
/*     */       
/* 291 */       Rectangle r = this.table.getCellRect(row, draggedColumnIndex, false);
/* 292 */       r.x += distance;
/* 293 */       paintCell(g, r, row, draggedColumnIndex);
/*     */ 
/*     */       
/* 296 */       if (this.table.getShowHorizontalLines()) {
/* 297 */         g.setColor(this.table.getGridColor());
/*     */         
/* 299 */         Rectangle rcr = this.table.getCellRect(row, draggedColumnIndex, true);
/* 300 */         rcr.x += distance;
/*     */         
/* 302 */         int x1 = rcr.x;
/* 303 */         int y1 = rcr.y;
/* 304 */         int x2 = x1 + rcr.width - 1;
/* 305 */         int y2 = y1 + rcr.height - 1;
/* 306 */         g.drawLine(x1, y2, x2, y2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void paintCell(Graphics g, Rectangle cellRect, int row, int column) {
/* 312 */     if (this.table.isEditing() && this.table.getEditingRow() == row && this.table.getEditingColumn() == column) {
/*     */       
/* 314 */       Component component = this.table.getEditorComponent();
/* 315 */       component.setBounds(cellRect);
/* 316 */       component.validate();
/*     */     } else {
/* 318 */       TableCellRenderer renderer = this.table.getCellRenderer(row, column);
/* 319 */       Component component = this.table.prepareRenderer(renderer, row, column);
/*     */       
/* 321 */       if (LiquidLookAndFeel.defaultRowBackgroundMode & (!this.table.isCellSelected(row, column) ? 1 : 0))
/*     */       {
/* 323 */         if (row % 2 == 0) {
/* 324 */           if (this.defaultBackground.equals(component.getBackground())) {
/* 325 */             component.setBackground(LiquidLookAndFeel.getDesktopColor());
/*     */           }
/*     */         }
/* 328 */         else if (LiquidLookAndFeel.getDesktopColor().equals(component.getBackground())) {
/* 329 */           component.setBackground(this.defaultBackground);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 334 */       this.rendererPane.paintComponent(g, component, this.table, cellRect.x, cellRect.y, cellRect.width, cellRect.height, true);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidTableUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */