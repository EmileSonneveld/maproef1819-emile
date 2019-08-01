/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import recoder.abstraction.Member;
/*    */ 
/*    */ public class RecoderToMemojConverter
/*    */ {
/*    */   public static int convertAccessMode(Member member) {
/*  8 */     if (member.isPublic())
/*  9 */       return 1; 
/* 10 */     if (member.isProtected())
/* 11 */       return 2; 
/* 12 */     if (member.isPrivate())
/* 13 */       return 4; 
/* 14 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\RecoderToMemojConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */