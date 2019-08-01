/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HeavyServiceProviders
/*    */   extends GroupBuilder
/*    */ {
/* 18 */   public HeavyServiceProviders() { super("heavy service providers", "", "system"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aSystem) {
/* 22 */     GroupEntity heavyCoupledMethods = aSystem.getGroup("method group").applyFilter("model function").applyFilter("Heavy Coupling");
/*    */     
/* 24 */     return heavyCoupledMethods.getGroup("external service providers").distinct().getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\HeavyServiceProviders.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */