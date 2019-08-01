/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.plugins.filters.memoria.variables.IsAttribute;
/*    */ import lrg.insider.plugins.visualizations.HeightNumericalCommand;
/*    */ import lrg.jMondrian.commands.AbstractNumericalCommand;
/*    */ 
/*    */ class HeightNumericalCommand
/*    */   extends AbstractNumericalCommand {
/* 10 */   public HeightNumericalCommand(AbstractEntityInterface anEntity) { this.entity = anEntity; }
/*    */   private AbstractEntityInterface entity;
/*    */   public double execute() {
/* 13 */     double theValue = 0.0D;
/* 14 */     AbstractEntityInterface receiverEntity = (AbstractEntityInterface)this.receiver;
/*    */     
/* 16 */     double offset = 5.0D;
/*    */     
/* 18 */     if ((new IsAttribute()).applyFilter(receiverEntity)) {
/* 19 */       theValue = receiverEntity.getGroup("methods accessing field").intersect(this.entity.contains("method group")).size() + offset;
/*    */     } else {
/*    */       
/* 22 */       theValue = ((Double)receiverEntity.getProperty("LOC").getValue()).doubleValue() + offset;
/* 23 */     }  return theValue;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\HeightNumericalCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */