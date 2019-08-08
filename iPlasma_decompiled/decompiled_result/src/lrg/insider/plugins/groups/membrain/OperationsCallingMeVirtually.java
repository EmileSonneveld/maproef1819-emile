/*    */ package classes.lrg.insider.plugins.groups.membrain;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.common.metamodel.MetaModel;
/*    */ import lrg.membrain.representation.instructionSet.Abstractions.MCall;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ 
/*    */ public class OperationsCallingMeVirtually
/*    */   extends GroupBuilder
/*    */ {
/* 16 */   public OperationsCallingMeVirtually() { super("operations calling me virtually", "", "method"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface entity) {
/* 21 */     Method aMethod = (Method)entity;
/* 22 */     GroupEntity allClients = aMethod.getGroup("operations calling me").distinct();
/* 23 */     ArrayList result = new ArrayList();
/* 24 */     Iterator it = allClients.iterator();
/* 25 */     while (it.hasNext()) {
/* 26 */       Object o = it.next();
/* 27 */       if (!(o instanceof Method))
/* 28 */         continue;  Method tested = (Method)o;
/* 29 */       GroupEntity vCalls = tested.getGroup("call group").applyFilter("Dynamic Dispached Calls");
/* 30 */       if (vCalls.size() != 0) {
/* 31 */         Iterator anIt = vCalls.iterator();
/* 32 */         while (anIt.hasNext()) {
/* 33 */           AbstractEntityInterface tmp = MetaModel.instance().findEntityByAddress(((MCall)anIt.next()).produceCalledAddress());
/* 34 */           if (tmp != null && aMethod == tmp) {
/* 35 */             result.add(tested);
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/* 41 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\OperationsCallingMeVirtually.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */