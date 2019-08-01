/*     */ package classes.lrg.insider.plugins.tools;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.insider.plugins.tools.ClusteringAlgorithm;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClusteringAlgorithm
/*     */ {
/*     */   private HashMap<AbstractEntity, GroupEntity> entityToPartnersMapper;
/*     */   private HashMap<GroupEntity, GroupEntity> resultClustersMap;
/*     */   
/*     */   public ClusteringAlgorithm(HashMap<AbstractEntity, GroupEntity> e2pm) {
/*  19 */     this.entityToPartnersMapper = new HashMap(e2pm);
/*  20 */     this.resultClustersMap = new HashMap();
/*     */ 
/*     */ 
/*     */     
/*  24 */     for (Iterator<AbstractEntity> iterator = this.entityToPartnersMapper.keySet().iterator(); iterator.hasNext(); ) {
/*  25 */       AbstractEntity crtEntity = (AbstractEntity)iterator.next();
/*  26 */       GroupEntity crtCluster = new GroupEntity("cluster", new ArrayList());
/*  27 */       crtCluster.add(crtEntity);
/*  28 */       this.resultClustersMap.put(crtCluster, (GroupEntity)this.entityToPartnersMapper.get(crtEntity));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HashMap<GroupEntity, GroupEntity> cluster() {
/*  36 */     int found = 0;
/*     */     
/*     */     do {
/*  39 */       found = 0;
/*  40 */       HashMap<GroupEntity, GroupEntity> newClusterMap = new HashMap<GroupEntity, GroupEntity>(this.resultClustersMap);
/*     */       
/*  42 */       Iterator<AbstractEntity> methodsIt = this.entityToPartnersMapper.keySet().iterator();
/*  43 */       while (methodsIt.hasNext()) {
/*  44 */         int newfound = 0;
/*  45 */         AbstractEntity crtMethod = (AbstractEntity)methodsIt.next();
/*  46 */         newClusterMap = new HashMap<GroupEntity, GroupEntity>(this.resultClustersMap);
/*  47 */         for (Iterator<GroupEntity> clusterIt = this.resultClustersMap.keySet().iterator(); clusterIt.hasNext(); ) {
/*  48 */           GroupEntity crtCluster = (GroupEntity)clusterIt.next();
/*  49 */           GroupEntity crtInvokerClasses = (GroupEntity)this.resultClustersMap.get(crtCluster);
/*  50 */           if (!crtCluster.isInGroup(crtMethod)) newfound += findAndUpdateClusters(newClusterMap, crtCluster, crtInvokerClasses, crtMethod, (GroupEntity)this.entityToPartnersMapper.get(crtMethod)); 
/*     */         } 
/*  52 */         found += newfound;
/*  53 */         this.resultClustersMap = newClusterMap;
/*     */       } 
/*     */       
/*  56 */       this.resultClustersMap = newClusterMap;
/*  57 */     } while (found > 0);
/*  58 */     System.out.println(String.valueOf(this.resultClustersMap.size()) + " >> " + this.resultClustersMap);
/*  59 */     return this.resultClustersMap;
/*     */   }
/*     */   
/*     */   private int findAndUpdateClusters(HashMap<GroupEntity, GroupEntity> map, GroupEntity firstCluster, GroupEntity firstInvokerClasses, AbstractEntity crtMethod, GroupEntity secondInvokerClasses) {
/*  63 */     GroupEntity commonInvokerClasses = firstInvokerClasses.intersect(secondInvokerClasses);
/*  64 */     ArrayList<AbstractEntity> firstClusterElements = firstCluster.getElements();
/*  65 */     if (commonInvokerClasses.size() > 0) {
/*  66 */       GroupEntity newCluster = new GroupEntity("cluster", new ArrayList(firstClusterElements));
/*  67 */       newCluster.add(crtMethod);
/*  68 */       if (shouldRemove(map, firstCluster, firstInvokerClasses, commonInvokerClasses)) {
/*  69 */         map.remove(firstCluster);
/*     */       }
/*  71 */       if (!isPresent(newCluster, map)) {
/*  72 */         map.put(newCluster, commonInvokerClasses);
/*  73 */         return 1;
/*     */       } 
/*  75 */       return 0;
/*     */     } 
/*  77 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isPresent(GroupEntity anEntity, HashMap<GroupEntity, GroupEntity> aMap) {
/*  82 */     Iterator<GroupEntity> it = aMap.keySet().iterator();
/*     */     
/*  84 */     while (it.hasNext()) {
/*  85 */       GroupEntity crtKey = (GroupEntity)it.next();
/*  86 */       if (areEqual(crtKey, anEntity) || isContained(anEntity, crtKey)) return true; 
/*     */     } 
/*  88 */     return false;
/*     */   }
/*     */   
/*     */   private boolean shouldRemove(HashMap<GroupEntity, GroupEntity> map, GroupEntity toRemove, GroupEntity toRemoveInvokers, GroupEntity commonInvokers) {
/*  92 */     if (toRemoveInvokers.size() == commonInvokers.size()) return true; 
/*  93 */     GroupEntity containedInvokers = new GroupEntity("clustere", new ArrayList());
/*  94 */     for (Iterator<GroupEntity> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
/*  95 */       GroupEntity crtKey = (GroupEntity)iterator.next();
/*  96 */       if (isContained(toRemove, crtKey)) containedInvokers.addAll((GroupEntity)map.get(crtKey)); 
/*     */     } 
/*  98 */     if (toRemoveInvokers.size() == containedInvokers.distinct().size()) {
/*  99 */       return true;
/*     */     }
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 105 */   private boolean areEqual(GroupEntity first, GroupEntity second) { return (first.exclude(second).size() == 0 && second.exclude(first).size() == 0); }
/*     */ 
/*     */   
/*     */   private boolean isContained(GroupEntity small, GroupEntity large) {
/* 109 */     if (small.size() >= large.size()) return false;
/*     */     
/* 111 */     if (small.intersect(large).size() == small.size()) return true; 
/* 112 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\ClusteringAlgorithm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */