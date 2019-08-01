/*    */ package lrg.dude.gui;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ButtonAction
/*    */   extends AbstractAction
/*    */ {
/*    */   private GUI parent;
/*    */   public static final String PATH = "path";
/*    */   public static final String SAVE = "save";
/*    */   public static final String COMPUTE = "compute";
/*    */   public static final String STATISTICS = "statistics";
/*    */   public static final String HELP = "help";
/*    */   public static final String ABOUT = "about";
/*    */   
/* 24 */   public ButtonAction(GUI parent) { this.parent = parent; }
/*    */ 
/*    */   
/*    */   public void actionPerformed(ActionEvent ae) {
/* 28 */     String command = ae.getActionCommand();
/* 29 */     if (command == "path") {
/* 30 */       this.parent.setPathAction();
/*    */     }
/* 32 */     else if (command == "compute") {
/* 33 */       this.parent.computeAction();
/*    */     }
/* 35 */     else if (command == "statistics") {
/* 36 */       this.parent.showStatisticsAction();
/*    */     }
/* 38 */     else if (command == "save") {
/* 39 */       this.parent.saveAction();
/*    */     }
/* 41 */     else if (command == "help") {
/* 42 */       this.parent.helpAction();
/*    */     }
/* 44 */     else if (command == "about") {
/* 45 */       this.parent.aboutAction();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\gui\ButtonAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */