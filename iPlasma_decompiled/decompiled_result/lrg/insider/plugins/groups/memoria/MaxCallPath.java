/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.groups.memoria.MaxCallPath;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaxCallPath
/*    */   extends GroupBuilder
/*    */ {
/* 22 */   public MaxCallPath() { super("max call path", "", "method"); }
/*    */ 
/*    */ 
/*    */   
/* 26 */   private GroupEntity getDistinctCalledModelMethods(AbstractEntityInterface aMethod) { return aMethod.getGroup("operations called").distinct().applyFilter("model function"); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private ArrayList buildCallPath(AbstractEntityInterface currentMethod, ArrayList alreadyVisited) {
/* 43 */     GroupEntity calledMethods = getDistinctCalledModelMethods(currentMethod);
/* 44 */     calledMethods = calledMethods.exclude(new GroupEntity("name", alreadyVisited));
/*    */ 
/*    */ 
/*    */     
/* 48 */     ArrayList maxList = new ArrayList();
/*    */     
/* 50 */     if (calledMethods.size() == 0) {
/* 51 */       ArrayList thePath = new ArrayList();
/* 52 */       thePath.add(currentMethod);
/* 53 */       return thePath;
/*    */     } 
/*    */     
/* 56 */     Iterator it = calledMethods.getElements().iterator();
/*    */     
/* 58 */     while (it.hasNext()) {
/* 59 */       AbstractEntityInterface crt = (AbstractEntityInterface)it.next();
/* 60 */       alreadyVisited.add(currentMethod);
/* 61 */       ArrayList crtList = buildCallPath(crt, alreadyVisited);
/* 62 */       if (crtList.size() > maxList.size()) maxList = crtList;
/*    */     
/*    */     } 
/* 65 */     alreadyVisited.remove(currentMethod);
/* 66 */     maxList.add(0, currentMethod);
/* 67 */     return maxList;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aMethod) {
/* 72 */     ArrayList visited = new ArrayList();
/* 73 */     visited.add(aMethod);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 81 */     return buildCallPath(aMethod, visited);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\MaxCallPath.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */