/*    */ package lrg.metrics;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.SortedSet;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CollectionResult
/*    */   extends Result
/*    */ {
/*    */   private ArrayList m_results;
/*    */   protected final String m_name;
/*    */   protected final String m_fileName;
/* 19 */   public static String sourcePath = "unknown";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CollectionResult() {
/* 26 */     this.m_results = new ArrayList();
/* 27 */     this.m_fileName = "";
/* 28 */     this.m_name = "inner_collection";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CollectionResult(String fileName, String name) {
/* 36 */     this.m_name = name;
/* 37 */     this.m_fileName = fileName;
/* 38 */     this.m_results = new ArrayList();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public void add(Result result) { this.m_results.add(result); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SortedSet getResultsSet() {
/* 51 */     TreeSet temp_set = new TreeSet();
/*    */     
/* 53 */     int size = this.m_results.size();
/* 54 */     for (int i = 0; i < size; i++) {
/* 55 */       Result act_result = (Result)this.m_results.get(i);
/* 56 */       temp_set.addAll(act_result.getResultsSet());
/*    */     } 
/* 58 */     return temp_set;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void saveResults() {
/*    */     try {
/* 67 */       File file = new File(this.m_fileName);
/* 68 */       File path = new File(file.getParent());
/* 69 */       path.mkdirs();
/*    */       
/* 71 */       FileWriter fw = new FileWriter(this.m_fileName);
/* 72 */       fw.write("Metric's full name: \"" + this.m_name + "\"\n");
/* 73 */       fw.write("Source path: \"" + sourcePath + "\"\n");
/* 74 */       Iterator set_iterator = getResultsSet().iterator();
/*    */ 
/*    */       
/* 77 */       while (set_iterator.hasNext()) {
/*    */         
/* 79 */         String str = (String)set_iterator.next();
/* 80 */         fw.write(str);
/*    */       } 
/* 82 */       fw.close();
/* 83 */     } catch (IOException e) {
/* 84 */       System.err.println("IOException occured while saving the results in " + this.m_fileName);
/* 85 */       System.err.println(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\CollectionResult.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */