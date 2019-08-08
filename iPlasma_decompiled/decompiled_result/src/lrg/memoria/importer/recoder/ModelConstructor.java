/*    */ package lrg.memoria.importer.recoder;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedList;
/*    */ import java.util.Map;
/*    */ import recoder.convenience.ASTIterator;
/*    */ import recoder.convenience.ASTIteratorListener;
/*    */ import recoder.java.Comment;
/*    */ import recoder.java.NonTerminalProgramElement;
/*    */ import recoder.java.ProgramElement;
/*    */ import recoder.list.generic.ASTList;
/*    */ 
/*    */ public class ModelConstructor
/*    */   implements ASTIteratorListener
/*    */ {
/* 17 */   private static Map factories = new HashMap(25);
/* 18 */   private static Map allFactories = new HashMap(90);
/* 19 */   private LinkedList listenersList = new LinkedList();
/*    */   
/*    */   static void cleanUp() {
/* 22 */     if (factories != null) {
/* 23 */       it = factories.values().iterator();
/* 24 */       while (it.hasNext()) {
/* 25 */         ((IFactory)it.next()).cleanUp();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 31 */   public static void addFactory(String fName, IFactory f) { factories.put(fName, f); }
/*    */ 
/*    */   
/*    */   public Listener getListener(String name) {
/* 35 */     String listenerName = "lrg.memoria.importer.recoder." + name + "Listener";
/*    */     
/* 37 */     if (!allFactories.containsKey(listenerName)) {
/*    */       try {
/* 39 */         allFactories.put(listenerName, "");
/* 40 */         Class.forName(listenerName);
/* 41 */         IFactory fac = (IFactory)factories.get(listenerName);
/* 42 */         return fac.getListener();
/* 43 */       } catch (ClassNotFoundException e) {
/* 44 */         return null;
/*    */       } 
/*    */     }
/* 47 */     IFactory fac = (IFactory)factories.get(listenerName);
/* 48 */     if (fac != null) return fac.getListener(); 
/* 49 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public boolean enterChildNode(ASTIterator it, NonTerminalProgramElement thisNode, ProgramElement childNode) { return true; }
/*    */ 
/*    */ 
/*    */   
/* 60 */   public int enterChildren(ASTIterator it, NonTerminalProgramElement thisNode) { return 2; }
/*    */ 
/*    */   
/*    */   public void enteringNode(ASTIterator it, ProgramElement node) {
/* 64 */     String curClassName = node.getClass().getName();
/* 65 */     String name = curClassName.substring(curClassName.lastIndexOf(".") + 1);
/* 66 */     Listener curentListener = getListener(name);
/*    */     
/* 68 */     if (curentListener != null) {
/* 69 */       this.listenersList.addLast(curentListener);
/* 70 */       curentListener.enterModelComponent(node);
/*    */     } 
/*    */     
/* 73 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/* 74 */     ASTList<Comment> cml = node.getComments();
/* 75 */     if (cml != null)
/* 76 */       mer.addComments(cml.size()); 
/*    */   }
/*    */   
/*    */   public void leavingNode(ASTIterator it, ProgramElement node) {
/* 80 */     String curClassName = node.getClass().getName();
/* 81 */     String name = curClassName.substring(curClassName.lastIndexOf(".") + 1);
/* 82 */     Listener curentListener = getListener(name);
/* 83 */     if (curentListener != null) {
/* 84 */       curentListener = (Listener)this.listenersList.removeLast();
/* 85 */       curentListener.leaveModelComponent(node);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void returnedFromChildNode(ASTIterator it, NonTerminalProgramElement thisNode, ProgramElement childNode) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\ModelConstructor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */