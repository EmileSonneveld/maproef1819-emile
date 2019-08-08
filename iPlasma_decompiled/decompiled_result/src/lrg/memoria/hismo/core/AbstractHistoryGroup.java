/*     */ package lrg.memoria.hismo.core;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import lrg.memoria.hismo.utils.AbstractSelectionCondition;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractHistoryGroup
/*     */ {
/*  15 */   protected Hashtable<String, AbstractHistory> histories = new Hashtable();
/*     */ 
/*     */ 
/*     */   
/*  19 */   public void put(AbstractHistory history) { this.histories.put(history.getFullName(), history); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAll(AbstractHistoryGroup aHistoryGroup) {
/*  24 */     Collection histories = aHistoryGroup.histories.values();
/*  25 */     Collection currentHistories = this.histories.values();
/*  26 */     for (Iterator it = histories.iterator(); it.hasNext(); ) {
/*  27 */       AbstractHistory aHistory = (AbstractHistory)it.next();
/*  28 */       if (!currentHistories.contains(aHistory))
/*  29 */         this.histories.put(aHistory.getFullName(), aHistory); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public AbstractHistoryGroup createNewHistoryGroupByAdding(AbstractHistoryGroup aHistoryGroup) {
/*  34 */     AbstractHistoryGroup newGroup = aHistoryGroup.createHistoryGroup();
/*  35 */     newGroup.addAll(this);
/*  36 */     newGroup.addAll(aHistoryGroup);
/*  37 */     return newGroup;
/*     */   }
/*     */ 
/*     */   
/*  41 */   public int size() { return this.histories.size(); }
/*     */ 
/*     */ 
/*     */   
/*  45 */   public Collection<AbstractHistory> getHistoriesCollection() { return this.histories.values(); }
/*     */ 
/*     */ 
/*     */   
/*  49 */   public ArrayList<AbstractHistory> getHistoriesArrayList() { return new ArrayList(this.histories.values()); }
/*     */ 
/*     */   
/*     */   public boolean equals(AbstractHistoryGroup aHistoryGroup) {
/*  53 */     if (this.histories.values().containsAll(aHistoryGroup.histories.values()) && 
/*  54 */       aHistoryGroup.histories.values().containsAll(this.histories.values())) return true; 
/*     */     return false;
/*     */   }
/*     */   
/*  58 */   public boolean includes(AbstractHistoryGroup aHistoryGroup) { return this.histories.values().containsAll(aHistoryGroup.histories.values()); }
/*     */ 
/*     */ 
/*     */   
/*  62 */   public AbstractHistory get(String fullName) { return (AbstractHistory)this.histories.get(fullName); }
/*     */ 
/*     */   
/*     */   public VersionsList getAllVersions() {
/*  66 */     VersionsList allVersions = new VersionsList();
/*  67 */     VersionsList currentVersions = new VersionsList();
/*     */     
/*  69 */     for (Iterator it = this.histories.values().iterator(); it.hasNext(); ) {
/*  70 */       AbstractHistory currentHistory = (AbstractHistory)it.next();
/*  71 */       currentVersions = currentHistory.getVersions();
/*  72 */       allVersions.addAll(currentVersions);
/*     */     } 
/*  74 */     return allVersions;
/*     */   }
/*     */   
/*     */   public AbstractHistoryGroup selectGroupMatchingCondition(AbstractSelectionCondition cond) {
/*  78 */     AbstractHistoryGroup currentGroup = createHistoryGroup();
/*     */     
/*  80 */     for (Iterator it = this.histories.values().iterator(); it.hasNext(); ) {
/*  81 */       AbstractHistory currentHistory = (AbstractHistory)it.next();
/*  82 */       if (cond.isSatisfiedBy(currentHistory))
/*  83 */         currentGroup.put(currentHistory); 
/*     */     } 
/*  85 */     return currentGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractHistory selectFirstHistoryMatchingCondition(AbstractSelectionCondition cond) {
/*  90 */     for (Iterator it = this.histories.values().iterator(); it.hasNext(); ) {
/*  91 */       AbstractHistory currentHistory = (AbstractHistory)it.next();
/*  92 */       if (cond.isSatisfiedBy(currentHistory))
/*  93 */         return currentHistory; 
/*     */     } 
/*  95 */     return null;
/*     */   }
/*     */   
/*     */   public AbstractHistoryGroup selectHistoriesBornInVersion(String name) {
/*     */     class BornInVersionSelectionCondition
/*     */       implements AbstractSelectionCondition {
/*     */       String version;
/*     */       
/* 103 */       public BornInVersionSelectionCondition(String versionName) { this.version = versionName; }
/*     */ 
/*     */       
/*     */       public boolean isSatisfiedBy(AbstractHistory history) {
/* 107 */         AbstractVersion firstVersion = history.getFirstVersion();
/* 108 */         if (firstVersion != null && firstVersion.versionName().equals(this.version))
/* 109 */           return true; 
/* 110 */         return false;
/*     */       }
/*     */     };
/* 113 */     return selectGroupMatchingCondition(new BornInVersionSelectionCondition(name));
/*     */   }
/*     */   
/*     */   public AbstractHistoryGroup selectHistoriesBornAfterVersion(String name) {
/*     */     class BornAfterVersionSelectionCondition
/*     */       implements AbstractSelectionCondition {
/*     */       String version;
/*     */       
/* 121 */       public BornAfterVersionSelectionCondition(String versionName) { this.version = versionName; }
/*     */ 
/*     */       
/*     */       public boolean isSatisfiedBy(AbstractHistory history) {
/* 125 */         AbstractVersion firstVersion = history.getFirstVersion();
/* 126 */         if (firstVersion != null && firstVersion.versionName().compareTo(this.version) >= 0)
/* 127 */           return true; 
/* 128 */         return false;
/*     */       }
/*     */     };
/* 131 */     return selectGroupMatchingCondition(new BornAfterVersionSelectionCondition(name));
/*     */   }
/*     */   
/*     */   public AbstractHistoryGroup selectHistoriesBornBeforeVersion(String name) {
/*     */     class BornBeforeVersionSelectionCondition
/*     */       implements AbstractSelectionCondition {
/*     */       String version;
/*     */       
/* 139 */       public BornBeforeVersionSelectionCondition(String versionName) { this.version = versionName; }
/*     */ 
/*     */       
/*     */       public boolean isSatisfiedBy(AbstractHistory history) {
/* 143 */         AbstractVersion firstVersion = history.getFirstVersion();
/* 144 */         if (firstVersion != null && firstVersion.versionName().compareTo(this.version) < 0)
/* 145 */           return true; 
/* 146 */         return false;
/*     */       }
/*     */     };
/* 149 */     return selectGroupMatchingCondition(new BornBeforeVersionSelectionCondition(name));
/*     */   }
/*     */   
/*     */   public AbstractHistoryGroup selectSubHistoryForVersions(ArrayList versionNames) {
/* 153 */     AbstractHistoryGroup currentGroup = createHistoryGroup();
/*     */ 
/*     */ 
/*     */     
/* 157 */     for (Iterator it = this.histories.values().iterator(); it.hasNext(); ) {
/* 158 */       AbstractHistory currentHistory = (AbstractHistory)it.next();
/* 159 */       VersionsList newVersionsList = new VersionsList();
/* 160 */       for (Iterator vit = currentHistory.getVersionIterator(); vit.hasNext(); ) {
/* 161 */         AbstractVersion currentVersion = (AbstractVersion)vit.next();
/* 162 */         if (versionNames.contains(currentVersion.versionName())) {
/* 163 */           newVersionsList.add(currentVersion);
/*     */         }
/*     */       } 
/* 166 */       if (newVersionsList.size() > 0) {
/* 167 */         AbstractHistory subHistory = createHistory(newVersionsList);
/* 168 */         currentGroup.put(subHistory);
/*     */       } 
/*     */     } 
/* 171 */     return currentGroup;
/*     */   }
/*     */   
/*     */   public abstract AbstractHistory createHistory(VersionsList paramVersionsList);
/*     */   
/*     */   public abstract AbstractHistoryGroup createHistoryGroup();
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\AbstractHistoryGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */