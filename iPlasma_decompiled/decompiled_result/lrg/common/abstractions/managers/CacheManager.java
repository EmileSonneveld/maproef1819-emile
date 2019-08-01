/*    */ package lrg.common.abstractions.managers;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectOutputStream;
/*    */ 
/*    */ public class CacheManager {
/*  6 */   private static String staticEntityTypes = ".cache\\staticEntityTypes.cache";
/*  7 */   private static String dynamicEntityTypes = ".cache\\dynamicEntityTypes.cache";
/*  8 */   private static ObjectOutputStream serout = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 13 */   public static ObjectInputStream readStaticETStream() throws IOException { return new ObjectInputStream(new FileInputStream(staticEntityTypes)); }
/*    */ 
/*    */ 
/*    */   
/* 17 */   public static ObjectInputStream readDynamicETStream() throws IOException { return new ObjectInputStream(new FileInputStream(dynamicEntityTypes)); }
/*    */ 
/*    */ 
/*    */   
/* 21 */   public static ObjectOutputStream writeStaticETStream() throws IOException { return new ObjectOutputStream(new FileOutputStream(staticEntityTypes)); }
/*    */ 
/*    */   
/*    */   public static void writeDynamicETStream(Object anObject) {
/*    */     try {
/* 26 */       serout = new ObjectOutputStream(new FileOutputStream(dynamicEntityTypes, true));
/* 27 */       serout.writeObject(anObject);
/* 28 */       serout.close();
/* 29 */     } catch (IOException e) {
/* 30 */       System.err.println("ERROR: Unable to write to cache !");
/* 31 */       e.printStackTrace();
/*    */     } 
/*    */   }
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
/* 49 */   public static File getStaticETCache() { return new File(staticEntityTypes); }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public static File getDynamicETCache() { return new File(dynamicEntityTypes); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\managers\CacheManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */