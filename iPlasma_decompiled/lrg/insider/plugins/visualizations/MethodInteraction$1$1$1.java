/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.insider.plugins.visualizations.MethodInteraction;
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
/* 89 */     if (this.receiver.equals(containerClass)) return OurColors.VERY_LIGHT_GRAY; 
/* 90 */     if (containerClass.getGroup("all descendants").intersect((AbstractEntity)this.receiver).size() > 0)
/* 91 */       return OurColors.LIGHT_RED; 
/* 92 */     if (containerClass.getGroup("all ancestors").intersect((AbstractEntity)this.receiver).size() > 0)
/* 93 */       return OurColors.LIGHT_LILA; 
/* 94 */     return Color.WHITE.getRGB();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\MethodInteraction$1$1$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */