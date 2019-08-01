/*     */ package classes.lrg.insider.gui.ui;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JOptionPane;
/*     */ import lrg.common.metamodel.MetaModel;
/*     */ import lrg.insider.gui.ui.MainForm;
/*     */ import lrg.insider.gui.ui.browser.BrowserUI;
/*     */ import lrg.insider.gui.ui.stories.StoryTreeUI;
/*     */ import lrg.insider.gui.ui.utils.ProgressBar;
/*     */ import lrg.insider.metamodel.MemoriaCacheModelBuilder;
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
/* 175 */     progress = new ProgressBar("Loading the model ...");
/*     */     try {
/* 177 */       Thread.currentThread().setPriority(10);
/* 178 */       if (e.getSource() == MainForm.this.openCachedModel) {
/* 179 */         MetaModel.createFrom(
/* 180 */             new MemoriaCacheModelBuilder(fc.getSelectedFile().getAbsolutePath(), progress), 
/* 181 */             fc.getSelectedFile().getAbsolutePath());
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 190 */     catch (ParserException e1) {
/* 191 */       e1.printStackTrace();
/* 192 */       JOptionPane.showMessageDialog(MainForm.access$0(MainForm.this), e1.toString(), "EXCEPTION: ParserException", 2);
/*     */       return;
/* 194 */     } catch (Exception e2) {
/* 195 */       e2.printStackTrace();
/* 196 */       JOptionPane.showMessageDialog(MainForm.access$0(MainForm.this), "The model could not be loaded !", "ERROR", 2);
/*     */       return;
/*     */     } finally {
/* 199 */       progress.close();
/*     */     } 
/*     */     
/* 202 */     BrowserUI.instance().newMetaModelLoaded();
/* 203 */     StoryTreeUI.instance().setMetaModel();
/* 204 */     MainForm.this.setStatusBarText("Model succesfully loaded from: " + fc.getSelectedFile().getAbsolutePath());
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\MainForm$3.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */