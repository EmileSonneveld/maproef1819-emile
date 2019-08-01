/*    */ package classes.lrg.insider.plugins.tools;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.plugins.tools.PRStructure;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PRStructure
/*    */ {
/*    */   AbstractEntityInterface theClass;
/*    */   double PR;
/*    */   double CA;
/*    */   ArrayList<AbstractEntityInterface> CAList;
/*    */   ArrayList<PRStructure> TList;
/*    */   
/*    */   PRStructure(AbstractEntityInterface aClass, ArrayList<AbstractEntityInterface> aCAList, double pr) {
/* 24 */     this.theClass = aClass;
/* 25 */     this.CAList = aCAList;
/* 26 */     this.CA = this.CAList.size();
/* 27 */     this.TList = new ArrayList();
/* 28 */     this.PR = pr;
/*    */   }
/*    */ 
/*    */   
/*    */   PRStructure(PRStructure anotherStructure, double newPR) {
/* 33 */     this.theClass = anotherStructure.theClass;
/* 34 */     this.CA = anotherStructure.CA;
/* 35 */     this.CAList = anotherStructure.CAList;
/* 36 */     this.TList = anotherStructure.TList;
/*    */     
/* 38 */     this.PR = newPR;
/*    */   }
/*    */ 
/*    */   
/* 42 */   public double getPR() { return this.PR; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\PRStructure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */