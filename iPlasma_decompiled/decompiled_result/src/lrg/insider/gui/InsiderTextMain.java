/*    */ package classes.lrg.insider.gui;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.JOptionPane;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*    */ import lrg.common.metamodel.MetaModel;
/*    */ import lrg.insider.gui.InsiderGUIMain;
/*    */ import lrg.insider.gui.ui.loader.ModelLoaderUI;
/*    */ import lrg.insider.gui.ui.stories.StoryTreeUI;
/*    */ import lrg.insider.gui.ui.utils.ProgressBar;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.insider.metamodel.MemoriaJavaModelBuilder;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InsiderTextMain
/*    */ {
/*    */   public static void main(String[] args) {
/* 21 */     if (args.length < 2) {
/* 22 */       System.out.println("Param Usage: [project source path] [report class name] (result source path)");
/*    */       
/*    */       return;
/*    */     } 
/* 26 */     String strSourcePath = args[0];
/* 27 */     String strReportName = "lrg.insider.plugins.tools." + args[1];
/* 28 */     String strReportPath = (args.length > 2) ? args[2] : "";
/*    */ 
/*    */     
/* 31 */     progress = new ProgressBar("Loading the model ...");
/*    */     try {
/* 33 */       MetaModel.createFrom(new MemoriaJavaModelBuilder(strSourcePath, 
/* 34 */             ModelLoaderUI.getCachePath(), 
/* 35 */             InsiderGUIMain.getAdditioanClassPath(), progress), strSourcePath);
/* 36 */     } catch (Exception e2) {
/* 37 */       e2.printStackTrace();
/* 38 */       JOptionPane.showMessageDialog(StoryTreeUI.instance()
/* 39 */           .getTopComponent(), "The model could not be loaded !", 
/* 40 */           "ERROR", 2);
/*    */       return;
/*    */     } finally {
/* 43 */       progress.close();
/*    */     } 
/*    */     
/* 46 */     ArrayList dummyList = new ArrayList();
/* 47 */     dummyList.add(MetaModel.instance()
/* 48 */         .findEntityByAddress(Address.buildForRoot()));
/* 49 */     AbstractEntityInterface selectedEntity = (new GroupEntity(
/* 50 */         Address.buildForRoot(), dummyList)).getElementAt(0);
/*    */ 
/*    */     
/* 53 */     ArrayList paramList = new ArrayList();
/* 54 */     paramList.add(strReportPath);
/*    */     
/* 56 */     AbstractEntityTool aEntityTool = null;
/*    */     try {
/* 58 */       Class classReport = Class.forName(strReportName);
/* 59 */       aEntityTool = (AbstractEntityTool)classReport.newInstance();
/* 60 */     } catch (ClassNotFoundException e) {
/* 61 */       JOptionPane.showMessageDialog(StoryTreeUI.instance()
/* 62 */           .getTopComponent(), "Class " + strReportName + " not found!", "EXCEPTION", 2);
/* 63 */       e.printStackTrace();
/* 64 */     } catch (InstantiationException instExc) {
/* 65 */       System.out.println("Class " + strReportName + " is a non-instantiable class !");
/* 66 */       instExc.printStackTrace();
/* 67 */     } catch (IllegalAccessException illegalExc) {
/* 68 */       System.out.println("Class " + strReportName + " or its nullary constructor is not accessible !");
/* 69 */       illegalExc.printStackTrace();
/*    */     } 
/*    */ 
/*    */     
/*    */     try {
/* 74 */       aEntityTool.run(selectedEntity, paramList);
/* 75 */     } catch (RuntimeException exc) {
/* 76 */       System.err.println(String.valueOf(aEntityTool.getToolName()) + 
/* 77 */           " could not be run !");
/* 78 */       exc.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gui\InsiderTextMain.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */