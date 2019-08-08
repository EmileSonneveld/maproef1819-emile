/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.properties.memoria.classes.UsageClusters;
/*    */ import lrg.insider.plugins.tools.ClusteringAlgorithm;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UsageClusters
/*    */   extends PropertyComputer
/*    */ {
/* 18 */   public UsageClusters() { super("Usage Clusters", "Usage Clusters", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   private HashMap<AbstractEntity, GroupEntity> createInitialMap(AbstractEntityInterface measuredClass) {
/* 22 */     HashMap<AbstractEntity, GroupEntity> entity2Partners = new HashMap<AbstractEntity, GroupEntity>();
/*    */     
/* 24 */     ArrayList<AbstractEntity> methods = measuredClass.contains("method group").applyFilter("is public").getElements();
/* 25 */     for (AbstractEntity crtMethod : methods) {
/* 26 */       GroupEntity externalInvokerClasses = ((GroupEntity)crtMethod.getGroup("operations calling me").belongsTo("class")).distinct().exclude((AbstractEntity)measuredClass);
/* 27 */       if (externalInvokerClasses.size() > 0) {
/* 28 */         entity2Partners.put(crtMethod, externalInvokerClasses);
/*    */       }
/*    */     } 
/* 31 */     return entity2Partners;
/*    */   }
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface measuredClass) {
/* 35 */     HashMap<AbstractEntity, GroupEntity> initialMapping = createInitialMap(measuredClass);
/* 36 */     return new ResultEntity((new ClusteringAlgorithm(initialMapping)).cluster());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\UsageClusters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */