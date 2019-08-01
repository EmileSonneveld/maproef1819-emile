/*    */ package classes.lrg.insider.plugins.tools;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*    */ import lrg.insider.plugins.details.DesignFlawsClassDetail;
/*    */ import lrg.insider.plugins.details.DesignFlawsMethodDetail;
/*    */ import lrg.insider.plugins.details.OverviewPyramid;
/*    */ import lrg.insider.plugins.tools.DesignFlawsReport;
/*    */ 
/*    */ public class DesignFlawsReport extends AbstractEntityTool {
/* 16 */   public DesignFlawsReport() { super("Complex Design Flaw Report", "Building Report", "system"); }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public String getToolName() { return "OCEReport"; }
/*    */ 
/*    */   
/*    */   private void writeFile(String foldername, AbstractEntityInterface anEntity) {
/* 24 */     if (anEntity.getEntityType().getName().compareTo("system") == 0) {
/*    */       try {
/* 26 */         String filename = String.valueOf(foldername) + "/__index.html";
/* 27 */         PrintStream out_stream = new PrintStream(new FileOutputStream(filename));
/* 28 */         out_stream.print(buildReport(anEntity));
/* 29 */         out_stream.close();
/* 30 */       } catch (Exception exception) {}
/*    */     }
/*    */     
/*    */     try {
/* 34 */       String filename = String.valueOf(foldername) + "/" + anEntity.getProperty("Address").getValue();
/* 35 */       PrintStream out_stream = new PrintStream(new FileOutputStream(filename));
/* 36 */       out_stream.print(buildReport(anEntity));
/* 37 */       out_stream.close();
/* 38 */     } catch (Exception exception) {}
/*    */   }
/*    */   public void run(AbstractEntityInterface aSystem, Object theToolParameters) {
/* 41 */     ArrayList<String> params = (ArrayList)theToolParameters;
/* 42 */     String foldername = "../../";
/* 43 */     if (((String)params.get(0)).equals("")) { foldername = String.valueOf(foldername) + "DesignFlaw-Report"; }
/* 44 */     else { foldername = String.valueOf(foldername) + (String)params.get(0); }
/* 45 */      (new File(foldername)).mkdir();
/*    */ 
/*    */     
/* 48 */     writeFile(foldername, aSystem);
/*    */ 
/*    */     
/* 51 */     for (Iterator iterator = aSystem.contains("class group").getElements().iterator(); iterator.hasNext();) {
/* 52 */       writeFile(foldername, (AbstractEntityInterface)iterator.next());
/*    */     }
/* 54 */     for (Iterator iterator = aSystem.contains("method group").getElements().iterator(); iterator.hasNext();)
/* 55 */       writeFile(foldername, (AbstractEntityInterface)iterator.next()); 
/*    */   }
/*    */   
/*    */   private String buildReport(AbstractEntityInterface anEntity) {
/* 59 */     if (anEntity.getEntityType().getName().compareTo("system") == 0)
/* 60 */       return (String)(new OverviewPyramid()).compute(anEntity).getValue(); 
/* 61 */     if (anEntity.getEntityType().getName().compareTo("class") == 0)
/* 62 */       return (String)(new DesignFlawsClassDetail()).compute(anEntity).getValue(); 
/* 63 */     if (anEntity.getEntityType().getName().compareTo("method") == 0)
/* 64 */       return (String)(new DesignFlawsMethodDetail()).compute(anEntity).getValue(); 
/* 65 */     return "";
/*    */   }
/*    */   
/*    */   public ArrayList<String> getParameterList() {
/* 69 */     ArrayList<String> parList = new ArrayList<String>();
/* 70 */     parList.add("Folder name (relative to working directory): ");
/* 71 */     return parList;
/*    */   }
/*    */   
/*    */   public ArrayList<String> getParameterExplanations() {
/* 75 */     ArrayList<String> parList = new ArrayList<String>();
/* 76 */     parList.add("Name of the folder to stort HTML files");
/* 77 */     return parList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\DesignFlawsReport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */