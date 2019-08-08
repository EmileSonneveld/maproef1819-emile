/*     */ package classes.lrg.insider.gui.ui;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.JOptionPane;
/*     */ import lrg.common.metamodel.MetaModel;
/*     */ import lrg.insider.gui.InsiderGUIMain;
/*     */ import lrg.insider.gui.ui.MainForm;
/*     */ import lrg.insider.gui.ui.browser.BrowserUI;
/*     */ import lrg.insider.gui.ui.loader.ModelLoaderUI;
/*     */ import lrg.insider.gui.ui.stories.StoryTreeUI;
/*     */ import lrg.insider.gui.ui.utils.ProgressBar;
/*     */ import lrg.insider.metamodel.MemoriaCPPModelBuilder;
/*     */ import lrg.insider.metamodel.MemoriaJavaModelBuilder;
/*     */ import recoder.ParserException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class null
/*     */   extends Thread
/*     */ {
/*     */   public void run() {
/* 110 */     progress = new ProgressBar("Loading the model ...");
/*     */     try {
/* 112 */       Thread.currentThread().setPriority(10);
/* 113 */       if (e.getSource() == MainForm.this.openCppSources) {
/* 114 */         MetaModel.createFrom(
/* 115 */             new MemoriaCPPModelBuilder(ModelLoaderUI.getSourcePath(), 
/* 116 */               ModelLoaderUI.getCachePath(), progress), 
/* 117 */             ModelLoaderUI.getSourcePath());
/*     */       }
/*     */       
/* 120 */       if (e.getSource() == MainForm.this.openJavaSources) {
/* 121 */         MetaModel.createFrom(
/* 122 */             new MemoriaJavaModelBuilder(ModelLoaderUI.getSourcePath(), 
/* 123 */               ModelLoaderUI.getCachePath(), InsiderGUIMain.getAdditioanClassPath(), progress), 
/* 124 */             ModelLoaderUI.getSourcePath());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 135 */     catch (ParserException e1) {
/* 136 */       e1.printStackTrace();
/* 137 */       JOptionPane.showMessageDialog(MainForm.access$0(MainForm.this), e1.toString(), "EXCEPTION: ParserException", 2);
/*     */       return;
/* 139 */     } catch (Exception e2) {
/* 140 */       e2.printStackTrace();
/* 141 */       JOptionPane.showMessageDialog(MainForm.access$0(MainForm.this), "The model could not be loaded !", "ERROR", 2);
/*     */       return;
/*     */     } finally {
/* 144 */       progress.close();
/*     */     } 
/*     */     
/* 147 */     BrowserUI.instance().newMetaModelLoaded();
/* 148 */     StoryTreeUI.instance().setMetaModel();
/* 149 */     MainForm.this.setStatusBarText("Model succesfully loaded from: " + ModelLoaderUI.getSourcePath());
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\MainForm$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */