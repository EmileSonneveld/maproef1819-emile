/*    */ package classes.lrg.insider.plugins.properties.memoria.subsystems;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InstabilityFactor
/*    */   extends PropertyComputer
/*    */ {
/* 19 */   public InstabilityFactor() { super("IF", "Instability Factor", "subsystem", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aPackage) {
/* 24 */     double fanout = aPackage.getGroup("fanout class group").size();
/* 25 */     double fanin = aPackage.getGroup("fanin class group").size();
/*    */     
/* 27 */     double totalDependencies = fanin + fanout;
/* 28 */     if (totalDependencies == 0.0D) return new ResultEntity(2.0D);
/*    */     
/* 30 */     return new ResultEntity(fanout / totalDependencies);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\subsystems\InstabilityFactor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */