/*    */ package lrg.memoria.importer.mcc.loader;
/*    */ 
/*    */ import lrg.memoria.core.Namespace;
/*    */ 
/*    */ public class DefaultNamespaceVisitor
/*    */   implements NamespaceVisitor {
/*    */   private Integer id;
/*    */   private String name;
/*    */   
/* 10 */   public void setId(Integer id) { this.id = id; }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public void setName(String name) { this.name = name; }
/*    */ 
/*    */   
/*    */   public void addNamespace() {
/* 18 */     Namespace n = new Namespace(this.name);
/* 19 */     n.setStatute(1);
/* 20 */     Loader.getInstance().addNamespace(this.id, n);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\DefaultNamespaceVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */