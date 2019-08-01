/*    */ package classes.lrg.insider.plugins.properties.hismo.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.properties.hismo.AbstractEvolutionOfNumberOfEntities;
/*    */ import lrg.insider.plugins.properties.hismo.classes.ENOA;
/*    */ import lrg.memoria.hismo.core.AbstractVersion;
/*    */ import lrg.memoria.hismo.core.ClassVersion;
/*    */ 
/*    */ 
/*    */ public class ENOA
/*    */   extends AbstractEvolutionOfNumberOfEntities
/*    */ {
/* 14 */   public ENOA() { super("ENOA", "Evolution of Number of Attributes", "class history", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 18 */     if (!(anEntity instanceof lrg.memoria.hismo.core.ClassHistory)) {
/* 19 */       return null;
/*    */     }
/* 21 */     return computeTheEvolution(anEntity);
/*    */   }
/*    */ 
/*    */   
/* 25 */   protected int getProperty(AbstractVersion av) { return ((ClassVersion)av).getAttributeList().size(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\hismo\classes\ENOA.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */