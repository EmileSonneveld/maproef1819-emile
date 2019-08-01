/*    */ package lrg.metrics.system;
/*    */ 
/*    */ import lrg.memoria.core.System;
/*    */ import lrg.metrics.Result;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class SystemMeasure
/*    */ {
/*    */   protected String m_name;
/*    */   protected String m_fullName;
/* 14 */   public static String m_path = ".";
/*    */   protected String m_kind;
/*    */   
/*    */   public abstract Result measure(System paramSystem);
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\system\SystemMeasure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */