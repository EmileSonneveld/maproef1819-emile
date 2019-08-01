/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ import lrg.memoria.core.Package;
/*    */ 
/*    */ public class PackageVersion
/*    */   extends Package implements AbstractVersion {
/*    */   public PackageVersion(String versionName, Package pack) {
/*  8 */     super(pack);
/*  9 */     this.versionName = versionName;
/*    */   }
/*    */   private String versionName;
/*    */   
/* 13 */   public String versionName() { return this.versionName; }
/*    */ 
/*    */ 
/*    */   
/* 17 */   public int compareTo(Object ver2) { return this.versionName.compareTo(((AbstractVersion)ver2).versionName()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\PackageVersion.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */