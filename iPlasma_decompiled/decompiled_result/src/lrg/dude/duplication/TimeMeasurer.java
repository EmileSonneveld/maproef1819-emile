/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeMeasurer
/*    */ {
/*    */   public static String convertTimeToString(long miliseconds) {
/* 12 */     if (miliseconds < 1000L)
/* 13 */       return new String(miliseconds + "ms"); 
/* 14 */     int totalSeconds = (int)(miliseconds / 1000L);
/* 15 */     int hours = totalSeconds / 3600;
/* 16 */     int rezidualSeconds = totalSeconds % 3600;
/* 17 */     int minutes = rezidualSeconds / 60;
/* 18 */     int seconds = rezidualSeconds % 60;
/* 19 */     time = "";
/* 20 */     if (hours > 0) {
/* 21 */       if (hours < 10)
/* 22 */         time = String.valueOf(time) + "0"; 
/* 23 */       time = String.valueOf(time) + hours + "h";
/*    */     } 
/* 25 */     if (minutes > 0) {
/* 26 */       if (minutes < 10)
/* 27 */         time = String.valueOf(time) + "0"; 
/* 28 */       time = String.valueOf(time) + minutes + "m";
/*    */     } 
/* 30 */     if (seconds < 10)
/* 31 */       time = String.valueOf(time) + "0"; 
/* 32 */     return String.valueOf(time) + seconds + "s";
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\TimeMeasurer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */