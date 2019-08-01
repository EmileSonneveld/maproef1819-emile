/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.dude.duplication.Duplication;
/*    */ import lrg.dude.duplication.DuplicationList;
/*    */ import lrg.insider.plugins.groups.memoria.MethodsWithExternalDuplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EDUPLINES
/*    */   extends PropertyComputer
/*    */ {
/* 23 */   public EDUPLINES() { super("EDUPLINES", "Duplication level with other classes", "method", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aMethod) {
/* 27 */     double result = 0.0D;
/* 28 */     ResultEntity aResult = aMethod.getProperty("#DUPLICATION#");
/* 29 */     if (aResult == null) return new ResultEntity(result);
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
/* 45 */     if (aResult.getValue() instanceof DuplicationList) {
/* 46 */       ArrayList duplicators = MethodsWithExternalDuplication.getUnrelatedMethodsWithDuplication((DuplicationList)aResult.getValue());
/*    */       
/* 48 */       for (Iterator it = duplicators.iterator(); it.hasNext();) {
/* 49 */         result += ((Duplication)it.next()).copiedLength();
/*    */       }
/*    */     } 
/* 52 */     return new ResultEntity(result);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\EDUPLINES.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */