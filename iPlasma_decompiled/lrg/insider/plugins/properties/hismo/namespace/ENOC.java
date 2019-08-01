/*    */ package classes.lrg.insider.plugins.properties.hismo.namespace;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.properties.hismo.AbstractEvolutionOfNumberOfEntities;
/*    */ import lrg.insider.plugins.properties.hismo.namespace.ENOC;
/*    */ import lrg.memoria.hismo.core.AbstractVersion;
/*    */ import lrg.memoria.hismo.core.NamespaceVersion;
/*    */ 
/*    */ public class ENOC
/*    */   extends AbstractEvolutionOfNumberOfEntities
/*    */ {
/* 13 */   public ENOC() { super("ENOC", "Evolution of Number of Classes", "namespace history", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof lrg.memoria.hismo.core.NamespaceHistory)) {
/* 18 */       return null;
/*    */     }
/* 20 */     return computeTheEvolution(anEntity);
/*    */   }
/*    */ 
/*    */   
/* 24 */   protected int getProperty(AbstractVersion av) { return ((NamespaceVersion)av).getAbstractDataTypes().size(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\hismo\namespace\ENOC.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */