/*    */ package classes.lrg.insider.plugins.tools;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.JOptionPane;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*    */ import lrg.insider.gui.ui.MainForm;
/*    */ import lrg.insider.plugins.tools.NorexInitTool;
/*    */ import lrg.norex.InsiderNorexImpl;
/*    */ 
/*    */ public class NorexInitTool
/*    */   extends AbstractEntityTool
/*    */ {
/*    */   private boolean done;
/*    */   
/*    */   public NorexInitTool() {
/* 17 */     super("NOREXInitialization", "Initialize NOREX", "system");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 24 */     this.done = false;
/*    */   } public void run(AbstractEntityInterface anEntity, Object theToolParameters) {
/* 26 */     ArrayList<String> params = (ArrayList)theToolParameters;
/* 27 */     if (this.done && "no".equalsIgnoreCase((String)params.get(2)))
/* 28 */       return;  InsiderNorexImpl.attachUI(MainForm.instance().getMenuBar());
/* 29 */     String result = InsiderNorexImpl.init((String)params.get(0), Integer.valueOf(Integer.parseInt((String)params.get(1))));
/* 30 */     if (result == null) {
/* 31 */       JOptionPane.showMessageDialog(MainForm.instance().getTopComponent(), 
/* 32 */           "NOREX was already initialized!", 
/* 33 */           "Warning", 2);
/*    */     } else {
/* 35 */       JOptionPane.showMessageDialog(MainForm.instance().getTopComponent(), result, 
/* 36 */           "NOREX initialization", 1);
/* 37 */     }  this.done = true;
/*    */   }
/*    */   public String getToolName() { return "Perform Norex Initialization" + (this.done ? " again" : "?"); }
/*    */   
/*    */   public void runOld(AbstractEntityInterface anEntity, Object theToolParameters) {
/* 42 */     ArrayList<String> params = (ArrayList)theToolParameters;
/* 43 */     if (this.done && "no".equalsIgnoreCase((String)params.get(2)))
/*    */       return;  try {
/* 45 */       Class norex = Class.forName("lrg.norex.InsiderNorexImpl");
/* 46 */       norex.getMethod("attachUI", new Class[] { javax.swing.JMenuBar.class
/* 47 */           }).invoke(null, new Object[] { MainForm.instance().getMenuBar() });
/* 48 */       String result = (String)norex.getMethod("init", new Class[] { String.class, Integer.class
/* 49 */           }).invoke(null, new Object[] { params.get(0), new Integer(Integer.parseInt((String)params.get(1))) });
/* 50 */       if (result == null) throw new NullPointerException(); 
/* 51 */       this.done = true;
/* 52 */       JOptionPane.showMessageDialog(
/* 53 */           MainForm.instance().getTopComponent(), result, 
/* 54 */           "NOREX initialization", 1);
/* 55 */     } catch (NullPointerException e) {
/* 56 */       JOptionPane.showMessageDialog(
/* 57 */           MainForm.instance().getTopComponent(), 
/* 58 */           "NOREX was already initialized!", 
/* 59 */           "Warning", 2);
/* 60 */     } catch (Exception e) {
/* 61 */       JOptionPane.showMessageDialog(
/* 62 */           MainForm.instance().getTopComponent(), 
/* 63 */           "NOREX implementation not found! Perhaps you are missing jar-files from classpath?", 
/* 64 */           "Failure", 2);
/*    */     } 
/*    */   }
/*    */   
/*    */   public ArrayList<String> getParameterList() {
/* 69 */     ArrayList<String> par = new ArrayList<String>();
/* 70 */     par.add("    Proxy Host:     ");
/* 71 */     par.add("    Proxy Port:     ");
/* 72 */     if (!this.done) return par; 
/* 73 */     par.add("Initialize again?");
/* 74 */     return par;
/*    */   }
/*    */   
/*    */   public ArrayList<String> getParameterExplanations() {
/* 78 */     ArrayList<String> par = new ArrayList<String>();
/* 79 */     par.add("Machine hosting the NOREX proxy server");
/* 80 */     par.add("Port number for the NOREX proxy server");
/* 81 */     if (!this.done) return par; 
/* 82 */     par.add("Write YES if you are sure you want to do this");
/* 83 */     return par;
/*    */   }
/*    */   
/*    */   public ArrayList<String> getParameterInitialValue() {
/* 87 */     ArrayList<String> par = new ArrayList<String>();
/* 88 */     par.add("brooks.cs.upt.ro");
/* 89 */     par.add("5673");
/* 90 */     if (!this.done) return par; 
/* 91 */     par.add("no");
/* 92 */     return par;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\NorexInitTool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */