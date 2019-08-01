/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*     */ import lrg.insider.plugins.filters.memoria.variables.IsAttribute;
/*     */ import lrg.insider.plugins.visualizations.AttributeUsage;
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
/*     */ class null
/*     */   extends AbstractNumericalCommand
/*     */ {
/*     */   public double execute() {
/* 109 */     double value, offset = 7.0D;
/* 110 */     AbstractEntityInterface receiverEntity = (AbstractEntityInterface)this.receiver;
/* 111 */     if (theAttribute.equals(receiverEntity)) { value = 10.0D; }
/* 112 */     else if ((new IsAttribute()).applyFilter(receiverEntity)) { value = ((Double)receiverEntity.getProperty("NMAV").getValue()).doubleValue() + offset; }
/* 113 */     else if ((new IsAccessor()).applyFilter(receiverEntity)) { value = ((Double)receiverEntity.getProperty("CM").getValue()).doubleValue() + offset; }
/* 114 */     else { value = ((Double)receiverEntity.getProperty("LOC").getValue()).doubleValue() + offset; }
/* 115 */      return value;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\AttributeUsage$1$1$2$2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */