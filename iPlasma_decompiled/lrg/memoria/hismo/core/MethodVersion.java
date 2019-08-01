/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ public class MethodVersion
/*    */   extends Method implements AbstractVersion {
/*    */   private String versionName;
/*    */   
/*    */   public MethodVersion(String versionName, Method meth) {
/* 10 */     super(meth);
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


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\MethodVersion.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */