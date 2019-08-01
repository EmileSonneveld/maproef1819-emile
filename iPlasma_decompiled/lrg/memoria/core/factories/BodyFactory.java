/*    */ package lrg.memoria.core.factories;
/*    */ 
/*    */ import lrg.memoria.core.Body;
/*    */ import lrg.memoria.core.FunctionBody;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ 
/*    */ public class BodyFactory
/*    */ {
/* 10 */   private static BodyFactory instance = null;
/*    */ 
/*    */   
/* 13 */   public static void cleanUp() { instance = null; }
/*    */ 
/*    */ 
/*    */   
/*    */   public static BodyFactory getInstance() {
/* 18 */     if (instance == null) {
/* 19 */       if (System.getProperty("memoria.extended.model") != null && 
/* 20 */         System.getProperty("memoria.extended.model").equals("yes")) {
/* 21 */         instance = new XBodyFactory();
/*    */       } else {
/* 23 */         instance = new BodyFactory();
/*    */       } 
/*    */     }
/*    */     
/* 27 */     return instance;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public Body produceBody(Method m) { return new FunctionBody(m); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\factories\BodyFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */