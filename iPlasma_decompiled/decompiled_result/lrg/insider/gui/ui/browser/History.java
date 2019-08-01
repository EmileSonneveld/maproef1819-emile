/*    */ package classes.lrg.insider.gui.ui.browser;
/*    */ import lrg.insider.gui.ui.browser.History;
/*    */ import lrg.insider.gui.ui.browser.HistoryDetail;
/*    */ 
/*    */ public class History {
/*    */   private HistoryDetail[] historyDetails;
/*    */   
/*    */   public History(int maxAddressesToRemember) {
/*  9 */     this.historyDetails = new HistoryDetail[maxAddressesToRemember];
/* 10 */     this.currentHistoryIndex = -1;
/* 11 */     this.lastHistoryIndex = -1;
/*    */   }
/*    */   private int currentHistoryIndex; private int lastHistoryIndex;
/*    */   
/* 15 */   public int numberOfBackLocations() { return this.currentHistoryIndex; }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public int numberOfForwardLocations() { return this.lastHistoryIndex - this.currentHistoryIndex; }
/*    */ 
/*    */   
/*    */   public HistoryDetail getBackDetail() {
/* 23 */     if (numberOfBackLocations() > 0) {
/* 24 */       this.currentHistoryIndex--;
/* 25 */       return this.historyDetails[this.currentHistoryIndex];
/*    */     } 
/*    */     
/* 28 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public HistoryDetail getForwardDetail() {
/* 34 */     if (numberOfForwardLocations() > 0) {
/* 35 */       this.currentHistoryIndex++;
/* 36 */       return this.historyDetails[this.currentHistoryIndex];
/*    */     } 
/*    */     
/* 39 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addNewDetail(String address, String detailName) {
/* 45 */     if (this.currentHistoryIndex == this.historyDetails.length - 1) {
/* 46 */       for (int i = 1; i < this.historyDetails.length; i++) {
/* 47 */         this.historyDetails[i - 1] = this.historyDetails[i];
/*    */       }
/*    */     } else {
/* 50 */       this.currentHistoryIndex++;
/*    */     } 
/*    */     
/* 53 */     this.historyDetails[this.currentHistoryIndex] = new HistoryDetail(address, detailName);
/* 54 */     this.lastHistoryIndex = this.currentHistoryIndex;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\browser\History.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */