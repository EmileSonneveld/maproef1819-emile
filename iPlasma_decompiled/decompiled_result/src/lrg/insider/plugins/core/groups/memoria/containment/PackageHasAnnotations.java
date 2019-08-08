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
/*    */ public class PackageHasAnnotations
/*    */   extends GroupBuilder
/*    */ {
/* 17 */   public PackageHasAnnotations() { super("annotations group", "the annotations", "package"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 21 */     ArrayList annotationsList = new ArrayList();
/* 22 */     if (!(anEntity instanceof Package)) return annotationsList; 
/* 23 */     return ((Package)anEntity).getAnnotationsList();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memoria\containment\PackageHasAnnotations.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */