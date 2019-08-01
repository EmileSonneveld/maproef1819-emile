/*     */ package lrg.memoria.hismo.utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import lrg.memoria.hismo.core.ClassHistory;
/*     */ import lrg.memoria.hismo.core.HismoLoader;
/*     */ import lrg.memoria.hismo.core.NamespaceHistory;
/*     */ import lrg.memoria.hismo.core.PackageHistory;
/*     */ import lrg.memoria.hismo.core.SystemHistory;
/*     */ 
/*     */ public class HismoWrapper {
/*     */   public HismoWrapper(ArrayList historyNamesList, ArrayList pathList, ArrayList cacheList) {
/*  12 */     HismoLoader hismoBuilder = new HismoLoader(historyNamesList, pathList, cacheList, "testdata", null, HismoLoader.CPP_SOURCES);
/*  13 */     this.currentSystemHistory = hismoBuilder.getSystemHistory();
/*     */   }
/*     */   protected SystemHistory currentSystemHistory;
/*     */   
/*  17 */   public HismoWrapper(SystemHistory systemHistory) { this.currentSystemHistory = systemHistory; }
/*     */ 
/*     */ 
/*     */   
/*  21 */   public SystemHistory getSystemHistory() { return this.currentSystemHistory; }
/*     */ 
/*     */ 
/*     */   
/*  25 */   public PackageHistoryGroup getPackageHistories() { return this.currentSystemHistory.getPackageHistories(); }
/*     */ 
/*     */ 
/*     */   
/*  29 */   public NamespaceHistoryGroup getNamespaceHistories() { return this.currentSystemHistory.getNamespaceHistories(); }
/*     */ 
/*     */ 
/*     */   
/*  33 */   public ClassHistoryGroup getClassHistories() { return this.currentSystemHistory.getClassHistories(); }
/*     */ 
/*     */   
/*     */   public PackageHistory getPackageHistoryNamed(String name) {
/*  37 */     PackageHistoryGroup packageHistories = getPackageHistories();
/*  38 */     for (Iterator it = packageHistories.getHistoriesCollection().iterator(); it.hasNext(); ) {
/*  39 */       PackageHistory packHist = (PackageHistory)it.next();
/*  40 */       if (name.equals(packHist.getFullName()))
/*  41 */         return packHist; 
/*     */     } 
/*  43 */     return null;
/*     */   }
/*     */   
/*     */   public NamespaceHistory getNamespaceHistoryNamed(String name) {
/*  47 */     NamespaceHistoryGroup namespaceHistories = getNamespaceHistories();
/*  48 */     for (Iterator<AbstractHistory> it = namespaceHistories.getHistoriesCollection().iterator(); it.hasNext(); ) {
/*  49 */       NamespaceHistory namespaceHistory = (NamespaceHistory)it.next();
/*  50 */       if (name.equals(namespaceHistory.getFullName()))
/*  51 */         return namespaceHistory; 
/*     */     } 
/*  53 */     return null;
/*     */   }
/*     */   
/*     */   public GlobalFunctionHistory getGlobalFunctionHistoryNamed(String fullName) {
/*  57 */     String temp = fullName.substring(0, fullName.indexOf("("));
/*  58 */     String namespaceName = temp.substring(0, temp.lastIndexOf("."));
/*  59 */     NamespaceHistory namespaceHistory = getNamespaceHistoryNamed(namespaceName);
/*  60 */     if (namespaceHistory == null)
/*  61 */       return null; 
/*  62 */     GlobalFunctionHistoryGroup globalFunctionHistories = namespaceHistory.getGlobalFunctionHistories();
/*  63 */     return (GlobalFunctionHistory)globalFunctionHistories.get(fullName);
/*     */   }
/*     */   
/*     */   public GlobalVariableHistory getGlobalVariableHistoryNamed(String fullName) {
/*  67 */     String namespaceName = fullName.substring(0, fullName.lastIndexOf("."));
/*  68 */     NamespaceHistory namespaceHistory = getNamespaceHistoryNamed(namespaceName);
/*  69 */     if (namespaceHistory == null)
/*  70 */       return null; 
/*  71 */     GlobalVariableHistoryGroup globalVariableHistories = namespaceHistory.getGlobalVariableHistories();
/*  72 */     return (GlobalVariableHistory)globalVariableHistories.get(fullName);
/*     */   }
/*     */   
/*     */   public ClassHistory getClassHistoryNamed(String fullName) {
/*  76 */     String namespaceName = fullName.substring(0, fullName.lastIndexOf("."));
/*  77 */     NamespaceHistory namespaceHistory = getNamespaceHistoryNamed(namespaceName);
/*  78 */     if (namespaceHistory == null)
/*  79 */       return null; 
/*  80 */     ClassHistoryGroup classHistories = namespaceHistory.getClassHistories();
/*  81 */     return (ClassHistory)classHistories.get(fullName);
/*     */   }
/*     */   
/*     */   public MethodHistory getMethodHistoryNamed(String fullName) {
/*  85 */     String methodName = fullName.substring(0, fullName.indexOf("("));
/*  86 */     String fullClassName = methodName.substring(0, methodName.lastIndexOf("."));
/*  87 */     ClassHistory classHistory = getClassHistoryNamed(fullClassName);
/*  88 */     if (classHistory == null)
/*  89 */       return null; 
/*  90 */     MethodHistoryGroup methodHistories = classHistory.getMethodHistories();
/*  91 */     return (MethodHistory)methodHistories.get(fullName);
/*     */   }
/*     */   
/*     */   public AttributeHistory getAttributeHistoryNamed(String fullName) {
/*  95 */     String fullClassName = fullName.substring(0, fullName.lastIndexOf("."));
/*  96 */     ClassHistory classHistory = getClassHistoryNamed(fullClassName);
/*  97 */     if (classHistory == null)
/*  98 */       return null; 
/*  99 */     AttributeHistoryGroup attributeHistories = classHistory.getAttributeHistories();
/* 100 */     return (AttributeHistory)attributeHistories.get(fullName);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hism\\utils\HismoWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */