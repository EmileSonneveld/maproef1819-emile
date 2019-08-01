/*    */ package lrg.memoria.core;
/*    */ 
/*    */ public class Union
/*    */   extends DataAbstraction
/*    */ {
/*  6 */   public Union(String name) { super(name); }
/*    */ 
/*    */ 
/*    */   
/* 10 */   public void accept(ModelVisitor mv) { mv.visitUnion(this); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public boolean isUnion() { return true; }
/*    */ 
/*    */ 
/*    */   
/* 18 */   public String toString() { return "Union: \n \tname: " + getName(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Union.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */