/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.SortedSet;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VersionsList
/*    */ {
/* 12 */   private TreeSet<AbstractVersion> list = new TreeSet();
/*    */ 
/*    */ 
/*    */   
/* 16 */   public boolean equals(VersionsList aVersionList) { return this.list.equals(aVersionList.list); }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public void add(AbstractVersion version) { this.list.add(version); }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public AbstractVersion firstVersion() { return (AbstractVersion)this.list.first(); }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public AbstractVersion lastVersion() { return (AbstractVersion)this.list.last(); }
/*    */ 
/*    */   
/*    */   public SortedSet getVersionNames() {
/* 32 */     SortedSet names = new TreeSet();
/* 33 */     for (Iterator it = this.list.iterator(); it.hasNext();)
/* 34 */       names.add(((AbstractVersion)it.next()).versionName()); 
/* 35 */     return names;
/*    */   }
/*    */ 
/*    */   
/* 39 */   public Iterator<AbstractVersion> iterator() { return this.list.iterator(); }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public int size() { return this.list.size(); }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public void addAll(VersionsList versions) { this.list.addAll(versions.list); }
/*    */ 
/*    */ 
/*    */   
/* 51 */   public boolean contains(AbstractVersion version) { return this.list.contains(version); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\VersionsList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */