/*    */ package classes.lrg.insider.plugins.groups.membrain;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.common.metamodel.MetaModel;
/*    */ import lrg.membrain.representation.instructionSet.Abstractions.MCall;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ 
/*    */ public class PotentialClients
/*    */   extends GroupBuilder
/*    */ {
/* 17 */   public PotentialClients() { super("all potential clients", "Clients that contain static calls that may hit the type", "class"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 21 */     DataAbstraction aClass = (DataAbstraction)anEntity;
/* 22 */     GroupEntity allMethods = aClass.getGroup("virtual methods of object");
/* 23 */     GroupEntity allClients = allMethods.getGroup("operations calling me").distinct();
/* 24 */     ArrayList result = new ArrayList();
/* 25 */     Iterator it = allClients.iterator();
/* 26 */     while (it.hasNext()) {
/* 27 */       Method tested = (Method)it.next();
/* 28 */       GroupEntity vCalls = tested.getGroup("call group").applyFilter("Dynamic Dispached Calls");
/* 29 */       if (vCalls.size() != 0) {
/* 30 */         Iterator anIt = vCalls.iterator();
/* 31 */         while (anIt.hasNext()) {
/* 32 */           AbstractEntityInterface tmp = MetaModel.instance().findEntityByAddress(((MCall)anIt.next()).produceCalledAddress());
/* 33 */           if (tmp != null && allMethods.isInGroup(tmp) && !result.contains(tested)) {
/* 34 */             result.add(tested);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/* 39 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\PotentialClients.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */