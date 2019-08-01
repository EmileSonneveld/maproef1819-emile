/*     */ package classes.lrg.insider.gui.ui.views;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.entities.ResultEntity;
/*     */ import lrg.insider.gui.ui.views.InsiderViewTableModel;
/*     */ import lrg.insider.gui.ui.views.sort.CoolSortStrategy;
/*     */ import lrg.insider.gui.ui.views.sort.SortStrategy;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InsiderViewTableModel
/*     */   extends DefaultTableModel
/*     */ {
/*     */   public static final boolean ASCENDING = false;
/*     */   public static final boolean DESCENDING = true;
/*     */   private int[] indexMap;
/*  25 */   private SortStrategy sortStrategy = new CoolSortStrategy();
/*     */ 
/*     */ 
/*     */   
/*  29 */   public boolean isCellEditable(int row, int col) { return false; }
/*     */ 
/*     */   
/*     */   private void clear() {
/*  33 */     while (getRowCount() > 0)
/*  34 */       removeRow(0); 
/*     */   }
/*     */   
/*     */   public void feedWithData(ArrayList columnNames, Iterator groupElementIterator) {
/*  38 */     clear();
/*     */     
/*  40 */     setColumnIdentifiers(columnNames.toArray());
/*  41 */     while (groupElementIterator.hasNext()) {
/*  42 */       AbstractEntityInterface currentEntity = (AbstractEntityInterface)groupElementIterator.next();
/*  43 */       if (currentEntity == null)
/*     */         continue; 
/*  45 */       ArrayList rowData = new ArrayList(columnNames.size());
/*  46 */       Iterator it = columnNames.iterator();
/*     */ 
/*     */       
/*  49 */       while (it.hasNext()) {
/*  50 */         String columnName = it.next().toString();
/*  51 */         int index = columnName.indexOf(".");
/*  52 */         if (index != -1) {
/*  53 */           String groupName = columnName.substring(0, index);
/*  54 */           String propertyName = columnName.substring(index + 1);
/*  55 */           int indexOfProp = propertyName.indexOf("_");
/*  56 */           GroupEntity crtGroup = currentEntity.getGroup(String.valueOf(groupName) + " group");
/*  57 */           rowData.add(crtGroup.getProperty(propertyName.substring(indexOfProp + 1)).aggregate(propertyName.substring(0, indexOfProp))); continue;
/*     */         } 
/*  59 */         rowData.add(currentEntity.getProperty(columnName));
/*     */       } 
/*  61 */       addRow(rowData.toArray());
/*     */     } 
/*     */     
/*  64 */     setSorterRowCount(getRowCount());
/*     */     
/*  66 */     if (this.dataVector.size() < 2)
/*     */       return; 
/*     */     try {
/*  69 */       Vector rowVector = (Vector)this.dataVector.elementAt(0);
/*  70 */       ResultEntity aResult = (ResultEntity)rowVector.elementAt(rowVector.size() - 1);
/*  71 */       if (aResult.getEntityType().getName().compareTo("string") == 0)
/*  72 */       { sortByColumn(getColumnCount() - 1, false); }
/*     */       else
/*  74 */       { sortByColumn(getColumnCount() - 1, true); } 
/*  75 */     } catch (ClassCastException e) {
/*  76 */       sortByColumn(getColumnCount() - 1, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sortByColumn(int column, boolean ascending) {
/*  82 */     ArrayList unsortedColumnValues = new ArrayList();
/*  83 */     for (int i = 0; i < getRowCount(); i++) {
/*  84 */       unsortedColumnValues.add(super.getValueAt(i, column));
/*     */     }
/*  86 */     this.sortStrategy.sort(unsortedColumnValues, this.indexMap, ascending);
/*     */   }
/*     */ 
/*     */   
/*  90 */   public Object getValueAt(int row, int column) { return super.getValueAt(this.indexMap[row], column); }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public int mapToGroupElementIndex(int selectedRow) { return this.indexMap[selectedRow]; }
/*     */ 
/*     */   
/*     */   private void setSorterRowCount(int rowCount) {
/*  98 */     this.indexMap = new int[rowCount];
/*  99 */     for (int i = 0; i < rowCount; i++)
/* 100 */       this.indexMap[i] = i; 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\views\InsiderViewTableModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */