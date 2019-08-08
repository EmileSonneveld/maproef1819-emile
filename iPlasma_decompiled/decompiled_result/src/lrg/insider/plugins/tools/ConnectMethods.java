/*    */ package classes.lrg.insider.plugins.tools;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*    */ import lrg.memoria.core.Call;
/*    */ import lrg.memoria.core.FunctionBody;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConnectMethods
/*    */   extends AbstractEntityTool
/*    */ {
/* 18 */   public ConnectMethods() { super("ConnectMethods", "Connects two methods", "method"); }
/*    */ 
/*    */   
/*    */   public void run(AbstractEntityInterface anEntity, Object theToolParameters) {
/* 22 */     ArrayList<String> params = (ArrayList)theToolParameters;
/* 23 */     Method firstMethod = (Method)anEntity;
/* 24 */     FunctionBody functionBody = firstMethod.getBody();
/*    */     
/* 26 */     if (functionBody == null) {
/* 27 */       System.out.println("Method has no body");
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 32 */     String methodName = (String)params.get(0);
/* 33 */     String className = (String)params.get(1);
/*    */     
/* 35 */     GroupEntity allClasses = firstMethod.belongsTo("system").getGroup("class group");
/*    */     
/* 37 */     FilteringRule aClassFilter = new FilteringRule("Name", "==", "class", className);
/* 38 */     FilteringRule aMethodFilter = new FilteringRule("Name", "==", "method", methodName);
/*    */     
/* 40 */     GroupEntity ourClasses = allClasses.applyFilter(aClassFilter);
/* 41 */     GroupEntity ourMethods = ourClasses.getGroup("method group").applyFilter(aMethodFilter);
/*    */     
/* 43 */     System.out.println("Cate metode: " + ourMethods.size());
/* 44 */     Method secondMethod = (Method)ourMethods.getElementAt(0);
/*    */     
/* 46 */     functionBody.getCodeStripe().addCall(new Call(secondMethod, functionBody.getCodeStripe()));
/*    */ 
/*    */     
/* 49 */     System.out.println("here");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   public String getToolName() { return "ConnectMethods"; }
/*    */ 
/*    */   
/*    */   public ArrayList<String> getParameterList() {
/* 59 */     ArrayList<String> parList = new ArrayList<String>();
/* 60 */     parList.add("Method to Connect to (only methodname)");
/* 61 */     parList.add("Class of Method (only classname)");
/* 62 */     return parList;
/*    */   }
/*    */   
/*    */   public ArrayList<String> getParameterExplanations() {
/* 66 */     ArrayList<String> parList = new ArrayList<String>();
/* 67 */     parList.add("Method to Connect to (only methodname)");
/* 68 */     parList.add("Class of Method (only classname)");
/* 69 */     return parList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\ConnectMethods.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */