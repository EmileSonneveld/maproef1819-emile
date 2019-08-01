/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.plugins.visualizations.AttributeUsage;
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
/*    */ class null
/*    */   extends AbstractNumericalCommand
/*    */ {
/*    */   public double execute() {
/* 76 */     AbstractEntityInterface aClass = (AbstractEntityInterface)this.receiver;
/* 77 */     if (aClass.equals(containerClass)) return OurColors.VERY_LIGHT_GRAY; 
/* 78 */     if (containerClass.getGroup("all descendants").intersect(aClass).size() > 0)
/* 79 */       return OurColors.LIGHT_RED; 
/* 80 */     if (containerClass.getGroup("all ancestors").intersect(aClass).size() > 0)
/* 81 */       return OurColors.LIGHT_LILA; 
/* 82 */     return Color.WHITE.getRGB();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\AttributeUsage$1$1$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */