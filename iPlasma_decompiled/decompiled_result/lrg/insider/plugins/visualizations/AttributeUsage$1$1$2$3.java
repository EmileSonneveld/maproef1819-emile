/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*     */ import lrg.insider.plugins.filters.memoria.variables.IsAttribute;
/*     */ import lrg.insider.plugins.visualizations.AttributeUsage;
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
/*     */ class null
/*     */   extends AbstractNumericalCommand
/*     */ {
/*     */   public double execute() {
/* 120 */     AbstractEntityInterface receiverEntity = (AbstractEntityInterface)this.receiver;
/* 121 */     if ((new IsAttribute()).applyFilter(receiverEntity)) return OurColors.VERY_LIGHT_GRAY; 
/* 122 */     if (clientMethods.intersect(receiverEntity).size() > 0 && (
/* 123 */       new IsAccessor()).applyFilter(receiverEntity)) {
/* 124 */       return OurColors.ACCESSOR_BLUE;
/*     */     }
/* 126 */     return OurColors.RED;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\AttributeUsage$1$1$2$3.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */