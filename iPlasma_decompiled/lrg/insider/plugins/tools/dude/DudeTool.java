/*    */ package classes.lrg.insider.plugins.tools.dude;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.dude.duplication.MethodEntity;
/*    */ import lrg.dude.duplication.Parameters;
/*    */ import lrg.dude.duplication.Processor;
/*    */ import lrg.dude.duplication.Subject;
/*    */ import lrg.insider.gui.ui.utils.ProgressBar;
/*    */ import lrg.insider.plugins.tools.dude.DudeTool;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.System;
/*    */ 
/*    */ public class DudeTool extends AbstractEntityTool implements Observer {
/* 16 */   public DudeTool() { super("Dude", "Duplication detector", "system"); }
/*    */   private ProgressBar progress;
/*    */   
/*    */   public void run(AbstractEntityInterface abstractEntityInterface, Object o) {
/* 20 */     if (!(abstractEntityInterface instanceof System))
/*    */       return; 
/* 22 */     System currentSystem = (System)abstractEntityInterface;
/* 23 */     ArrayList<String> params = (ArrayList)o;
/*    */     
/* 25 */     int par1 = Integer.parseInt((String)params.get(0));
/* 26 */     int par2 = Integer.parseInt((String)params.get(1));
/* 27 */     int par3 = Integer.parseInt((String)params.get(2));
/* 28 */     Parameters dudeParameters = new Parameters(par1, par2, par3, false);
/*    */     
/* 30 */     runDude(currentSystem, dudeParameters, true);
/*    */   }
/*    */ 
/*    */   
/* 34 */   public void runDude(System currentSystem) { runDude(currentSystem, new Parameters(10, 2, 3, false), false); }
/*    */ 
/*    */   
/*    */   private void runDude(System currentSystem, Parameters dudeParameters, boolean displayProgressBar) {
/* 38 */     Iterator it = null;
/* 39 */     GroupEntity methodGroup = currentSystem.getGroup("method group").applyFilter("model function").distinct();
/*    */     
/* 41 */     it = methodGroup.iterator();
/* 42 */     MethodEntity[] arrayOfMethodEntities = new MethodEntity[methodGroup.size()];
/* 43 */     int i = 0;
/*    */     
/* 45 */     while (it.hasNext()) {
/* 46 */       Method currentMethod = (Method)it.next();
/* 47 */       arrayOfMethodEntities[i++] = new MethodEntity(currentMethod);
/*    */     } 
/*    */     
/* 50 */     if (displayProgressBar) { this.progress = new ProgressBar("Computing the duplications ..."); }
/* 51 */     else { this.progress = null; }
/* 52 */      Processor aDudeProcessor = new Processor(arrayOfMethodEntities, this.progress);
/* 53 */     aDudeProcessor.setParams(dudeParameters);
/* 54 */     aDudeProcessor.attach(this);
/* 55 */     if (displayProgressBar) { aDudeProcessor.start(); }
/* 56 */     else { aDudeProcessor.run(); }
/*    */   
/*    */   }
/*    */   public void getDuplication(Subject source) {
/* 60 */     ((Processor)source).attachResultsToMethods();
/* 61 */     if (this.progress != null) this.progress.close(); 
/* 62 */     System.out.println("Duplications have entered the building");
/*    */   }
/*    */ 
/*    */   
/* 66 */   public String getToolName() { return "Dude"; }
/*    */ 
/*    */   
/*    */   public ArrayList<String> getParameterList() {
/* 70 */     ArrayList<String> parList = new ArrayList<String>();
/* 71 */     parList.add("Min length ");
/* 72 */     parList.add("Max line bias ");
/* 73 */     parList.add("Min exact chunk ");
/* 74 */     return parList;
/*    */   }
/*    */   
/*    */   public ArrayList<String> getParameterExplanations() {
/* 78 */     ArrayList<String> parList = new ArrayList<String>();
/* 79 */     parList.add("Minimum accepted length for duplication chains (LOC)");
/* 80 */     parList.add("The maximum size of the line bias between two exact chunks");
/* 81 */     parList.add("Minimum accepted size of the exact chunks within a chain");
/* 82 */     return parList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\dude\DudeTool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */