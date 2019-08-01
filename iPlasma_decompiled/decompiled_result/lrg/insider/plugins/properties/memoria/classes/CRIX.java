/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class CRIX
/*    */   extends PropertyComputer {
/* 10 */   public CRIX() { super("CRIX", "Criticality Index", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface measuredClass) {
/* 14 */     double crix = 0.0D;
/* 15 */     GroupEntity methods = measuredClass.contains("method group");
/* 16 */     Boolean BrainClass = (Boolean)measuredClass.getProperty("Brain Class").getValue();
/*    */     
/* 18 */     Boolean GodClass = (Boolean)measuredClass.getProperty("God Class").getValue();
/* 19 */     Boolean DataClass = (Boolean)measuredClass.getProperty("Data Class").getValue();
/* 20 */     Boolean TraditionBreaker = (Boolean)measuredClass.getProperty("Tradition Breaker").getValue();
/* 21 */     Boolean RefusedParentBequest = (Boolean)measuredClass.getProperty("Refused Parent Bequest").getValue();
/*    */ 
/*    */     
/* 24 */     int FeatureEnvy = methods.applyFilter("Feature Envy").size();
/* 25 */     int IntensiveCoupling = methods.applyFilter("Intensive Coupling").size();
/* 26 */     int BrainMethod = methods.applyFilter("Brain Method").size();
/*    */     
/* 28 */     if (GodClass.booleanValue()) crix += 2.0D; 
/* 29 */     if (DataClass.booleanValue()) crix += 2.0D; 
/* 30 */     if (BrainClass.booleanValue()) crix += 2.0D; 
/* 31 */     if (TraditionBreaker.booleanValue()) crix++; 
/* 32 */     if (RefusedParentBequest.booleanValue()) crix++;
/*    */     
/* 34 */     crix += IntensiveCoupling * 0.5D;
/* 35 */     crix += FeatureEnvy * 0.5D;
/* 36 */     crix += BrainMethod * 0.5D;
/*    */     
/* 38 */     return new ResultEntity(crix);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\CRIX.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */