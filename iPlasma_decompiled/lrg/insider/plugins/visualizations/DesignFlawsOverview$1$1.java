/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.insider.plugins.filters.memoria.classes.DataClass;
/*     */ import lrg.insider.plugins.filters.memoria.classes.GodClass;
/*     */ import lrg.insider.plugins.filters.memoria.methods.FeatureEnvy;
/*     */ import lrg.insider.plugins.visualizations.DesignFlawsOverview;
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
/*     */ class null
/*     */   extends AbstractNumericalCommand
/*     */ {
/*     */   public double execute() {
/* 108 */     if (hasClassDesignFlaw((AbstractEntity)this.receiver)) return OurColors.RED; 
/* 109 */     if (hasMethodDesignFlaw((AbstractEntity)this.receiver)) return Color.YELLOW.getRGB(); 
/* 110 */     return OurColors.GREEN;
/*     */   }
/*     */   
/*     */   private boolean hasClassDesignFlaw(AbstractEntity aClass) {
/* 114 */     if ((new GodClass()).applyFilter(aClass) || (
/* 115 */       new DataClass()).applyFilter(aClass)) return true; 
/* 116 */     return false;
/*     */   }
/*     */   
/*     */   private boolean hasMethodDesignFlaw(AbstractEntity aClass) {
/* 120 */     if (aClass.contains("method group").applyFilter(new FeatureEnvy()).size() > 0) return true; 
/* 121 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\DesignFlawsOverview$1$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */