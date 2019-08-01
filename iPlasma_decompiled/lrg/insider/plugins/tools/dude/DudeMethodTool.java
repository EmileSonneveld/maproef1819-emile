/*    */ package classes.lrg.insider.plugins.tools.dude;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.dude.duplication.MethodEntity;
/*    */ import lrg.dude.duplication.Observer;
/*    */ import lrg.dude.duplication.Parameters;
/*    */ import lrg.dude.duplication.Processor;
/*    */ import lrg.dude.duplication.Subject;
/*    */ import lrg.insider.gui.ui.utils.ProgressBar;
/*    */ import lrg.insider.plugins.tools.dude.DudeMethodTool;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ public class DudeMethodTool extends AbstractEntityTool implements Observer {
/* 16 */   public DudeMethodTool() { super("Dude Method", "Duplication detector", "method"); }
/*    */   private ProgressBar progress;
/*    */   
/*    */   public void run(AbstractEntityInterface theMeth, Object o) {
/* 20 */     if (!(theMeth instanceof Method))
/*    */       return; 
/* 22 */     Method currentMethod = (Method)theMeth;
/* 23 */     ArrayList<String> params = (ArrayList)o;
/*    */     
/* 25 */     int par1 = Integer.parseInt((String)params.get(0));
/* 26 */     int par2 = Integer.parseInt((String)params.get(1));
/* 27 */     int par3 = Integer.parseInt((String)params.get(2));
/* 28 */     Parameters dudeParameters = new Parameters(par1, par2, par3, false);
/*    */     
/* 30 */     runDude(currentMethod, dudeParameters, true);
/*    */   }
/*    */ 
/*    */   
/* 34 */   public void runDude(Method currentMethod) { runDude(currentMethod, new Parameters(10, 2, 3, false), false); }
/*    */ 
/*    */ 
/*    */   
/*    */   private void runDude(Method theMethod, Parameters dudeParameters, boolean displayProgressBar) {
/* 39 */     Iterator it = null;
/*    */     
/* 41 */     GroupEntity methodGroup = theMethod.belongsTo("system")
/* 42 */       .getGroup("method group").applyFilter("model function").distinct();
/*    */     
/* 44 */     it = methodGroup.iterator();
/* 45 */     MethodEntity[] arrayOfMethodEntities = new MethodEntity[methodGroup.size()];
/* 46 */     int i = 0;
/*    */     
/* 48 */     while (it.hasNext()) {
/* 49 */       Method crtMeth = (Method)it.next();
/* 50 */       arrayOfMethodEntities[i++] = new MethodEntity(crtMeth);
/*    */     } 
/* 52 */     System.out.println(i);
/*    */     
/* 54 */     if (displayProgressBar) { this.progress = new ProgressBar("Computing the duplications ..."); }
/* 55 */     else { this.progress = null; }
/* 56 */      Processor aDudeProcessor = new Processor(arrayOfMethodEntities, new MethodEntity(theMethod), this.progress);
/* 57 */     aDudeProcessor.setParams(dudeParameters);
/* 58 */     aDudeProcessor.attach(this);
/* 59 */     if (displayProgressBar) { aDudeProcessor.start(); }
/* 60 */     else { aDudeProcessor.run(); }
/*    */   
/*    */   }
/*    */   public void getDuplication(Subject source) {
/* 64 */     ((Processor)source).attachResultsToReferenceMethod();
/* 65 */     if (this.progress != null) this.progress.close();
/*    */   
/*    */   }
/*    */   
/* 69 */   public String getToolName() { return "Dude"; }
/*    */ 
/*    */   
/*    */   public ArrayList<String> getParameterList() {
/* 73 */     ArrayList<String> parList = new ArrayList<String>();
/* 74 */     parList.add("Min length ");
/* 75 */     parList.add("Max line bias ");
/* 76 */     parList.add("Min exact chunk ");
/* 77 */     return parList;
/*    */   }
/*    */   
/*    */   public ArrayList<String> getParameterExplanations() {
/* 81 */     ArrayList<String> parList = new ArrayList<String>();
/* 82 */     parList.add("Minimum accepted length for duplication chains (LOC)");
/* 83 */     parList.add("The maximum size of the line bias between two exact chunks");
/* 84 */     parList.add("Minimum accepted size of the exact chunks within a chain");
/* 85 */     return parList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\dude\DudeMethodTool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */