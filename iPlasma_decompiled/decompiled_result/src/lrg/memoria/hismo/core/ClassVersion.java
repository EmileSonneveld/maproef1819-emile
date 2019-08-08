/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassVersion
/*    */   extends Class
/*    */   implements AbstractVersion
/*    */ {
/*    */   private String versionName;
/*    */   
/*    */   public ClassVersion(String versionName, Class cls) {
/* 17 */     super(cls);
/* 18 */     this.versionName = versionName;
/*    */   }
/*    */ 
/*    */   
/* 22 */   public String versionName() { return this.versionName; }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public int compareTo(Object ver2) { return this.versionName.compareTo(((AbstractVersion)ver2).versionName()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\ClassVersion.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */