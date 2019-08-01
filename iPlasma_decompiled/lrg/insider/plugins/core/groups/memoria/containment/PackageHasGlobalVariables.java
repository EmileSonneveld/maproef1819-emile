/*    */ package classes.lrg.insider.plugins.core.groups.memoria.containment;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.GlobalVariable;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Package;
/*    */ 
/*    */ public class PackageHasGlobalVariables
/*    */   extends GroupBuilder {
/* 12 */   public PackageHasGlobalVariables() { super("global variable group", "", new String[] { "package", "namespace" }); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 16 */     ArrayList onlyGlobalVariables = new ArrayList();
/* 17 */     if (!(anEntity instanceof Package)) {
/* 18 */       return onlyGlobalVariables;
/*    */     }
/* 20 */     ModelElementList variablesList = ((Package)anEntity).getGlobalVariablesList();
/* 21 */     for (int i = 0; i < variablesList.size(); i++) {
/* 22 */       if (variablesList.get(i) instanceof GlobalVariable && (
/* 23 */         (GlobalVariable)variablesList.get(i)).getRefersTo() == null)
/* 24 */         onlyGlobalVariables.add(variablesList.get(i)); 
/* 25 */     }  return onlyGlobalVariables;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memoria\containment\PackageHasGlobalVariables.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */