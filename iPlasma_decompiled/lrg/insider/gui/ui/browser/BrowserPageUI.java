/*     */ package classes.lrg.insider.gui.ui.browser;
/*     */ 
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.event.HyperlinkEvent;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.metamodel.MetaModel;
/*     */ import lrg.insider.gui.ui.browser.BrowserPageUI;
/*     */ import lrg.insider.gui.ui.browser.BrowserUI;
/*     */ import lrg.insider.gui.ui.browser.History;
/*     */ import lrg.insider.gui.ui.browser.HistoryDetail;
/*     */ 
/*     */ public class BrowserPageUI extends MouseAdapter implements HyperlinkListener {
/*     */   private String detailName;
/*     */   private AbstractEntityInterface entity;
/*     */   private History history;
/*     */   private JScrollPane scrollPane;
/*     */   
/*  20 */   public BrowserPageUI(BrowserUI myBrowserUI) { this.detailName = "";
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
/* 148 */     $$$setupUI$$$();
/*     */     this.myBrowserUI = myBrowserUI;
/*     */     this.history = new History(500);
/*     */     myBrowserUI.addBrowser(this);
/*     */     this.htmlOutput = new JEditorPane();
/*     */     this.htmlOutput.setEditable(false);
/*     */     this.htmlOutput.setContentType("text/html");
/*     */     this.htmlOutput.addHyperlinkListener(this);
/*     */     this.htmlOutput.setBackground((new Color(16777113)).brighter());
/*     */     this.htmlOutput.addMouseListener(this);
/* 158 */     this.scrollPane.setViewportView(this.htmlOutput); } private JEditorPane htmlOutput; private MouseEvent lastMouseEvent; private BrowserUI myBrowserUI; String currentEntityDescription; public String getDetailName() { return this.detailName; } private void $$$setupUI$$$() { JScrollPane _1 = new JScrollPane();
/* 159 */     this.scrollPane = _1; }
/*     */ 
/*     */   
/*     */   public BrowserPageUI(BrowserUI myBrowserUI, String address, String detailName) {
/*     */     this(myBrowserUI);
/*     */     goTo(address, detailName);
/*     */     this.detailName = detailName;
/*     */   }
/*     */   
/*     */   public void goTo(String address, String detailName) {
/*     */     this.detailName = detailName;
/*     */     this.entity = MetaModel.instance().findEntityByAddress(address);
/*     */     if (this.entity instanceof GroupEntity && this.entity.getName().compareTo("~root") != 0) {
/*     */       GroupEntity gp = (GroupEntity)this.entity;
/*     */       if (gp.size() > 0)
/*     */         StoryTreeUI.instance().addStoryUI(new StoryUI(new ViewUI(gp)), 3); 
/*     */     } else {
/*     */       this.history.addNewDetail(address, detailName);
/*     */       this.htmlOutput.setText(buildHTMLTextFor(this.entity, detailName));
/*     */       this.htmlOutput.setCaretPosition(0);
/*     */       this.myBrowserUI.update(this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void goBack() {
/*     */     HistoryDetail back = this.history.getBackDetail();
/*     */     this.detailName = back.getDetailName();
/*     */     this.entity = MetaModel.instance().findEntityByAddress(back.getAddress());
/*     */     this.htmlOutput.setText(buildHTMLTextFor(this.entity, back.getDetailName()));
/*     */     this.htmlOutput.setCaretPosition(0);
/*     */     this.myBrowserUI.update(this);
/*     */   }
/*     */   
/*     */   public void goForward() {
/*     */     HistoryDetail forward = this.history.getForwardDetail();
/*     */     this.detailName = forward.getDetailName();
/*     */     this.entity = MetaModel.instance().findEntityByAddress(forward.getAddress());
/*     */     this.htmlOutput.setText(buildHTMLTextFor(this.entity, forward.getDetailName()));
/*     */     this.htmlOutput.setCaretPosition(0);
/*     */     this.myBrowserUI.update(this);
/*     */   }
/*     */   
/*     */   public AbstractEntityInterface getCurrentEntity() { return this.entity; }
/*     */   
/*     */   public History history() { return this.history; }
/*     */   
/*     */   public Component getTopComponent() { return this.scrollPane; }
/*     */   
/*     */   private String buildHTMLTextFor(AbstractEntityInterface anEntity, String detailName) {
/*     */     String text;
/*     */     if (anEntity.getName().compareTo("~root") == 0)
/*     */       anEntity = ((GroupEntity)this.entity).getElementAt(0); 
/*     */     if (anEntity.getEntityType().findDetails(detailName) != null) {
/*     */       text = anEntity.getEntityType().findDetails(detailName).compute(anEntity).toString();
/*     */       text = text.replaceAll("\n", "<br>");
/*     */       text = text.replaceAll(" ", "&nbsp;");
/*     */       text = text.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
/*     */     } else {
/*     */       text = "No \"" + detailName + "\" defined for \"" + anEntity.getEntityType().getName() + "\".";
/*     */     } 
/*     */     return text;
/*     */   }
/*     */   
/*     */   public void hyperlinkUpdate(HyperlinkEvent e) {
/*     */     if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
/*     */       if (this.lastMouseEvent.getButton() != 1) {
/*     */         EntityMouseAction.instance().buildFor(MetaModel.instance().findEntityByAddress(e.getDescription()), this.lastMouseEvent);
/*     */       } else {
/*     */         goTo(e.getDescription(), "Detail");
/*     */       }  
/*     */     if (e.getEventType() == HyperlinkEvent.EventType.ENTERED) {
/*     */       this.currentEntityDescription = e.getDescription();
/*     */       MainForm.instance().setStatusBarText(this.currentEntityDescription);
/*     */     } 
/*     */     if (e.getEventType() == HyperlinkEvent.EventType.EXITED)
/*     */       MainForm.instance().setStatusBarText(" "); 
/*     */   }
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/*     */     this.lastMouseEvent = e;
/*     */     EntityMouseAction.instance().buildFor(MetaModel.instance().findEntityByAddress(this.currentEntityDescription), this.lastMouseEvent);
/*     */   }
/*     */   
/*     */   public String getCurrentDetailText() { return this.htmlOutput.getText(); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\browser\BrowserPageUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */