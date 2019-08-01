/*    */ package classes.lrg.insider.plugins.tools;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*    */ import lrg.memoria.core.System;
/*    */ 
/*    */ public class SaveCache
/*    */   extends AbstractEntityTool
/*    */ {
/* 12 */   public SaveCache() { super("SaveCache", "Saves to Cache", "system"); }
/*    */ 
/*    */   
/*    */   public void run(AbstractEntityInterface anEntity, Object theToolParameters) {
/* 16 */     ArrayList<String> params = (ArrayList)theToolParameters;
/* 17 */     File aFile = new File((String)params.get(0));
/*    */     
/* 19 */     System.serializeToFile(aFile, (System)anEntity);
/*    */   }
/*    */ 
/*    */   
/* 23 */   public String getToolName() { return "SaveCache"; }
/*    */ 
/*    */   
/*    */   public ArrayList<String> getParameterList() {
/* 27 */     ArrayList<String> parList = new ArrayList<String>();
/* 28 */     parList.add("Cache File Name (to Save)");
/* 29 */     return parList;
/*    */   }
/*    */   
/*    */   public ArrayList<String> getParameterExplanations() {
/* 33 */     ArrayList<String> parList = new ArrayList<String>();
/* 34 */     parList.add("Name of the cache file where the model will be saved");
/* 35 */     return parList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\SaveCache.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */