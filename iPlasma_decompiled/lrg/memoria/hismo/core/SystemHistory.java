/*     */ package lrg.memoria.hismo.core;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.Namespace;
/*     */ import lrg.memoria.core.Package;
/*     */ 
/*     */ public class SystemHistory
/*     */   extends AbstractHistory
/*     */ {
/*     */   private String name;
/*     */   private PackageHistoryGroup packageHistories;
/*     */   private NamespaceHistoryGroup namespaceHistories;
/*     */   
/*     */   public SystemHistory(VersionsList versions, String name) {
/*  16 */     super(versions);
/*  17 */     this.name = name;
/*     */   }
/*     */   
/*     */   public SystemHistory(String name) {
/*  21 */     this.name = name;
/*  22 */     initializeInnerHistories();
/*     */   }
/*     */   
/*     */   protected void initializeInnerHistories() {
/*  26 */     this.packageHistories = new PackageHistoryGroup();
/*  27 */     this.namespaceHistories = new NamespaceHistoryGroup();
/*     */   }
/*     */ 
/*     */   
/*  31 */   public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */   
/*  35 */   public String getFullName() { return this.name; }
/*     */ 
/*     */ 
/*     */   
/*  39 */   public PackageHistoryGroup getPackageHistories() { return this.packageHistories; }
/*     */ 
/*     */ 
/*     */   
/*  43 */   public NamespaceHistoryGroup getNamespaceHistories() { return this.namespaceHistories; }
/*     */ 
/*     */   
/*     */   public ClassHistoryGroup getClassHistories() {
/*  47 */     ClassHistoryGroup allClassHistories = new ClassHistoryGroup();
/*     */     
/*  49 */     for (Iterator<AbstractHistory> it = getNamespaceHistories().getHistoriesCollection().iterator(); it.hasNext(); ) {
/*  50 */       ClassHistoryGroup currentClassHistories = ((NamespaceHistory)it.next()).getClassHistories();
/*  51 */       allClassHistories.addAll(currentClassHistories);
/*     */     } 
/*  53 */     return allClassHistories;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getVersionNamePreviousTo(SystemHistory aSystemHistory, String aVersionName) {
/*  63 */     String previous = null;
/*  64 */     for (Iterator it = aSystemHistory.getVersionIterator(); it.hasNext(); ) {
/*  65 */       String current = ((SystemVersion)it.next()).versionName();
/*  66 */       if (current.equals(aVersionName))
/*  67 */         return previous; 
/*  68 */       previous = current;
/*     */     } 
/*  70 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateInnerHistories(AbstractVersion version) {
/*  76 */     updatePackageHistories((SystemVersion)version);
/*  77 */     updateNamespaceHistories((SystemVersion)version);
/*     */   }
/*     */   
/*     */   private void updatePackageHistories(SystemVersion version) {
/*  81 */     ModelElementList<Package> currentPackages = version.getPackages();
/*     */ 
/*     */ 
/*     */     
/*  85 */     for (Package currentPackage : currentPackages) {
/*  86 */       PackageVersion currentPackageVersion = new PackageVersion(version.versionName(), currentPackage);
/*  87 */       String currentPackageName = currentPackage.getName();
/*  88 */       AbstractHistory currentPackageHistory = this.packageHistories.get(currentPackageName);
/*  89 */       if (currentPackageHistory == null) {
/*  90 */         currentPackageHistory = new PackageHistory();
/*  91 */         currentPackageHistory.addVersion(currentPackageVersion);
/*  92 */         this.packageHistories.put(currentPackageHistory); continue;
/*     */       } 
/*  94 */       currentPackageHistory.addVersion(currentPackageVersion);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateNamespaceHistories(SystemVersion version) {
/*  99 */     ModelElementList<Namespace> currentNamespaces = version.getNamespaces();
/*     */ 
/*     */ 
/*     */     
/* 103 */     for (Namespace currentNamespace : currentNamespaces) {
/* 104 */       NamespaceVersion currentNamespaceVersion = new NamespaceVersion(version.versionName(), currentNamespace);
/* 105 */       String currentNamespaceName = currentNamespace.getName();
/* 106 */       AbstractHistory currentNamespaceHistory = this.namespaceHistories.get(currentNamespaceName);
/* 107 */       if (currentNamespaceHistory == null) {
/* 108 */         currentNamespaceHistory = new NamespaceHistory();
/* 109 */         currentNamespaceHistory.addVersion(currentNamespaceVersion);
/* 110 */         this.namespaceHistories.put(currentNamespaceHistory); continue;
/*     */       } 
/* 112 */       currentNamespaceHistory.addVersion(currentNamespaceVersion);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\SystemHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */