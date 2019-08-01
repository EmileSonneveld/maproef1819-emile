/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.filters.memoria.classes.RefusedParentBequestInterface;
/*    */ import lrg.insider.plugins.filters.memoria.classes.TraditionBreaker;
/*    */ import lrg.insider.plugins.filters.memoria.methods.ABUSEINH;
/*    */ 
/*    */ public class ABUSEINH
/*    */   extends PropertyComputer {
/* 12 */   public ABUSEINH() { super("ABUSEINH", "Abusive Inheritance degree", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 17 */     RefusedParentBequestInterface refusedParentBequestInterface = new RefusedParentBequestInterface();
/* 18 */     TraditionBreaker traditionBreaker1 = new TraditionBreaker();
/*    */     
/* 20 */     int counter = 0;
/*    */ 
/*    */     
/* 23 */     counter += 100 * shortcircuitedAbstraction(anEntity);
/* 24 */     if (refusedParentBequestInterface.applyFilter(anEntity)) counter += 10; 
/* 25 */     if (traditionBreaker1.applyFilter(anEntity)) counter++; 
/* 26 */     return new ResultEntity(counter);
/*    */   }
/*    */ 
/*    */   
/* 30 */   private int shortcircuitedAbstraction(AbstractEntityInterface anEntity) { return anEntity.contains("method group").applyFilter("Shortcircuiting Method").size(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\ABUSEINH.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */