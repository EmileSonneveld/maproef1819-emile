/*    */ package classes.lrg.insider.plugins.core.groups.memoria.uses;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.Package;
/*    */ import lrg.memoria.core.System;
/*    */ import lrg.memoria.core.Type;
/*    */ import lrg.memoria.core.Variable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TypeUsedInDeclaration
/*    */   extends GroupBuilder
/*    */ {
/* 20 */   public TypeUsedInDeclaration() { super("group of variables of this type", "", "class"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 24 */     ArrayList resultList = new ArrayList();
/*    */     
/* 26 */     if (!(anEntity instanceof Type)) {
/* 27 */       return resultList;
/*    */     }
/* 29 */     Type aType = (Type)anEntity;
/* 30 */     if (!(aType.getScope() instanceof Package)) {
/* 31 */       return resultList;
/*    */     }
/* 33 */     System theSystem = ((Package)aType.getScope()).getSystem();
/*    */     
/* 35 */     GroupEntity aGroup = theSystem.getGroup("attribute group").union(
/* 36 */         theSystem.getGroup("global variable group")).union(
/* 37 */         theSystem.getGroup("parameter group")).union(
/* 38 */         theSystem.getGroup("local variable group"));
/*    */     
/* 40 */     Iterator it = aGroup.iterator();
/*    */     
/* 42 */     while (it.hasNext()) {
/* 43 */       Variable aVariable = (Variable)it.next();
/* 44 */       if (aVariable.getType() == aType) resultList.add(aVariable);
/*    */     
/*    */     } 
/* 47 */     return resultList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memori\\uses\TypeUsedInDeclaration.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */