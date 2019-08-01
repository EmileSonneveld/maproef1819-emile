/*    */ package lrg.dude.gui;
/*    */ 
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MyTableModel
/*    */   extends AbstractTableModel
/*    */ {
/* 13 */   private String[] columnNames = { "Reference File", 
/* 14 */       "Start", 
/* 15 */       "End", 
/* 16 */       "Duplication File", 
/* 17 */       "Start", 
/* 18 */       "End", 
/* 19 */       "Copied length", 
/* 20 */       "Length in file", 
/* 21 */       "Type", 
/* 22 */       "Signature" };
/*    */   
/*    */   private Object[][] data;
/*    */   
/* 26 */   public MyTableModel() { this.data = new Object[0][0]; }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public MyTableModel(Object[][] initData) { this.data = initData; }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public int getColumnCount() { return this.columnNames.length; }
/*    */ 
/*    */ 
/*    */   
/* 38 */   public int getRowCount() { return this.data.length; }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public String getColumnName(int col) { return this.columnNames[col]; }
/*    */ 
/*    */ 
/*    */   
/* 46 */   public Object getValueAt(int row, int col) { return this.data[row][col]; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public Class getColumnClass(int c) { return getValueAt(0, c).getClass(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setValueAt(Object value, int row, int col) {
/* 64 */     this.data[row][col] = value;
/* 65 */     fireTableCellUpdated(row, col);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\gui\MyTableModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */