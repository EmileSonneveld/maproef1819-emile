/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IsAbstract;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*     */ import lrg.insider.plugins.filters.memoria.variables.IsAttribute;
/*     */ import lrg.insider.plugins.visualizations.MethodInteraction;
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
/*     */ class null
/*     */   extends AbstractNumericalCommand
/*     */ {
/*     */   public double execute() {
/* 115 */     double value, offset = 7.0D;
/* 116 */     AbstractEntityInterface receiverEntity = (AbstractEntityInterface)this.receiver;
/* 117 */     if (theMethod.equals(receiverEntity)) { value = 300.0D; }
/* 118 */     else if ((new IsAttribute()).applyFilter(receiverEntity)) { value = ((Double)receiverEntity.getProperty("NMAV").getValue()).doubleValue() + offset; }
/* 119 */     else if ((new IsAbstract()).applyFilter(receiverEntity)) { value = ((Double)receiverEntity.getProperty("CM").getValue()).doubleValue() + offset; }
/* 120 */     else if ((new IsAccessor()).applyFilter(receiverEntity)) { value = ((Double)receiverEntity.getProperty("CM").getValue()).doubleValue() + offset; }
/* 121 */     else { value = ((Double)receiverEntity.getProperty("CM").getValue()).doubleValue() + offset; }
/*     */     
/* 123 */     return value;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\MethodInteraction$1$1$2$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */