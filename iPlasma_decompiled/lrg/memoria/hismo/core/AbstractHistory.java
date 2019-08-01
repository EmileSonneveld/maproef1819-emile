/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.memoria.core.NamedModelElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractHistory
/*    */   extends AbstractEntity
/*    */ {
/*    */   protected VersionsList versions;
/*    */   
/* 14 */   public AbstractHistory() { this.versions = new VersionsList(); }
/*    */ 
/*    */   
/*    */   public AbstractHistory(VersionsList versions) {
/* 18 */     this.versions = new VersionsList();
/* 19 */     initializeInnerHistories();
/* 20 */     for (Iterator it = versions.iterator(); it.hasNext();) {
/* 21 */       addVersion((AbstractVersion)it.next());
/*    */     }
/*    */   }
/*    */   
/* 25 */   public int getNumberOfVersions() { return this.versions.size(); }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public Iterator getVersionIterator() { return this.versions.iterator(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractVersion getVersionForName(String name) {
/* 34 */     for (Iterator it = getVersions().iterator(); it.hasNext(); ) {
/* 35 */       AbstractVersion currentVersion = (AbstractVersion)it.next();
/* 36 */       if (currentVersion.versionName().equals(name))
/* 37 */         return currentVersion; 
/*    */     } 
/* 39 */     return null;
/*    */   }
/*    */ 
/*    */   
/* 43 */   public AbstractVersion getFirstVersion() { return this.versions.firstVersion(); }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public AbstractVersion getLastVersion() { return this.versions.lastVersion(); }
/*    */ 
/*    */ 
/*    */   
/* 51 */   public VersionsList getVersions() { return this.versions; }
/*    */ 
/*    */   
/*    */   public boolean equals(AbstractHistory aHistory) {
/* 55 */     if (getFullName().equals(aHistory.getFullName()) && 
/* 56 */       this.versions.equals(aHistory.versions)) return true; 
/*    */     return false;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 61 */     AbstractVersion firstVersion = this.versions.firstVersion();
/* 62 */     return firstVersion.getName();
/*    */   }
/*    */   
/*    */   public String getFullName() {
/* 66 */     AbstractVersion firstVersion = this.versions.firstVersion();
/* 67 */     return firstVersion.getFullName();
/*    */   }
/*    */   
/*    */   public void addVersion(AbstractVersion version) {
/* 71 */     this.versions.add(version);
/* 72 */     updateInnerHistories(version);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isLibrary() {
/* 79 */     for (Iterator<AbstractVersion> it = getVersions().iterator(); it.hasNext();) {
/* 80 */       if (((NamedModelElement)it.next()).getStatute() == 1)
/* 81 */         return false; 
/*    */     } 
/* 83 */     return true;
/*    */   }
/*    */   
/*    */   protected abstract void initializeInnerHistories();
/*    */   
/*    */   protected abstract void updateInnerHistories(AbstractVersion paramAbstractVersion);
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\AbstractHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */