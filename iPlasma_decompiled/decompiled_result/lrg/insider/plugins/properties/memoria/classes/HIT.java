/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.properties.memoria.classes.HIT;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ 
/*    */ public class HIT
/*    */   extends PropertyComputer {
/* 13 */   public HIT() { super("HIT", "Height of Inheritance Tree", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   private int level(DataAbstraction actcls, DataAbstraction parent) {
/* 17 */     int max = 0;
/* 18 */     ModelElementList modelElementList = actcls.getDescendants();
/*    */ 
/*    */ 
/*    */     
/* 22 */     if (modelElementList.size() == 0) return 0;
/*    */     
/* 24 */     for (int i = 0; i < modelElementList.size(); i++) {
/*    */       try {
/* 26 */         DataAbstraction crt = (DataAbstraction)modelElementList.get(i);
/* 27 */         if (parent != crt) {
/*    */ 
/*    */ 
/*    */           
/* 31 */           int aux = level(crt, actcls);
/* 32 */           if (aux > max) max = aux; 
/*    */         } 
/*    */       } catch (ClassCastException e) {}
/* 35 */     }  return max + 1;
/*    */   }
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 39 */     if (!(anEntity instanceof lrg.memoria.core.Class)) {
/* 40 */       return null;
/*    */     }
/* 42 */     GroupEntity derivedClasses = anEntity.getGroup("derived classes").getGroup("derived classes");
/*    */ 
/*    */     
/* 45 */     return new ResultEntity(level((DataAbstraction)anEntity, null));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\HIT.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */