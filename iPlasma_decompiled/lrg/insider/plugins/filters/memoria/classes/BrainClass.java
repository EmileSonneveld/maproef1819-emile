/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.insider.plugins.filters.Threshold;
/*    */ import lrg.insider.plugins.filters.memoria.classes.BrainClass;
/*    */ import lrg.insider.plugins.filters.memoria.classes.GodClass;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BrainClass
/*    */   extends FilteringRule
/*    */ {
/* 20 */   public BrainClass() { super(new Descriptor("Brain Class", "class")); }
/*    */ 
/*    */   
/*    */   private double loc(AbstractEntityInterface anEntity) {
/* 24 */     double result = 0.0D;
/* 25 */     Iterator it = anEntity.getProperty("LOC").getValueAsCollection().iterator();
/* 26 */     while (it.hasNext()) {
/* 27 */       Object tmp = it.next();
/* 28 */       if (tmp instanceof ResultEntity)
/* 29 */         result += ((Double)((ResultEntity)tmp).getValue()).doubleValue(); 
/*    */     } 
/* 31 */     return result;
/*    */   }
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 35 */     if ((new GodClass()).applyFilter(anEntity)) return false;
/*    */     
/* 37 */     double totalLOC = loc(anEntity);
/* 38 */     double NrOfBrainMethods = ((Double)anEntity.getProperty("NrBM").getValue()).doubleValue();
/*    */     
/* 40 */     FilteringRule veryHighWMC = 
/* 41 */       new FilteringRule("WMC", ">", "class", Threshold.WMC_VERYHIGH);
/* 42 */     if (NrOfBrainMethods > 1.0D && 
/* 43 */       totalLOC > Threshold.LOC_CLASS_VERYHIGH && 
/* 44 */       veryHighWMC.applyFilter(anEntity)) {
/* 45 */       return true;
/*    */     }
/* 47 */     FilteringRule excessiveHighWMC = 
/* 48 */       new FilteringRule("WMC", ">", "class", 2.0D * Threshold.WMC_VERYHIGH);
/* 49 */     if (NrOfBrainMethods == 1.0D && 
/* 50 */       totalLOC > 2.0D * Threshold.LOC_CLASS_VERYHIGH && 
/* 51 */       excessiveHighWMC.applyFilter(anEntity)) {
/* 52 */       return true;
/*    */     }
/* 54 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\BrainClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */