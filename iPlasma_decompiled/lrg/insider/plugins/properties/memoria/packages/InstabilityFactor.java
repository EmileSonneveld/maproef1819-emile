/*    */ package classes.lrg.insider.plugins.properties.memoria.packages;
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
/*    */ public class InstabilityFactor
/*    */   extends PropertyComputer
/*    */ {
/* 17 */   public InstabilityFactor() { super("IF", "Instability Factor", "package", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 22 */     double fanout = anEntity.getGroup("fanout class group").size();
/* 23 */     double fanin = anEntity.getGroup("fanin class group").size();
/*    */     
/* 25 */     double totalDependencies = fanin + fanout;
/* 26 */     if (totalDependencies == 0.0D) return new ResultEntity(2.0D);
/*    */     
/* 28 */     return new ResultEntity(fanout / totalDependencies);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\packages\InstabilityFactor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */