/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.plugins.visualizations.AbstractnessInstabilityPlot;
/*    */ import lrg.insider.plugins.visualizations.OurColors;
/*    */ import lrg.jMondrian.commands.AbstractNumericalCommand;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   extends AbstractNumericalCommand
/*    */ {
/*    */   public double execute() {
/* 41 */     AbstractEntityInterface aPackage = (AbstractEntityInterface)this.receiver;
/* 42 */     double SAPDistance = ((Double)aPackage.getProperty("SAP Distance").getValue()).doubleValue();
/* 43 */     if (SAPDistance < -0.7D) return OurColors.RED; 
/* 44 */     if (SAPDistance >= -0.7D && SAPDistance <= -0.3D) return OurColors.BLACK; 
/* 45 */     if (SAPDistance > -0.3D && SAPDistance < 0.3D) return OurColors.GREEN; 
/* 46 */     if (SAPDistance >= 0.3D && SAPDistance <= 0.7D) return OurColors.BLACK; 
/* 47 */     return OurColors.BLUE;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\AbstractnessInstabilityPlot$2$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */