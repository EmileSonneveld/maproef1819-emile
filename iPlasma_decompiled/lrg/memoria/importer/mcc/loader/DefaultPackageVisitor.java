/*    */ package lrg.memoria.importer.mcc.loader;
/*    */ 
/*    */ import lrg.memoria.core.Package;
/*    */ 
/*    */ public class DefaultPackageVisitor
/*    */   implements PackageVisitor {
/*    */   private Integer id;
/*    */   private String name;
/*    */   
/* 10 */   public void setId(Integer id) { this.id = id; }
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 14 */     String temp = name.replaceAll("/", ".").replaceAll("\\\\", ".");
/* 15 */     while (temp.startsWith("."))
/* 16 */       temp = temp.substring(1, temp.length()); 
/* 17 */     this.name = temp;
/*    */   }
/*    */   
/*    */   public void addPackage() {
/* 21 */     Package p = new Package(this.name);
/* 22 */     p.setStatute(1);
/* 23 */     Loader.getInstance().addPackage(this.id, p);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\DefaultPackageVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */