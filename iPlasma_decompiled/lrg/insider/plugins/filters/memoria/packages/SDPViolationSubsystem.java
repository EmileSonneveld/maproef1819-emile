/*    */ package classes.lrg.insider.plugins.filters.memoria.packages;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.insider.plugins.filters.memoria.packages.SDPViolationSubsystem;
/*    */ 
/*    */ public class SDPViolationSubsystem
/*    */   extends FilteringRule
/*    */ {
/* 14 */   public SDPViolationSubsystem() { super(new Descriptor("SDP Violation (subsystem)", "subsystem")); }
/*    */ 
/*    */   
/*    */   public static ArrayList<AbstractEntityInterface> SDPBreakers(AbstractEntityInterface aSubsystem) {
/* 18 */     ArrayList<AbstractEntityInterface> sdpBreakers = new ArrayList<AbstractEntityInterface>();
/*    */     
/* 20 */     ArrayList<AbstractEntityInterface> temp = aSubsystem.getGroup("fanout class group").belongsTo("package").getProperty("Subsystem").getValueAsCollection();
/*    */     
/* 22 */     if (temp == null) return sdpBreakers; 
/* 23 */     ArrayList<AbstractEntityInterface> usedSubsystems = (new GroupEntity("subsystems", temp)).distinct().getElements();
/*    */     
/* 25 */     double referenceIFValue = ((Double)aSubsystem.getProperty("IF").getValue()).doubleValue();
/*    */     
/* 27 */     for (AbstractEntityInterface crtSubsystemResult : usedSubsystems) {
/* 28 */       AbstractEntityInterface crtSubsystem = (AbstractEntityInterface)((ResultEntity)crtSubsystemResult).getValue();
/* 29 */       double crtIFValue = ((Double)crtSubsystem.getProperty("IF").getValue()).doubleValue();
/* 30 */       if (referenceIFValue < crtIFValue * 0.9D) sdpBreakers.add(crtSubsystem); 
/*    */     } 
/* 32 */     return sdpBreakers;
/*    */   }
/*    */ 
/*    */   
/* 36 */   public boolean applyFilter(AbstractEntityInterface aPackage) { return (SDPBreakers(aPackage).size() > 0); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\packages\SDPViolationSubsystem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */