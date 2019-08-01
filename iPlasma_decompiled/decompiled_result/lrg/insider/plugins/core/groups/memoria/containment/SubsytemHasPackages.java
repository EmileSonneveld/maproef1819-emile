/*    */ package classes.lrg.insider.plugins.core.groups.memoria.containment;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.Subsystem;
/*    */ 
/*    */ 
/*    */ public class SubsytemHasPackages
/*    */   extends GroupBuilder
/*    */ {
/* 12 */   public SubsytemHasPackages() { super("package group", "", "subsystem"); }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public ArrayList buildGroup(AbstractEntityInterface aSubsystem) { return ((Subsystem)aSubsystem).getPackages(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memoria\containment\SubsytemHasPackages.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */