/*    */ package lrg.memoria.core;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class Subsystem
/*    */   extends NamedModelElement
/*    */ {
/*    */   private ArrayList<Package> thePackages;
/*    */   
/*    */   public Subsystem(String subsystemName, ArrayList packages) {
/* 11 */     super(subsystemName);
/* 12 */     this.thePackages = packages;
/*    */   }
/*    */ 
/*    */   
/* 16 */   public String getName() { return "subsytem_" + this.name; }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public ArrayList<Package> getPackages() { return this.thePackages; }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public void accept(ModelVisitor v) { v.visitSubsystem(this); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Subsystem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */