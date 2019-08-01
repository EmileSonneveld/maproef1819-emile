/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IsAbstract;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*     */ import lrg.insider.plugins.filters.memoria.variables.IsAttribute;
/*     */ import lrg.insider.plugins.filters.memoria.variables.IsConstant;
/*     */ import lrg.insider.plugins.visualizations.MethodInteraction;
/*     */ import lrg.insider.plugins.visualizations.OurColors;
/*     */ import lrg.jMondrian.commands.AbstractNumericalCommand;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class null
/*     */   extends AbstractNumericalCommand
/*     */ {
/*     */   public double execute() {
/* 141 */     AbstractEntityInterface receiverEntity = (AbstractEntityInterface)this.receiver;
/* 142 */     if (receiverEntity.equals(theMethod)) return OurColors.VERY_LIGHT_GRAY; 
/* 143 */     if ((new IsAttribute()).applyFilter(receiverEntity)) {
/* 144 */       if ((new IsConstant()).applyFilter(receiverEntity)) return OurColors.CYAN; 
/* 145 */       return OurColors.BLUE;
/* 146 */     }  if (theyCallMe.intersect(receiverEntity).size() > 0) return OurColors.RED; 
/* 147 */     if (overridenMethodCalls.intersect(receiverEntity).size() > 0) return OurColors.PINK; 
/* 148 */     if (iCallThem.intersect(receiverEntity).size() > 0) {
/* 149 */       if ((new IsAccessor()).applyFilter(receiverEntity)) return OurColors.ACCESSOR_BLUE; 
/* 150 */       if ((new IsAbstract()).applyFilter(receiverEntity)) return OurColors.ABSTRACT_BLUE; 
/* 151 */       return OurColors.LILA;
/*     */     } 
/* 153 */     return OurColors.LILA;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\MethodInteraction$1$1$2$3.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */