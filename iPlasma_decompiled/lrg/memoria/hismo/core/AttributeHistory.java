/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ public class AttributeHistory
/*    */   extends VariableHistory
/*    */ {
/*    */   private ClassHistory classHistory;
/*    */   
/*  8 */   public AttributeHistory(ClassHistory ch) { this.classHistory = ch; }
/*    */ 
/*    */ 
/*    */   
/* 12 */   public AttributeHistory(VersionsList versions) { super(versions); }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public ClassHistory getClassHistory() { return this.classHistory; }
/*    */   
/*    */   protected void initializeInnerHistories() {}
/*    */   
/*    */   protected void updateInnerHistories(AbstractVersion version) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\AttributeHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */