/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.filters.Threshold;
/*    */ import lrg.insider.plugins.properties.memoria.classes.GREEDY;
/*    */ 
/*    */ public class GREEDY
/*    */   extends PropertyComputer {
/* 13 */   public GREEDY() { super("GREEDY", "GREEDY degree of a class", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   private double loc(AbstractEntityInterface anEntity) {
/* 18 */     double result = 0.0D;
/* 19 */     Iterator it = anEntity.getProperty("LOC").getValueAsCollection().iterator();
/* 20 */     while (it.hasNext()) {
/* 21 */       Object tmp = it.next();
/* 22 */       if (tmp instanceof ResultEntity)
/* 23 */         result += ((Double)((ResultEntity)tmp).getValue()).doubleValue(); 
/*    */     } 
/* 25 */     return result;
/*    */   }
/*    */ 
/*    */   
/* 29 */   private int dataClassesAmongDataProvider(AbstractEntityInterface anEntity) { return anEntity.getGroup("foreign data providers").applyFilter("Data Class").size(); }
/*    */ 
/*    */   
/*    */   public boolean featureEnvy(AbstractEntityInterface anEntity) {
/* 33 */     FilteringRule featureEnvy = new FilteringRule("NrFE", ">", "class", 0.0D);
/* 34 */     FilteringRule highATFD = new FilteringRule("ATFD", ">", "class", 4.0D);
/*    */     
/* 36 */     return !(!featureEnvy.applyFilter(anEntity) && !highATFD.applyFilter(anEntity));
/*    */   }
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 40 */     FilteringRule veryHighWMC = new FilteringRule("WMC", ">", "class", Threshold.WMC_VERYHIGH);
/* 41 */     boolean largeAndComplex = (loc(anEntity) > Threshold.LOC_CLASS_VERYHIGH && 
/* 42 */       veryHighWMC.applyFilter(anEntity));
/*    */     
/* 44 */     FilteringRule intensiveCoupling = new FilteringRule("NrIC", ">", "class", 0.0D);
/* 45 */     FilteringRule extensiveCoupling = new FilteringRule("NrEC", ">", "class", 0.0D);
/* 46 */     int counter = 0;
/*    */     
/* 48 */     if (!featureEnvy(anEntity)) return new ResultEntity(0.0D);
/*    */     
/* 50 */     counter += 1000 * dataClassesAmongDataProvider(anEntity);
/*    */     
/* 52 */     if (largeAndComplex) counter += 100; 
/* 53 */     if (intensiveCoupling.applyFilter(anEntity)) counter += 10; 
/* 54 */     if (extensiveCoupling.applyFilter(anEntity)) counter++;
/*    */ 
/*    */     
/* 57 */     return new ResultEntity(counter);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\GREEDY.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */