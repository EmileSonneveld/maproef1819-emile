/*    */ package classes.lrg.insider.plugins.core.groups.memoria.containment;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.Package;
/*    */ 
/*    */ public class PackageHasClasses
/*    */   extends GroupBuilder
/*    */ {
/* 11 */   public PackageHasClasses() { super("class group", "", "package"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 16 */     if (!(anEntity instanceof Package)) {
/* 17 */       return new ArrayList();
/*    */     }
/* 19 */     return ((Package)anEntity).getAbstractDataTypes();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memoria\containment\PackageHasClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */