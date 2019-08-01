/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsAbstract;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsOverriden;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsSpecialization;
/*    */ import lrg.insider.plugins.filters.memoria.variables.IsAttribute;
/*    */ import lrg.insider.plugins.visualizations.ColorNumericalCommand;
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
/*    */ class ColorNumericalCommand
/*    */   extends AbstractNumericalCommand
/*    */ {
/*    */   private AbstractEntityInterface entity;
/*    */   
/* 32 */   public ColorNumericalCommand(AbstractEntityInterface anEntity) { this.entity = anEntity; }
/*    */   
/*    */   public double execute() {
/* 35 */     AbstractEntityInterface receiverEntity = (AbstractEntityInterface)this.receiver;
/* 36 */     if ((new IsAttribute()).applyFilter(receiverEntity)) return Color.blue.getRGB(); 
/* 37 */     if ((new IsAbstract()).applyFilter(receiverEntity)) return Color.cyan.getRGB(); 
/* 38 */     if ((new IsSpecialization()).applyFilter(receiverEntity)) return Color.orange.getRGB(); 
/* 39 */     if ((new IsOverriden()).applyFilter(receiverEntity)) return (new Color(150, 100, 75)).getRGB(); 
/* 40 */     if ((new IsAccessor()).applyFilter(receiverEntity)) {
/* 41 */       if (receiverEntity.getName().contains("get")) return Color.red.getRGB(); 
/* 42 */       if (receiverEntity.getName().contains("set")) return Color.orange.getRGB(); 
/* 43 */     }  return Color.WHITE.getRGB();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\ColorNumericalCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */