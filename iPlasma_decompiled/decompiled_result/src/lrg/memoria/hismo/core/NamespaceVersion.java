/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ import lrg.memoria.core.Namespace;
/*    */ 
/*    */ public class NamespaceVersion
/*    */   extends Namespace implements AbstractVersion {
/*    */   private String versionName;
/*    */   
/*    */   public NamespaceVersion(String versionName, Namespace nsp) {
/* 10 */     super(nsp);
/* 11 */     this.versionName = versionName;
/*    */   }
/*    */ 
/*    */   
/* 15 */   public String versionName() { return this.versionName; }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public int compareTo(Object ver2) { return this.versionName.compareTo(((AbstractVersion)ver2).versionName()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\NamespaceVersion.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */