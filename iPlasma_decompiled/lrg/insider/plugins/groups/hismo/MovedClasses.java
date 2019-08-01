/*    */ package classes.lrg.insider.plugins.groups.hismo;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.hismo.core.AbstractHistory;
/*    */ import lrg.memoria.hismo.core.ClassHistoryGroup;
/*    */ import lrg.memoria.hismo.core.NamespaceHistory;
/*    */ import lrg.memoria.hismo.core.NamespaceHistoryGroup;
/*    */ import lrg.memoria.hismo.core.SystemHistory;
/*    */ 
/*    */ public class MovedClasses extends GroupBuilder {
/* 13 */   public MovedClasses() { super("moved classes", "", "system history"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof SystemHistory)) return new ArrayList();
/*    */     
/* 19 */     SystemHistory aSystemHistory = (SystemHistory)anEntity;
/*    */     
/* 21 */     String firstVersionName = aSystemHistory.getFirstVersion().versionName();
/* 22 */     String lastVersionName = aSystemHistory.getLastVersion().versionName();
/* 23 */     NamespaceHistoryGroup nsg = aSystemHistory.getNamespaceHistories();
/* 24 */     ArrayList<AbstractHistory> classHistories = new ArrayList<AbstractHistory>();
/* 25 */     for (AbstractHistory nh : nsg.getHistoriesCollection()) {
/* 26 */       if (!nh.isLibrary()) {
/* 27 */         ClassHistoryGroup clhg = ((NamespaceHistory)nh).getClassHistories();
/* 28 */         for (AbstractHistory ch : clhg.getHistoriesCollection()) {
/* 29 */           if (!ch.getFirstVersion().versionName().equals(firstVersionName) || 
/* 30 */             !ch.getLastVersion().versionName().equals(lastVersionName)) {
/* 31 */             classHistories.add(ch);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/* 36 */     ArrayList result = new ArrayList();
/* 37 */     for (AbstractHistory ah1 : classHistories) {
/* 38 */       String firstHistoryName = ah1.getName();
/* 39 */       String firstHistoryFirstVersionName = ah1.getFirstVersion().versionName();
/* 40 */       for (AbstractHistory ah2 : classHistories) {
/* 41 */         String lvName = ah2.getLastVersion().versionName();
/* 42 */         if (firstHistoryName.equals(ah2.getName()) && 
/* 43 */           lvName.equals(SystemHistory.getVersionNamePreviousTo(aSystemHistory, firstHistoryFirstVersionName))) {
/* 44 */           result.add(ah1);
/* 45 */           result.add(ah2);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 50 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\hismo\MovedClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */