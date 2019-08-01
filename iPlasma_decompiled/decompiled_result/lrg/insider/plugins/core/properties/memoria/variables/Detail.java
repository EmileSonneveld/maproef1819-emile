/*    */ package classes.lrg.insider.plugins.core.properties.memoria.variables;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*    */ import lrg.insider.plugins.core.properties.memoria.variables.Detail;
/*    */ import lrg.memoria.core.Attribute;
/*    */ import lrg.memoria.core.GlobalVariable;
/*    */ import lrg.memoria.core.Variable;
/*    */ 
/*    */ public class Detail extends AbstractDetail {
/* 12 */   public Detail() { super("Detail", "HTML Detail", new String[] { "global variable", "attribute", "local variable", "parameter" }, "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 16 */     if (!(anEntity instanceof Variable)) return null;
/*    */     
/* 18 */     Variable aVariable = (Variable)anEntity;
/*    */     
/* 20 */     String text = "<big>" + printKind(aVariable) + " ";
/* 21 */     if (aVariable instanceof Attribute) {
/* 22 */       Attribute aAttr = (Attribute)aVariable;
/* 23 */       text = String.valueOf(text) + "</big><b>" + getAccessModeHTML(aAttr.getAccessMode()) + "</b><big> ";
/*    */     } 
/*    */     
/* 26 */     String fullTypeName = aVariable.getType().getFullName();
/* 27 */     int openBracketIndex = fullTypeName.indexOf("<");
/* 28 */     int closeBracketIndex = fullTypeName.indexOf(">");
/* 29 */     if (openBracketIndex > 0 && closeBracketIndex > 0) {
/* 30 */       text = String.valueOf(text) + linkTo(fullTypeName.substring(0, openBracketIndex)) + "&lt;" + 
/* 31 */         linkTo(fullTypeName.substring(openBracketIndex + 1, closeBracketIndex - 1)) + "&gt;";
/*    */     } else {
/* 33 */       text = String.valueOf(text) + linkTo(fullTypeName);
/*    */     } 
/* 35 */     text = String.valueOf(text) + " " + linkTo(aVariable.getScope()) + " :: " + linkTo(aVariable) + "</big><br>";
/* 36 */     if (aVariable instanceof Attribute) {
/* 37 */       text = String.valueOf(text) + buildModifiers((Attribute)aVariable) + "<hr><br>";
/*    */     }
/* 39 */     return new ResultEntity(text);
/*    */   }
/*    */   
/*    */   protected String printKind(Variable aVariable) {
/* 43 */     if (aVariable instanceof GlobalVariable)
/* 44 */       return "Global Variable"; 
/* 45 */     if (aVariable instanceof Attribute) {
/* 46 */       return "Attribute";
/*    */     }
/* 48 */     return "Variable";
/*    */   }
/*    */   
/*    */   private String buildModifiers(Variable v) {
/* 52 */     String text = "";
/* 53 */     String c = "<font color=#606020><font bgcolor=#E0E0FF>";
/* 54 */     String d = "</font></font>";
/*    */     
/* 56 */     if (v instanceof Attribute) {
/* 57 */       Attribute anAttribute = (Attribute)v;
/* 58 */       if (anAttribute.isPackage()) text = String.valueOf(text) + c + "package" + d; 
/* 59 */       if (anAttribute.isStatic()) text = String.valueOf(text) + c + "static" + d; 
/* 60 */     } else if (v instanceof GlobalVariable) {
/* 61 */       GlobalVariable gv = (GlobalVariable)v;
/* 62 */       if (gv.isStatic()) text = String.valueOf(text) + c + "static" + d;
/*    */     
/*    */     } 
/* 65 */     if (v.isFinal()) text = String.valueOf(text) + c + "final" + d; 
/* 66 */     return text;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\variables\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */