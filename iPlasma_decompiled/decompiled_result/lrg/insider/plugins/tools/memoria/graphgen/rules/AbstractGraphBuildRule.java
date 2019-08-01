/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.rules;
/*    */ 
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.AbstractGraphBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.StrategyDefinition;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractGraphBuildRule
/*    */ {
/* 13 */   private FileWriter fileWriter = null;
/* 14 */   protected ArrayList<StrategyDefinition> strategies = new ArrayList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected abstract String fileNameSuffix();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public ArrayList<StrategyDefinition> getStrategies() { return this.strategies; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public FileWriter getWriter() { return this.fileWriter; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void createFile(String directory, String fileNameRoot) {
/*    */     try {
/* 43 */       this.fileWriter = new FileWriter(String.valueOf(directory) + "/" + fileNameRoot + fileNameSuffix() + ".dot");
/* 44 */     } catch (IOException e) {
/*    */       
/* 46 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeToFile(String text) {
/*    */     try {
/* 54 */       this.fileWriter.write(text);
/* 55 */     } catch (IOException e) {
/*    */       
/* 57 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void closeFile() {
/*    */     try {
/* 65 */       this.fileWriter.close();
/* 66 */     } catch (IOException e) {
/*    */       
/* 68 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\rules\AbstractGraphBuildRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */