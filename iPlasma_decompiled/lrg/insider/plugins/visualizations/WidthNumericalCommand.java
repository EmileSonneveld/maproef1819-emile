/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ 
/*    */ class WidthNumericalCommand extends AbstractNumericalCommand {
/*    */   double theValue;
/*    */   
/*    */   public WidthNumericalCommand(AbstractEntityInterface anEntity) {
/*  8 */     this.theValue = 0.0D;
/*    */     
/* 10 */     this.entity = anEntity;
/*    */   } private AbstractEntityInterface entity;
/*    */   public double execute() {
/* 13 */     AbstractEntityInterface receiverEntity = (AbstractEntityInterface)this.receiver;
/*    */     
/* 15 */     double offset = 5.0D;
/*    */     
/* 17 */     if ((new IsAttribute()).applyFilter(receiverEntity)) {
/* 18 */       this.theValue = receiverEntity.getGroup("methods accessing field").exclude(this.entity.contains("method group")).size() + offset;
/*    */     } else {
/* 20 */       this.theValue = receiverEntity.getGroup("operations calling me").size() + offset;
/* 21 */     }  return this.theValue;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\WidthNumericalCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */