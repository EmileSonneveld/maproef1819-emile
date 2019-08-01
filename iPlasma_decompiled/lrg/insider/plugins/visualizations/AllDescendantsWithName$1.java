/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.plugins.visualizations.AllDescendantsWithName;
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
/*    */ class null
/*    */   extends AbstractNumericalCommand
/*    */ {
/*    */   public double execute() {
/* 44 */     if (((Boolean)((AbstractEntityInterface)this.receiver).getProperty("is interface").getValue()).booleanValue())
/* 45 */       return Color.LIGHT_GRAY.getRGB(); 
/* 46 */     if (((Boolean)((AbstractEntityInterface)this.receiver).getProperty("is abstract").getValue()).booleanValue()) {
/* 47 */       return Color.DARK_GRAY.getRGB();
/*    */     }
/* 49 */     return Color.WHITE.getRGB();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\AllDescendantsWithName$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */