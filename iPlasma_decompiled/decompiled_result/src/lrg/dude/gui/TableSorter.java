/*     */ package lrg.dude.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.event.TableModelEvent;
/*     */ import javax.swing.event.TableModelListener;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableCellRenderer;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TableSorter
/*     */   extends AbstractTableModel
/*     */ {
/*  82 */   private static Directive EMPTY_DIRECTIVE = new Directive(-1, 0);
/*     */   
/*  84 */   public static final Comparator COMPARABLE_COMPARATOR = new Comparator()
/*     */     {
/*  86 */       public int compare(Object o1, Object o2) { return ((Comparable)o1).compareTo(o2); }
/*     */     };
/*     */   
/*  89 */   public static final Comparator LEXICAL_COMPARATOR = new Comparator()
/*     */     {
/*  91 */       public int compare(Object o1, Object o2) { return o1.toString().compareTo(o2.toString()); }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   private Map columnComparators = new HashMap();
/* 102 */   private List sortingColumns = new ArrayList();
/*     */ 
/*     */   
/* 105 */   private MouseListener mouseListener = new MouseHandler(null);
/* 106 */   private TableModelListener tableModelListener = new TableModelHandler(null); protected TableModel tableModel; public static final int DESCENDING = -1;
/*     */   public static final int NOT_SORTED = 0;
/*     */   
/*     */   public TableSorter(TableModel tableModel) {
/* 110 */     this();
/* 111 */     setTableModel(tableModel);
/*     */   }
/*     */   public static final int ASCENDING = 1; private Row[] viewToModel; private int[] modelToView; private JTableHeader tableHeader;
/*     */   public TableSorter(TableModel tableModel, JTableHeader tableHeader) {
/* 115 */     this();
/* 116 */     setTableHeader(tableHeader);
/* 117 */     setTableModel(tableModel);
/*     */   }
/*     */   
/*     */   private void clearSortingState() {
/* 121 */     this.viewToModel = null;
/* 122 */     this.modelToView = null;
/*     */   }
/*     */ 
/*     */   
/* 126 */   public TableModel getTableModel() { return this.tableModel; }
/*     */ 
/*     */   
/*     */   public void setTableModel(TableModel tableModel) {
/* 130 */     if (this.tableModel != null) {
/* 131 */       this.tableModel.removeTableModelListener(this.tableModelListener);
/*     */     }
/*     */     
/* 134 */     this.tableModel = tableModel;
/* 135 */     if (this.tableModel != null) {
/* 136 */       this.tableModel.addTableModelListener(this.tableModelListener);
/*     */     }
/*     */     
/* 139 */     clearSortingState();
/* 140 */     fireTableStructureChanged();
/*     */   }
/*     */ 
/*     */   
/* 144 */   public JTableHeader getTableHeader() { return this.tableHeader; }
/*     */ 
/*     */   
/*     */   public void setTableHeader(JTableHeader tableHeader) {
/* 148 */     if (this.tableHeader != null) {
/* 149 */       this.tableHeader.removeMouseListener(this.mouseListener);
/* 150 */       TableCellRenderer defaultRenderer = this.tableHeader.getDefaultRenderer();
/* 151 */       if (defaultRenderer instanceof SortableHeaderRenderer) {
/* 152 */         this.tableHeader.setDefaultRenderer(((SortableHeaderRenderer)defaultRenderer).tableCellRenderer);
/*     */       }
/*     */     } 
/* 155 */     this.tableHeader = tableHeader;
/* 156 */     if (this.tableHeader != null) {
/* 157 */       this.tableHeader.addMouseListener(this.mouseListener);
/* 158 */       this.tableHeader.setDefaultRenderer(new SortableHeaderRenderer(this.tableHeader.getDefaultRenderer()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 163 */   public boolean isSorting() { return (this.sortingColumns.size() != 0); }
/*     */ 
/*     */   
/*     */   private Directive getDirective(int column) {
/* 167 */     for (int i = 0; i < this.sortingColumns.size(); i++) {
/* 168 */       Directive directive = (Directive)this.sortingColumns.get(i);
/* 169 */       if (directive.column == column) {
/* 170 */         return directive;
/*     */       }
/*     */     } 
/* 173 */     return EMPTY_DIRECTIVE;
/*     */   }
/*     */ 
/*     */   
/* 177 */   public int getSortingStatus(int column) { return (getDirective(column)).direction; }
/*     */ 
/*     */   
/*     */   private void sortingStatusChanged() {
/* 181 */     clearSortingState();
/* 182 */     fireTableDataChanged();
/* 183 */     this.tableHeader.repaint();
/*     */   }
/*     */   
/*     */   public void setSortingStatus(int column, int status) {
/* 187 */     Directive directive = getDirective(column);
/* 188 */     if (directive != EMPTY_DIRECTIVE) {
/* 189 */       this.sortingColumns.remove(directive);
/*     */     }
/* 191 */     if (status != 0) {
/* 192 */       this.sortingColumns.add(new Directive(column, status));
/*     */     }
/* 194 */     sortingStatusChanged();
/*     */   }
/*     */   
/*     */   protected Icon getHeaderRendererIcon(int column, int size) {
/* 198 */     Directive directive = getDirective(column);
/* 199 */     if (directive == EMPTY_DIRECTIVE) {
/* 200 */       return null;
/*     */     }
/* 202 */     return new Arrow((directive.direction == -1), size, this.sortingColumns.indexOf(directive));
/*     */   }
/*     */   
/*     */   private void cancelSorting() {
/* 206 */     this.sortingColumns.clear();
/* 207 */     sortingStatusChanged();
/*     */   }
/*     */   
/*     */   public void setColumnComparator(Class type, Comparator comparator) {
/* 211 */     if (comparator == null) {
/* 212 */       this.columnComparators.remove(type);
/*     */     } else {
/* 214 */       this.columnComparators.put(type, comparator);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Comparator getComparator(int column) {
/* 219 */     Class columnType = this.tableModel.getColumnClass(column);
/* 220 */     Comparator comparator = (Comparator)this.columnComparators.get(columnType);
/* 221 */     if (comparator != null) {
/* 222 */       return comparator;
/*     */     }
/* 224 */     if (Comparable.class.isAssignableFrom(columnType)) {
/* 225 */       return COMPARABLE_COMPARATOR;
/*     */     }
/* 227 */     return LEXICAL_COMPARATOR;
/*     */   }
/*     */   
/*     */   private Row[] getViewToModel() {
/* 231 */     if (this.viewToModel == null) {
/* 232 */       int tableModelRowCount = this.tableModel.getRowCount();
/* 233 */       this.viewToModel = new Row[tableModelRowCount];
/* 234 */       for (int row = 0; row < tableModelRowCount; row++) {
/* 235 */         this.viewToModel[row] = new Row(row);
/*     */       }
/*     */       
/* 238 */       if (isSorting()) {
/* 239 */         Arrays.sort(this.viewToModel);
/*     */       }
/*     */     } 
/* 242 */     return this.viewToModel;
/*     */   }
/*     */ 
/*     */   
/* 246 */   public int modelIndex(int viewIndex) { return (getViewToModel()[viewIndex]).modelIndex; }
/*     */ 
/*     */   
/*     */   private int[] getModelToView() {
/* 250 */     if (this.modelToView == null) {
/* 251 */       int n = getViewToModel().length;
/* 252 */       this.modelToView = new int[n];
/* 253 */       for (int i = 0; i < n; i++) {
/* 254 */         this.modelToView[modelIndex(i)] = i;
/*     */       }
/*     */     } 
/* 257 */     return this.modelToView;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   public int getRowCount() { return (this.tableModel == null) ? 0 : this.tableModel.getRowCount(); }
/*     */ 
/*     */ 
/*     */   
/* 267 */   public int getColumnCount() { return (this.tableModel == null) ? 0 : this.tableModel.getColumnCount(); }
/*     */ 
/*     */ 
/*     */   
/* 271 */   public String getColumnName(int column) { return this.tableModel.getColumnName(column); }
/*     */ 
/*     */ 
/*     */   
/* 275 */   public Class getColumnClass(int column) { return this.tableModel.getColumnClass(column); }
/*     */ 
/*     */ 
/*     */   
/* 279 */   public boolean isCellEditable(int row, int column) { return this.tableModel.isCellEditable(modelIndex(row), column); }
/*     */ 
/*     */ 
/*     */   
/* 283 */   public Object getValueAt(int row, int column) { return this.tableModel.getValueAt(modelIndex(row), column); }
/*     */ 
/*     */ 
/*     */   
/* 287 */   public void setValueAt(Object aValue, int row, int column) { this.tableModel.setValueAt(aValue, modelIndex(row), column); }
/*     */   
/*     */   public TableSorter() {}
/*     */   
/*     */   private class Row
/*     */     implements Comparable
/*     */   {
/*     */     private int modelIndex;
/*     */     
/* 296 */     public Row(int index) { this.modelIndex = index; }
/*     */ 
/*     */     
/*     */     public int compareTo(Object o) {
/* 300 */       int row1 = this.modelIndex;
/* 301 */       int row2 = ((Row)o).modelIndex;
/*     */       
/* 303 */       for (Iterator it = TableSorter.this.sortingColumns.iterator(); it.hasNext(); ) {
/* 304 */         TableSorter.Directive directive = (TableSorter.Directive)it.next();
/* 305 */         int column = directive.column;
/* 306 */         Object o1 = TableSorter.this.tableModel.getValueAt(row1, column);
/* 307 */         Object o2 = TableSorter.this.tableModel.getValueAt(row2, column);
/*     */         
/* 309 */         int comparison = 0;
/*     */         
/* 311 */         if (o1 == null && o2 == null) {
/* 312 */           comparison = 0;
/* 313 */         } else if (o1 == null) {
/* 314 */           comparison = -1;
/* 315 */         } else if (o2 == null) {
/* 316 */           comparison = 1;
/*     */         } else {
/* 318 */           comparison = TableSorter.this.getComparator(column).compare(o1, o2);
/*     */         } 
/* 320 */         if (comparison != 0) {
/* 321 */           return (directive.direction == -1) ? -comparison : comparison;
/*     */         }
/*     */       } 
/* 324 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   private class TableModelHandler implements TableModelListener { private TableModelHandler() {}
/*     */     
/*     */     public void tableChanged(TableModelEvent e) {
/* 331 */       if (!TableSorter.this.isSorting()) {
/* 332 */         TableSorter.this.fireTableChanged(e);
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */ 
/*     */       
/* 339 */       if (e.getFirstRow() == -1) {
/* 340 */         TableSorter.this.cancelSorting();
/* 341 */         TableSorter.this.fireTableChanged(e);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 363 */       int column = e.getColumn();
/* 364 */       if (e.getFirstRow() == e.getLastRow() && 
/* 365 */         column != -1 && 
/* 366 */         TableSorter.this.getSortingStatus(column) == 0 && 
/* 367 */         TableSorter.this.modelToView != null) {
/* 368 */         int viewIndex = TableSorter.this.getModelToView()[e.getFirstRow()];
/* 369 */         TableSorter.this
/*     */           
/* 371 */           .fireTableChanged(new TableModelEvent(TableSorter.this, viewIndex, viewIndex, column, e.getType()));
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 376 */       TableSorter.this.clearSortingState();
/* 377 */       TableSorter.this.fireTableDataChanged();
/*     */     } }
/*     */   
/*     */   private class MouseHandler extends MouseAdapter {
/*     */     private MouseHandler() {}
/*     */     
/*     */     public void mouseClicked(MouseEvent e) {
/* 384 */       JTableHeader h = (JTableHeader)e.getSource();
/* 385 */       TableColumnModel columnModel = h.getColumnModel();
/* 386 */       int viewColumn = columnModel.getColumnIndexAtX(e.getX());
/* 387 */       int column = columnModel.getColumn(viewColumn).getModelIndex();
/* 388 */       if (column != -1) {
/* 389 */         int status = TableSorter.this.getSortingStatus(column);
/* 390 */         if (!e.isControlDown()) {
/* 391 */           TableSorter.this.cancelSorting();
/*     */         }
/*     */ 
/*     */         
/* 395 */         status += (e.isShiftDown() ? -1 : 1);
/* 396 */         status = (status + 1) % 3 - 1;
/* 397 */         TableSorter.this.setSortingStatus(column, status);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Arrow implements Icon {
/*     */     private boolean descending;
/*     */     private int size;
/*     */     private int priority;
/*     */     
/*     */     public Arrow(boolean descending, int size, int priority) {
/* 408 */       this.descending = descending;
/* 409 */       this.size = size;
/* 410 */       this.priority = priority;
/*     */     }
/*     */     
/*     */     public void paintIcon(Component c, Graphics g, int x, int y) {
/* 414 */       Color color = (c == null) ? Color.GRAY : c.getBackground();
/*     */ 
/*     */       
/* 417 */       int dx = (int)((this.size / 2) * Math.pow(0.8D, this.priority));
/* 418 */       int dy = this.descending ? dx : -dx;
/*     */       
/* 420 */       y = y + 5 * this.size / 6 + (this.descending ? -dy : 0);
/* 421 */       int shift = this.descending ? 1 : -1;
/* 422 */       g.translate(x, y);
/*     */ 
/*     */       
/* 425 */       g.setColor(color.darker());
/* 426 */       g.drawLine(dx / 2, dy, 0, 0);
/* 427 */       g.drawLine(dx / 2, dy + shift, 0, shift);
/*     */ 
/*     */       
/* 430 */       g.setColor(color.brighter());
/* 431 */       g.drawLine(dx / 2, dy, dx, 0);
/* 432 */       g.drawLine(dx / 2, dy + shift, dx, shift);
/*     */ 
/*     */       
/* 435 */       if (this.descending) {
/* 436 */         g.setColor(color.darker().darker());
/*     */       } else {
/* 438 */         g.setColor(color.brighter().brighter());
/*     */       } 
/* 440 */       g.drawLine(dx, 0, 0, 0);
/*     */       
/* 442 */       g.setColor(color);
/* 443 */       g.translate(-x, -y);
/*     */     }
/*     */ 
/*     */     
/* 447 */     public int getIconWidth() { return this.size; }
/*     */ 
/*     */ 
/*     */     
/* 451 */     public int getIconHeight() { return this.size; }
/*     */   }
/*     */   
/*     */   private class SortableHeaderRenderer
/*     */     implements TableCellRenderer
/*     */   {
/*     */     private TableCellRenderer tableCellRenderer;
/*     */     
/* 459 */     public SortableHeaderRenderer(TableCellRenderer tableCellRenderer) { this.tableCellRenderer = tableCellRenderer; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
/* 468 */       Component c = this.tableCellRenderer.getTableCellRendererComponent(table, value, 
/* 469 */           isSelected, 
/* 470 */           hasFocus, 
/* 471 */           row, column);
/* 472 */       if (c instanceof JLabel) {
/* 473 */         JLabel l = (JLabel)c;
/* 474 */         l.setHorizontalTextPosition(2);
/* 475 */         int modelColumn = table.convertColumnIndexToModel(column);
/* 476 */         l.setIcon(TableSorter.this.getHeaderRendererIcon(modelColumn, l.getFont().getSize()));
/*     */       } 
/* 478 */       return c;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Directive {
/*     */     private int column;
/*     */     private int direction;
/*     */     
/*     */     public Directive(int column, int direction) {
/* 487 */       this.column = column;
/* 488 */       this.direction = direction;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\gui\TableSorter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */