/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ import lrg.memoria.core.System;
/*    */ 
/*    */ public class SystemVersion
/*    */   extends System implements AbstractVersion {
/*    */   private String versionName;
/*    */   
/*    */   public SystemVersion(String versionName, System system) {
/* 10 */     super(system);
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


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\SystemVersion.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */