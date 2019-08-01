/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringList
/*    */ {
/*    */   private ArrayList list;
/*    */   
/*    */   public StringList(String[] tab) {
/* 16 */     this();
/* 17 */     for (int i = 0; i < tab.length; i++)
/* 18 */       this.list.add(tab[i]); 
/*    */   }
/*    */   public StringList() {
/*    */     this.list = null;
/* 22 */     this.list = new ArrayList();
/*    */   }
/*    */ 
/*    */   
/* 26 */   public void add(String s) { this.list.add(s); }
/*    */ 
/*    */   
/*    */   public void addAll(StringList aList) {
/* 30 */     int size = aList.size();
/* 31 */     for (int i = 0; i < size; i++) {
/* 32 */       this.list.add(aList.get(i));
/*    */     }
/*    */   }
/*    */   
/* 36 */   public String get(int index) { return (String)this.list.get(index); }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public void set(int i, String value) { this.list.set(i, value); }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public int size() { return this.list.size(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\StringList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */