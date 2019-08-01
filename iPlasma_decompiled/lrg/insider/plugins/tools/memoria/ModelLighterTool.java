/*    */ package classes.lrg.insider.plugins.tools.memoria;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*    */ import lrg.insider.gui.ui.utils.ToolsStarter;
/*    */ import lrg.memoria.core.System;
/*    */ import lrg.memoria.utils.ModelLighter;
/*    */ 
/*    */ class ModelLighterTool
/*    */   extends AbstractEntityTool {
/* 13 */   public ModelLighterTool() { super("Model Lighter", "Lightens the model", "system"); }
/*    */ 
/*    */   
/*    */   public void run(AbstractEntityInterface abstractEntityInterface, Object o) {
/* 17 */     if (!(abstractEntityInterface instanceof System))
/*    */       return; 
/* 19 */     System aSystem = (System)abstractEntityInterface;
/*    */     
/* 21 */     ArrayList<String> params = (ArrayList)o;
/* 22 */     (new ModelLighter(aSystem)).lightenModel(Integer.parseInt((String)params.get(0)));
/* 23 */     System.serializeToFile(new File((String)params.get(1)), aSystem);
/*    */   }
/*    */ 
/*    */   
/* 27 */   public String getToolName() { return "Model Lighter"; }
/*    */ 
/*    */   
/*    */   public ArrayList<String> getParameterList() {
/* 31 */     ArrayList<String> parList = new ArrayList<String>();
/* 32 */     parList.add("Level ");
/* 33 */     parList.add("Output File ");
/* 34 */     ToolsStarter.kindOfButtons = new ArrayList();
/* 35 */     ToolsStarter.kindOfButtons.add(new Integer(0));
/* 36 */     ToolsStarter.kindOfButtons.add(new Integer(1));
/* 37 */     return parList;
/*    */   }
/*    */   
/*    */   public ArrayList<String> getParameterExplanations() {
/* 41 */     ArrayList<String> parList = new ArrayList<String>();
/* 42 */     parList.add("1 = dispose_up_to_types, 2 = dispose_up_to_methods");
/* 43 */     parList.add("The file where the light model will be saved");
/* 44 */     return parList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\ModelLighterTool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */