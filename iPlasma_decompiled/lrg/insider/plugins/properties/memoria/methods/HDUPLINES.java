/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.dude.duplication.Duplication;
/*    */ import lrg.dude.duplication.DuplicationList;
/*    */ import lrg.insider.plugins.groups.memoria.MethodsWithHierarchyDuplication;
/*    */ 
/*    */ 
/*    */ public class HDUPLINES
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public HDUPLINES() { super("HDUPLINES", "Total Lines of Duplication with other classes in hierarchy", "method", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aMethod) {
/* 21 */     double result = 0.0D;
/* 22 */     ResultEntity aResult = aMethod.getProperty("#DUPLICATION#");
/* 23 */     if (aResult == null) return new ResultEntity(result);
/*    */ 
/*    */     
/* 26 */     if (aResult.getValue() instanceof DuplicationList) {
/* 27 */       ArrayList duplicators = MethodsWithHierarchyDuplication.getMethodsWithHierarchyDuplication((DuplicationList)aResult.getValue());
/*    */       
/* 29 */       for (Iterator it = duplicators.iterator(); it.hasNext();) {
/* 30 */         result += ((Duplication)it.next()).copiedLength();
/*    */       }
/*    */     } 
/* 33 */     return new ResultEntity(result);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\HDUPLINES.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */