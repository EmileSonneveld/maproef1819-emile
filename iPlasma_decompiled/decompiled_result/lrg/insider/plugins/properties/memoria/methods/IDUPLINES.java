/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.dude.duplication.Duplication;
/*    */ import lrg.dude.duplication.DuplicationList;
/*    */ import lrg.dude.duplication.MethodEntity;
/*    */ import lrg.insider.plugins.properties.memoria.methods.IDUPLINES;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IDUPLINES
/*    */   extends PropertyComputer
/*    */ {
/* 21 */   public IDUPLINES() { super("IDUPLINES", "Duplication Level Inside Class", "method", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aMethod) {
/* 25 */     int result = 0;
/* 26 */     if (!(aMethod instanceof lrg.memoria.core.Function)) return null;
/*    */     
/* 28 */     ResultEntity aResult = aMethod.getProperty("#DUPLICATION#");
/* 29 */     if (aResult == null) return new ResultEntity(0.0D);
/*    */ 
/*    */     
/* 32 */     if (aResult.getValue() instanceof DuplicationList) {
/* 33 */       DuplicationList aDuplicationList = (DuplicationList)aResult.getValue();
/* 34 */       if (aDuplicationList.size() > 0) {
/* 35 */         result += countDuplicationLength(aDuplicationList);
/*    */       }
/*    */     } 
/* 38 */     return new ResultEntity(result);
/*    */   }
/*    */ 
/*    */   
/*    */   private static boolean isFromSameClass(MethodEntity reference, MethodEntity duplicator) {
/* 43 */     String referenceAddress = (String)reference.getMethod().belongsTo("class").getProperty("Address").getValue();
/* 44 */     String duplicatorAddress = (String)duplicator.getMethod().belongsTo("class").getProperty("Address").getValue();
/*    */     
/* 46 */     return (referenceAddress.compareTo(duplicatorAddress) == 0);
/*    */   }
/*    */   
/*    */   public static ArrayList getMethodsWithIntraClassDuplication(DuplicationList aDuplicationList) {
/* 50 */     ArrayList resultsList = new ArrayList();
/* 51 */     if (aDuplicationList.size() == 0) return resultsList; 
/* 52 */     MethodEntity reference = (MethodEntity)aDuplicationList.get(0).getReferenceCode().getEntity();
/*    */ 
/*    */     
/* 55 */     for (int i = 0; i < aDuplicationList.size(); i++) {
/* 56 */       Duplication aDuplication = aDuplicationList.get(i);
/* 57 */       MethodEntity duplicator = (MethodEntity)aDuplication.getDuplicateCode().getEntity();
/*    */       
/* 59 */       if (isFromSameClass(reference, duplicator)) resultsList.add(aDuplication); 
/*    */     } 
/* 61 */     return resultsList;
/*    */   }
/*    */   
/*    */   private int countDuplicationLength(DuplicationList aDuplicationList) {
/* 65 */     int result = 0;
/* 66 */     MethodEntity reference = (MethodEntity)aDuplicationList.get(0).getReferenceCode().getEntity();
/*    */     
/* 68 */     for (int i = 0; i < aDuplicationList.size(); i++) {
/* 69 */       Duplication aDuplication = aDuplicationList.get(i);
/* 70 */       MethodEntity duplicator = (MethodEntity)aDuplication.getDuplicateCode().getEntity();
/*    */       
/* 72 */       if (isFromSameClass(reference, duplicator)) result = (int)(result + (aDuplication.isSelfDuplication() ? aDuplication.copiedLength() : (aDuplication.copiedLength() / 2L))); 
/*    */     } 
/* 74 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\IDUPLINES.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */