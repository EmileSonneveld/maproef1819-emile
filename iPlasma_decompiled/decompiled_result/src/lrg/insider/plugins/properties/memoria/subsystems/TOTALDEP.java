/*    */ package classes.lrg.insider.plugins.properties.memoria.subsystems;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class TOTALDEP
/*    */   extends PropertyComputer {
/*  9 */   public TOTALDEP() { super("TOTALDEP", "Fanin plus Fanout", "subsystem", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aPackage) {
/* 13 */     double fanout = aPackage.getGroup("fanout class group").size();
/* 14 */     double fanin = aPackage.getGroup("fanin class group").size();
/*    */     
/* 16 */     return new ResultEntity(fanin + fanout);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\subsystems\TOTALDEP.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */