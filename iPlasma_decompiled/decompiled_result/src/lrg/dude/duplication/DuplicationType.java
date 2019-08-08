/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DuplicationType
/*    */ {
/* 11 */   public static final DuplicationType EXACT = new DuplicationType("EXACT");
/* 12 */   public static final DuplicationType INSERT = new DuplicationType("INSERT");
/* 13 */   public static final DuplicationType DELETE = new DuplicationType("DELETE");
/* 14 */   public static final DuplicationType MODIFIED = new DuplicationType("MODIFIED");
/* 15 */   public static final DuplicationType COMPOSED = new DuplicationType("COMPOSED");
/*    */   
/*    */   private final String myName;
/*    */ 
/*    */   
/* 20 */   private DuplicationType(String name) { this.myName = name; }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public String toString() { return this.myName; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\DuplicationType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */