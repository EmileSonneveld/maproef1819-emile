/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsConstructor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TCC
/*    */   extends PropertyComputer
/*    */ {
/* 23 */   public TCC() { super("TCC", "Tight Class Cohesion", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 27 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsConstructor());
/* 28 */     GroupEntity methods = anEntity.contains("method group").applyFilter(notComposedFilteringRule);
/* 29 */     double cohesivePairs = 0.0D, nrOfMethods = methods.size();
/*    */     
/* 31 */     if (nrOfMethods == 0.0D || nrOfMethods == 1.0D) return new ResultEntity(nrOfMethods);
/*    */     
/* 33 */     Iterator it = methods.iterator();
/* 34 */     while (it.hasNext()) {
/* 35 */       AbstractEntity crtMethod = (AbstractEntity)it.next();
/* 36 */       GroupEntity accessingMethods = crtMethod.uses("variables accessed").isUsed("methods accessing variable").exclude(crtMethod);
/* 37 */       cohesivePairs += accessingMethods.intersect(methods).distinct().size();
/*    */     } 
/*    */     
/* 40 */     return new ResultEntity(cohesivePairs / nrOfMethods * (nrOfMethods - 1.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\TCC.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */