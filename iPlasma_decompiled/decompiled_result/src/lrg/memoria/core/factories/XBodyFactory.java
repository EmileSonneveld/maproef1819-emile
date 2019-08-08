/*    */ package lrg.memoria.core.factories;
/*    */ 
/*    */ import lrg.memoria.core.Body;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.XFunctionBody;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class XBodyFactory
/*    */   extends BodyFactory
/*    */ {
/* 44 */   public Body produceBody(Method m) { return new XFunctionBody(m); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\factories\XBodyFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */