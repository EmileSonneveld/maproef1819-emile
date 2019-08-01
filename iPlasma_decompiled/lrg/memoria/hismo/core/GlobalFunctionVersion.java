/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ import lrg.memoria.core.GlobalFunction;
/*    */ 
/*    */ public class GlobalFunctionVersion extends GlobalFunction implements AbstractVersion {
/*    */   private String versionName;
/*    */   
/*    */   public GlobalFunctionVersion(String versionName, GlobalFunction gf) {
/*  9 */     super(gf);
/* 10 */     this.versionName = versionName;
/*    */   }
/*    */ 
/*    */   
/* 14 */   public String versionName() { return this.versionName; }
/*    */ 
/*    */ 
/*    */   
/* 18 */   public int compareTo(Object ver2) { return this.versionName.compareTo(((AbstractVersion)ver2).versionName()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\GlobalFunctionVersion.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */