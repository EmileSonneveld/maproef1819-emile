/*    */ package classes.lrg.insider.plugins.core.groups.memoria.containment;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.core.groups.memoria.containment.NamespaceHasTypes;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Namespace;
/*    */ import lrg.memoria.core.Type;
/*    */ 
/*    */ public class NamespaceHasTypes extends GroupBuilder {
/* 12 */   public NamespaceHasTypes() { super("type group", "", "namespace"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ModelElementList<Type> buildGroup(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof Namespace)) {
/* 18 */       return new ModelElementList();
/*    */     }
/* 20 */     return ((Namespace)anEntity).getTypesList();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memoria\containment\NamespaceHasTypes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */