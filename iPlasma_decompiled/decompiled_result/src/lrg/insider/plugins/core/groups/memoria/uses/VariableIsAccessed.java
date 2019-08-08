/*    */ package classes.lrg.insider.plugins.core.groups.memoria.uses;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.Access;
/*    */ import lrg.memoria.core.Variable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VariableIsAccessed
/*    */   extends GroupBuilder
/*    */ {
/* 18 */   public VariableIsAccessed() { super("methods accessing variable", "", new String[] { "global variable", "attribute", "local variable", "parameter" }); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 22 */     ArrayList resultList = new ArrayList();
/*    */     
/* 24 */     if (!(anEntity instanceof Variable)) {
/* 25 */       return resultList;
/*    */     }
/* 27 */     Variable aVariable = (Variable)anEntity;
/* 28 */     if (aVariable.getAccessList() == null) return resultList;
/*    */     
/* 30 */     Iterator it = aVariable.getAccessList().iterator();
/* 31 */     while (it.hasNext()) {
/* 32 */       Access theAccess = (Access)it.next();
/* 33 */       resultList.add(theAccess.getScope().getScope());
/*    */     } 
/* 35 */     return resultList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memori\\uses\VariableIsAccessed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */