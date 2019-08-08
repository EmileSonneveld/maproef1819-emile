/*    */ package classes.lrg.insider.plugins.core.groups.memoria.containment;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.Package;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PackageHasGlobalFunctions
/*    */   extends GroupBuilder
/*    */ {
/* 17 */   public PackageHasGlobalFunctions() { super("global function group", "global functions", new String[] { "package", "namespace" }); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 21 */     ArrayList functionsList = new ArrayList();
/* 22 */     if (!(anEntity instanceof Package))
/* 23 */       return functionsList; 
/* 24 */     return ((Package)anEntity).getGlobalFunctionsList();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memoria\containment\PackageHasGlobalFunctions.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */