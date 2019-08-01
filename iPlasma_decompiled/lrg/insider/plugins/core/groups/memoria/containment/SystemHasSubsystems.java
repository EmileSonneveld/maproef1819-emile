/*    */ package classes.lrg.insider.plugins.core.groups.memoria.containment;
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.plugins.core.groups.memoria.containment.SystemHasSubsystems;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Subsystem;
/*    */ import lrg.memoria.core.System;
/*    */ 
/*    */ public class SystemHasSubsystems extends GroupBuilder {
/* 10 */   public SystemHasSubsystems() { super("subsystem group", "", "system"); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public ModelElementList<Subsystem> buildGroup(AbstractEntityInterface theSystem) { return ((System)theSystem).getSubsystems(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memoria\containment\SystemHasSubsystems.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */