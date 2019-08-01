/*    */ package classes.lrg.insider.plugins.core.groups.memoria.uses;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.Call;
/*    */ import lrg.memoria.core.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OperationCalls
/*    */   extends GroupBuilder
/*    */ {
/* 18 */   public OperationCalls() { super("operations called", "", new String[] { "global function", "method" }); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 22 */     ArrayList resultList = new ArrayList();
/*    */     
/* 24 */     if (!(anEntity instanceof Function)) {
/* 25 */       return resultList;
/*    */     }
/* 27 */     Function aMethod = (Function)anEntity;
/* 28 */     if (aMethod.getBody() == null) return resultList;
/*    */     
/* 30 */     if (aMethod.getBody().getCallList() == null) return resultList;
/*    */     
/* 32 */     Iterator it = aMethod.getBody().getCallList().iterator();
/* 33 */     while (it.hasNext()) {
/* 34 */       Call theCall = (Call)it.next();
/* 35 */       resultList.add(theCall.getFunction());
/*    */     } 
/* 37 */     return resultList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memori\\uses\OperationCalls.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */