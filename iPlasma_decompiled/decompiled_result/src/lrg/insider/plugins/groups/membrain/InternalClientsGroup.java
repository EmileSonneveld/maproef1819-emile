/*    */ package classes.lrg.insider.plugins.groups.membrain;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ 
/*    */ public class InternalClientsGroup
/*    */   extends GroupBuilder
/*    */ {
/* 14 */   public InternalClientsGroup() { super("internal clients group", "", "method"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface entity) {
/*    */     try {
/* 20 */       Method ent = (Method)entity;
/* 21 */       if (ent.isPrivate() || ent.isConstructor() || ent.isStatic()) {
/* 22 */         return new ArrayList();
/*    */       }
/* 24 */       GroupEntity allResolutionMethods = ent.getGroup("methods overriden").union(ent.getGroup("overridden by")).union(ent).distinct();
/* 25 */       GroupEntity allClients = allResolutionMethods.getGroup("operations calling me virtually").distinct();
/* 26 */       GroupEntity allHierarchyMethods = entity.belongsTo("class").getGroup("all ancestors").union(entity.belongsTo("class").getGroup("all descendants")).union(entity.belongsTo("class")).getGroup("method group").distinct();
/*    */       
/* 28 */       ArrayList result = new ArrayList();
/* 29 */       Iterator it = allClients.iterator();
/* 30 */       while (it.hasNext()) {
/* 31 */         Method aClient = (Method)it.next();
/* 32 */         if (allHierarchyMethods.isInGroup(aClient) && !result.contains(aClient)) result.add(aClient); 
/*    */       } 
/* 34 */       return result;
/* 35 */     } catch (Exception e) {
/* 36 */       return new ArrayList();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\InternalClientsGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */