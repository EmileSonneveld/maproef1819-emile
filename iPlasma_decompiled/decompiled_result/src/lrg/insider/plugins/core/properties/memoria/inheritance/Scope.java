/*    */ package classes.lrg.insider.plugins.core.properties.memoria.inheritance;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.core.properties.memoria.inheritance.Scope;
/*    */ import lrg.memoria.core.InheritanceRelation;
/*    */ import lrg.memoria.core.System;
/*    */ 
/*    */ public class Scope
/*    */   extends PropertyComputer {
/*    */   private static System currentSystem;
/* 14 */   private static ArrayList<InheritanceRelation> relation = new ArrayList();
/*    */ 
/*    */   
/* 17 */   public static void clear() { relation.clear(); }
/*    */ 
/*    */   
/*    */   public static void registerScopeProperty(System s, InheritanceRelation rel) {
/* 21 */     currentSystem = s;
/* 22 */     relation.add(rel);
/*    */   }
/*    */   
/*    */   public static ArrayList getElements() {
/* 26 */     tmp = new ArrayList();
/* 27 */     tmp.addAll(relation);
/* 28 */     return tmp;
/*    */   }
/*    */ 
/*    */   
/* 32 */   public Scope() { super("scope", "The scope of the inheritance relation", "inheritance relation", "entity"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 36 */     if (anEntity instanceof InheritanceRelation) {
/* 37 */       return new ResultEntity(currentSystem);
/*    */     }
/* 39 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\inheritance\Scope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */