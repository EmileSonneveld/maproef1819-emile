/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.dude.duplication.Duplication;
/*    */ import lrg.dude.duplication.DuplicationList;
/*    */ import lrg.dude.duplication.MethodEntity;
/*    */ import lrg.insider.plugins.properties.memoria.classes.IDUPLINES;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ public class IDUPLINES extends PropertyComputer {
/* 15 */   public IDUPLINES() { super("IDUPLINES", "Duplication Level Inside Class", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 19 */     int result = 0;
/* 20 */     if (!(anEntity instanceof lrg.memoria.core.Class)) return null;
/*    */     
/* 22 */     DataAbstraction theClass = (DataAbstraction)anEntity;
/*    */     
/* 24 */     Iterator it = theClass.getMethodList().iterator();
/* 25 */     while (it.hasNext()) {
/* 26 */       Method aMethod = (Method)it.next();
/* 27 */       ResultEntity aResult = aMethod.getProperty("#DUPLICATION#");
/* 28 */       if (aResult == null || !(aResult.getValue() instanceof DuplicationList)) return new ResultEntity(0.0D); 
/* 29 */       DuplicationList aDuplicationList = (DuplicationList)aResult.getValue();
/* 30 */       if (aDuplicationList.size() > 0) {
/* 31 */         result += countDuplicationLength(aDuplicationList);
/*    */       }
/*    */     } 
/* 34 */     return new ResultEntity(result);
/*    */   }
/*    */   
/*    */   private int countDuplicationLength(DuplicationList aDuplicationList) {
/* 38 */     int result = 0;
/* 39 */     MethodEntity reference = (MethodEntity)aDuplicationList.get(0).getReferenceCode().getEntity();
/*    */     
/* 41 */     for (int i = 0; i < aDuplicationList.size(); i++) {
/* 42 */       Duplication aDuplication = aDuplicationList.get(i);
/* 43 */       MethodEntity duplicator = (MethodEntity)aDuplication.getDuplicateCode().getEntity();
/* 44 */       if (reference.getMethod().belongsTo("class").equals(duplicator.getMethod().belongsTo("class")))
/* 45 */         if (aDuplication.isSelfDuplication()) {
/* 46 */           result = (int)(result + aDuplication.copiedLength());
/*    */         } else {
/* 48 */           result = (int)(result + aDuplication.copiedLength() / 2L);
/*    */         }  
/* 50 */     }  return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\IDUPLINES.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */