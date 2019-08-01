/*     */ package lrg.memoria.hismo.utils;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import lrg.memoria.hismo.core.AbstractHistory;
/*     */ import lrg.memoria.hismo.core.AbstractHistoryGroup;
/*     */ import lrg.memoria.hismo.core.AbstractVersion;
/*     */ import lrg.memoria.hismo.core.VersionsList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntitiesThatChangeTogether
/*     */ {
/*     */   private int minimumNumberOfVersions;
/*     */   private int minimumNumberOfEntities;
/*     */   private AbstractChangeProperty changeProperty;
/*     */   
/*     */   public EntitiesThatChangeTogether(int minimumNumberOfVersions, int minimumNumberOfEntities, AbstractChangeProperty changeProperty) {
/*  24 */     this.minimumNumberOfVersions = minimumNumberOfVersions;
/*  25 */     this.minimumNumberOfEntities = minimumNumberOfEntities;
/*  26 */     this.changeProperty = changeProperty;
/*     */   }
/*     */   
/*     */   public Set compute(AbstractHistoryGroup historyGroup) {
/*  30 */     Hashtable matrix = computeInitialMatrix(historyGroup);
/*  31 */     Hashtable newMatrix = new Hashtable();
/*  32 */     for (int i = 1; i < this.minimumNumberOfEntities; i++) {
/*  33 */       newMatrix = computeNextMatrix(matrix, i);
/*  34 */       matrix = newMatrix;
/*     */     } 
/*  36 */     return matrix.keySet();
/*     */   }
/*     */   
/*     */   private Hashtable computeInitialMatrix(AbstractHistoryGroup historyGroup) {
/*  40 */     Hashtable initialMatrix = new Hashtable();
/*  41 */     Collection histories = historyGroup.getHistoriesCollection();
/*  42 */     VersionsList versions = historyGroup.getAllVersions();
/*  43 */     for (Iterator it = histories.iterator(); it.hasNext(); ) {
/*  44 */       AbstractHistory currentHistory = (AbstractHistory)it.next();
/*  45 */       Vector vectorOfChanges = new Vector();
/*  46 */       AbstractVersion previousVersion = null;
/*  47 */       int num = 0;
/*  48 */       for (Iterator vit = versions.iterator(); vit.hasNext(); ) {
/*  49 */         AbstractVersion currentVersion = (AbstractVersion)vit.next();
/*  50 */         if (previousVersion != null) {
/*  51 */           if (currentHistory.getVersions().contains(previousVersion) && 
/*  52 */             currentHistory.getVersions().contains(currentVersion)) {
/*  53 */             int val = this.changeProperty.getChangeValue(currentHistory.getVersionForName(previousVersion.versionName()), 
/*  54 */                 currentHistory.getVersionForName(currentVersion.versionName()));
/*  55 */             if (val != 0)
/*  56 */               num++; 
/*  57 */             vectorOfChanges.add(new Integer(val));
/*     */           } else {
/*  59 */             vectorOfChanges.add(new Integer(0));
/*     */           } 
/*     */         }
/*  62 */         previousVersion = currentVersion;
/*     */       } 
/*  64 */       if (num > 0) {
/*  65 */         AbstractHistoryGroup ahg = historyGroup.createHistoryGroup();
/*  66 */         ahg.put(currentHistory);
/*  67 */         initialMatrix.put(ahg, vectorOfChanges);
/*     */       } 
/*     */     } 
/*  70 */     return initialMatrix;
/*     */   }
/*     */   
/*     */   private Hashtable computeNextMatrix(Hashtable currentMatrix, int iteration) {
/*  74 */     Hashtable newMatrix = new Hashtable();
/*  75 */     Set entrySet1 = currentMatrix.entrySet();
/*  76 */     for (Iterator it1 = entrySet1.iterator(); it1.hasNext(); ) {
/*  77 */       Map.Entry ent1 = (Map.Entry)it1.next();
/*  78 */       AbstractHistoryGroup ahg1 = (AbstractHistoryGroup)ent1.getKey();
/*  79 */       Vector vectorOfChanges1 = (Vector)ent1.getValue();
/*  80 */       Set entrySet2 = currentMatrix.entrySet();
/*  81 */       for (Iterator it2 = entrySet2.iterator(); it2.hasNext(); ) {
/*  82 */         Map.Entry ent2 = (Map.Entry)it2.next();
/*  83 */         AbstractHistoryGroup ahg2 = (AbstractHistoryGroup)ent2.getKey();
/*  84 */         Vector vectorOfChanges2 = (Vector)ent2.getValue();
/*  85 */         if (!ahg1.equals(ahg2)) {
/*  86 */           Vector vectorOfChangesResult = new Vector();
/*  87 */           int count = 0;
/*  88 */           for (int i = 0; i < vectorOfChanges1.size(); i++) {
/*  89 */             Integer el1 = (Integer)vectorOfChanges1.elementAt(i);
/*  90 */             Integer el2 = (Integer)vectorOfChanges2.elementAt(i);
/*  91 */             if (el1.intValue() == 1 && el2.intValue() == 1) {
/*  92 */               vectorOfChangesResult.add(new Integer(1));
/*  93 */               count++;
/*     */             } else {
/*  95 */               vectorOfChangesResult.add(new Integer(0));
/*     */             } 
/*  97 */           }  if (count >= this.minimumNumberOfVersions) {
/*  98 */             AbstractHistoryGroup ahgResult = ahg2.createNewHistoryGroupByAdding(ahg1);
/*  99 */             if (ahgResult.getHistoriesCollection().size() > iteration)
/* 100 */               newMatrix.put(ahgResult, vectorOfChangesResult); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 105 */     return optimize(newMatrix);
/*     */   }
/*     */   
/*     */   private Hashtable optimize(Hashtable matrix) {
/* 109 */     Hashtable optimizedMatrix = new Hashtable();
/* 110 */     Set entrySet1 = matrix.entrySet();
/* 111 */     Object[] array1 = entrySet1.toArray();
/* 112 */     for (int i = 0; i < entrySet1.size(); i++) {
/* 113 */       Map.Entry ent1 = (Map.Entry)array1[i];
/* 114 */       AbstractHistoryGroup ahg1 = (AbstractHistoryGroup)ent1.getKey();
/* 115 */       Vector vectorOfChanges1 = (Vector)ent1.getValue();
/* 116 */       boolean bool = false;
/* 117 */       for (int j = i + 1; j < entrySet1.size(); j++) {
/* 118 */         Map.Entry ent2 = (Map.Entry)array1[j];
/* 119 */         AbstractHistoryGroup ahg2 = (AbstractHistoryGroup)ent2.getKey();
/* 120 */         Vector vectorOfChanges2 = (Vector)ent2.getValue();
/* 121 */         if (ahg2.includes(ahg1)) {
/* 122 */           int sameChangesNum = 0;
/* 123 */           for (int k = 0; k < vectorOfChanges1.size(); k++) {
/* 124 */             if (!vectorOfChanges1.get(k).equals(vectorOfChanges2.get(k)))
/* 125 */               sameChangesNum++; 
/* 126 */           }  if (sameChangesNum == vectorOfChanges1.size())
/* 127 */             bool = true; 
/*     */         } 
/*     */       } 
/* 130 */       if (!bool)
/* 131 */         optimizedMatrix.put(ahg1, vectorOfChanges1); 
/*     */     } 
/* 133 */     return optimizedMatrix;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hism\\utils\EntitiesThatChangeTogether.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */