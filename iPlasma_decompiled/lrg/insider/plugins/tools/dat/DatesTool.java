/*    */ package classes.lrg.insider.plugins.tools.dat;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.AbstractPlugin;
/*    */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*    */ import lrg.common.metamodel.Loader;
/*    */ 
/*    */ 
/*    */ public class DatesTool
/*    */   extends AbstractEntityTool
/*    */ {
/* 14 */   public DatesTool() { super("DATES", "Design Analysis Tool for Enterprise Systems", "system"); }
/*    */ 
/*    */   
/*    */   public void run(AbstractEntityInterface abstractEntityInterface, Object o) {
/* 18 */     if (!(abstractEntityInterface instanceof lrg.memoria.core.System))
/*    */       return; 
/* 20 */     System.out.println("../dates/classes");
/* 21 */     Loader loader = new Loader("../dates/classes");
/* 22 */     Iterator it = loader.getNames().iterator();
/* 23 */     while (it.hasNext()) {
/*    */       
/* 25 */       AbstractPlugin someCommand = loader.buildFrom((String)it.next(), "tools");
/* 26 */       if (someCommand != null) {
/* 27 */         ((AbstractEntityTool)someCommand).run(abstractEntityInterface, o);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 33 */   public String getToolName() { return "DATES"; }
/*    */ 
/*    */   
/*    */   public ArrayList<String> getParameterList() {
/* 37 */     ArrayList<String> parList = new ArrayList<String>();
/* 38 */     parList.add("DataSource Library  ");
/* 39 */     parList.add("Presentation Library");
/* 40 */     parList.add("Database name");
/* 41 */     return parList;
/*    */   }
/*    */   
/*    */   public ArrayList<String> getParameterExplanations() {
/* 45 */     ArrayList<String> parList = new ArrayList<String>();
/* 46 */     parList.add("DataSource Library  ");
/* 47 */     parList.add("Presentation Library");
/* 48 */     parList.add("Database name");
/* 49 */     return parList;
/*    */   }
/*    */   
/*    */   public ArrayList<String> getParameterInitialValue() {
/* 53 */     ArrayList<String> parList = new ArrayList<String>();
/* 54 */     parList.add("sql");
/* 55 */     parList.add("swing");
/* 56 */     parList.add("");
/* 57 */     return parList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\dat\DatesTool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */