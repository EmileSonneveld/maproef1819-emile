/*    */ package classes.lrg.insider.plugins.properties.hismo;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.properties.hismo.AbstractEvolutionOfNumberOfEntities;
/*    */ import lrg.memoria.hismo.core.AbstractHistory;
/*    */ import lrg.memoria.hismo.core.AbstractVersion;
/*    */ 
/*    */ 
/*    */ public abstract class AbstractEvolutionOfNumberOfEntities
/*    */   extends PropertyComputer
/*    */ {
/* 15 */   public AbstractEvolutionOfNumberOfEntities(String name, String description, String entity, String resultType) { super(name, description, entity, resultType); }
/*    */ 
/*    */   
/*    */   protected ResultEntity computeTheEvolution(AbstractEntityInterface anEntity) {
/* 19 */     AbstractHistory anAbstractHistory = (AbstractHistory)anEntity;
/* 20 */     boolean firstVersion = true;
/* 21 */     int propertyInPreviousVersion = 0, evolution = 0;
/* 22 */     for (Iterator it = anAbstractHistory.getVersionIterator(); it.hasNext(); ) {
/* 23 */       AbstractVersion cv = (AbstractVersion)it.next();
/* 24 */       if (firstVersion) {
/* 25 */         propertyInPreviousVersion = getProperty(cv);
/* 26 */         firstVersion = false;
/*    */         continue;
/*    */       } 
/* 29 */       int propertyInCurrentVersion = getProperty(cv);
/* 30 */       evolution += Math.abs(propertyInCurrentVersion - propertyInPreviousVersion);
/* 31 */       propertyInPreviousVersion = propertyInCurrentVersion;
/*    */     } 
/*    */     
/* 34 */     return new ResultEntity(evolution);
/*    */   }
/*    */   
/*    */   protected abstract int getProperty(AbstractVersion paramAbstractVersion);
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\hismo\AbstractEvolutionOfNumberOfEntities.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */