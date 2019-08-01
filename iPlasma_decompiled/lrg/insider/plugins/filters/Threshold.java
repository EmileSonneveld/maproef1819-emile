/*    */ package classes.lrg.insider.plugins.filters;
/*    */ 
/*    */ import lrg.insider.plugins.filters.Threshold;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Threshold
/*    */ {
/*    */   public static final double NONE = 0.0D;
/*    */   public static final double SHALLOW = 1.0D;
/*    */   public static final double FEW = 2.0D;
/*    */   public static final double DEEP = 3.0D;
/*    */   public static final double MANY = 4.0D;
/*    */   public static final double SMemCap = 7.0D;
/*    */   public static final double ONE_QUARTER = 0.25D;
/*    */   public static final double ONE_THIRD = 0.333D;
/*    */   public static final double HALF = 0.5D;
/*    */   public static final double TWO_THIRDS = 0.666D;
/*    */   private static final double OUTLIER_FACTOR = 1.5D;
/* 25 */   public static double WMC_AVG = 13.229999999999999D;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public static double WMC_HIGH = 29.25D;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public static double WMC_VERYHIGH = WMC_HIGH * 1.5D;
/*    */ 
/*    */   
/* 38 */   public static double NOM_AVG = 7.0D;
/*    */ 
/*    */   
/* 41 */   public static double NOM_HIGH = 9.0D;
/*    */ 
/*    */   
/* 44 */   public static double LOC_VERYHIGH = 58.5D;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public static double LOC_CLASS_VERYHIGH = 175.5D;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public static double AMW_AVG = 1.89D;
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\Threshold.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */