/*    */ package classes.lrg.insider.plugins.properties.hismo.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.properties.hismo.AbstractEvolutionOfNumberOfEntities;
/*    */ import lrg.insider.plugins.properties.hismo.classes.ENOM;
/*    */ import lrg.memoria.hismo.core.AbstractVersion;
/*    */ import lrg.memoria.hismo.core.ClassVersion;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ENOM
/*    */   extends AbstractEvolutionOfNumberOfEntities
/*    */ {
/* 15 */   public ENOM() { super("ENOM", "Evolution of Number of Methods", "class history", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 19 */     if (!(anEntity instanceof lrg.memoria.hismo.core.ClassHistory)) {
/* 20 */       return null;
/*    */     }
/* 22 */     return computeTheEvolution(anEntity);
/*    */   }
/*    */ 
/*    */   
/* 26 */   protected int getProperty(AbstractVersion av) { return ((ClassVersion)av).getMethodList().size(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\hismo\classes\ENOM.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */