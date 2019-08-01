/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.strategies;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.InterestingMembersProvider;
/*    */ 
/*    */ public class FactoryOfInterestingStuff {
/*    */   public static InterestingMembersProvider create(String strategyClassName, AbstractEntityInterface theEntity) {
/*    */     try {
/*  9 */       Class aClass = Class.forName(strategyClassName);
/* 10 */       return (InterestingMembersProvider)aClass.getConstructor(new Class[] { Class.forName("lrg.common.abstractions.entities.AbstractEntityInterface") }).newInstance(new Object[] { theEntity });
/* 11 */     } catch (Exception e) {
/* 12 */       e.printStackTrace();
/*    */       
/* 14 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static InterestingMembersProvider create(String strategyClassName, AbstractEntityInterface theEntity, String strategyParameters) {
/*    */     try {
/* 20 */       Class aClass = Class.forName(strategyClassName);
/* 21 */       return (InterestingMembersProvider)aClass.getConstructor(new Class[] { null, (new Class[2][0] = Class.forName("lrg.common.abstractions.entities.AbstractEntityInterface")).forName("java.lang.String") }).newInstance(new Object[] { theEntity, strategyParameters });
/* 22 */     } catch (Exception e) {
/* 23 */       e.printStackTrace();
/*    */       
/* 25 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\strategies\FactoryOfInterestingStuff.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */