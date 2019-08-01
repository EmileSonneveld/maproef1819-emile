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
/*    */ public class ExternalClientsGroup
/*    */   extends GroupBuilder
/*    */ {
/* 14 */   public ExternalClientsGroup() { super("external clients group", "", "method"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface entity) {
/*    */     try {
/* 20 */       Method ent = (Method)entity;
/* 21 */       if (ent.isPrivate() || ent.isConstructor() || ent.isStatic()) {
/* 22 */         return new ArrayList();
/*    */       }
/*    */       
/* 25 */       GroupEntity allResolutionMethods = ent.getGroup("methods overriden").union(ent.getGroup("overridden by")).union(ent).distinct();
/* 26 */       GroupEntity allClients = allResolutionMethods.getGroup("operations calling me virtually").distinct();
/* 27 */       GroupEntity allHierarchy = entity.belongsTo("class").getGroup("all ancestors").union(
/* 28 */           entity.belongsTo("class").getGroup("all descendants")).union(entity.belongsTo("class")).distinct();
/*    */       
/* 30 */       ArrayList result = new ArrayList();
/* 31 */       Iterator it = allClients.iterator();
/* 32 */       while (it.hasNext()) {
/* 33 */         Method aClient = (Method)it.next();
/* 34 */         if (!allHierarchy.isInGroup(aClient.belongsTo("class")) && !result.contains(aClient)) result.add(aClient); 
/*    */       } 
/* 36 */       return result;
/* 37 */     } catch (Exception e) {
/* 38 */       return new ArrayList();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\ExternalClientsGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */