/*    */ package classes.lrg.insider.plugins.core.groups.memoria.uses;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
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
/*    */ 
/*    */ public class OperationIsCalled
/*    */   extends GroupBuilder
/*    */ {
/* 20 */   public OperationIsCalled() { super("operations calling me", "", new String[] { "global function", "method" }); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 24 */     HashSet resultList = new HashSet();
/* 25 */     if (!(anEntity instanceof Function)) return new ArrayList(resultList);
/*    */     
/* 27 */     Function aFunction = (Function)anEntity;
/*    */     
/* 29 */     Iterator it = aFunction.getCallList().iterator();
/* 30 */     while (it.hasNext()) {
/* 31 */       resultList.add(((Call)it.next()).getScope().getScope());
/*    */     }
/* 33 */     return new ArrayList(resultList);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memori\\uses\OperationIsCalled.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */