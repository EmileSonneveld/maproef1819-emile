/*    */ package classes.lrg.jMondrian.layouts;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import lrg.jMondrian.figures.Node;
/*    */ import lrg.jMondrian.layouts.Checker;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class null
/*    */   extends Object
/*    */   implements Comparator<Node>
/*    */ {
/*    */   public int compare(Node a, Node b) {
/* 51 */     Checker.access$0(Checker.this).setReceiver(a.getEntity());
/* 52 */     double x = Checker.access$0(Checker.this).execute();
/* 53 */     Checker.access$0(Checker.this).setReceiver(b.getEntity());
/* 54 */     double y = Checker.access$0(Checker.this).execute();
/* 55 */     return (int)x - (int)y;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\layouts\Checker$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */