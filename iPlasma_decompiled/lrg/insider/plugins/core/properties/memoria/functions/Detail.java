/*     */ package classes.lrg.insider.plugins.core.properties.memoria.functions;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.ResultEntity;
/*     */ import lrg.dude.duplication.Duplication;
/*     */ import lrg.dude.duplication.DuplicationList;
/*     */ import lrg.dude.duplication.MethodEntity;
/*     */ import lrg.insider.plugins.core.details.HTMLDetail;
/*     */ import lrg.insider.plugins.core.properties.memoria.functions.Detail;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*     */ import lrg.insider.plugins.groups.memoria.MethodsWithExternalDuplication;
/*     */ import lrg.insider.plugins.groups.memoria.MethodsWithHierarchyDuplication;
/*     */ import lrg.insider.plugins.properties.memoria.methods.IDUPLINES;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.GlobalFunction;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.Parameter;
/*     */ 
/*     */ public class Detail extends HTMLDetail {
/*  22 */   public Detail() { super("Detail", "Constructs a detailed HTML String to be shown in the browser.", new String[] { "method", "global function" }); }
/*     */ 
/*     */   
/*     */   private String buildModifiers(Method aMethod) {
/*  26 */     String c = "<font color=#606020><font bgcolor=#E0E0FF>";
/*  27 */     String d = "</font></font>";
/*  28 */     String text = "";
/*  29 */     if (aMethod.isAbstract()) text = String.valueOf(text) + c + "abstract" + d; 
/*  30 */     if ((new IsAccessor()).applyFilter(aMethod)) text = String.valueOf(text) + c + "accessor" + d; 
/*  31 */     if (aMethod.isConstructor()) text = String.valueOf(text) + c + "constructor" + d; 
/*  32 */     if (aMethod.isFinal()) text = String.valueOf(text) + c + "final" + d; 
/*  33 */     if (aMethod.isPackage()) text = String.valueOf(text) + c + "package" + d; 
/*  34 */     if (aMethod.isStatic()) text = String.valueOf(text) + c + "static" + d; 
/*  35 */     return text;
/*     */   }
/*     */   
/*     */   private String printDuplication(Function aFunction) {
/*  39 */     htmlText = "";
/*  40 */     ResultEntity aResult = aFunction.getProperty("#DUPLICATION#");
/*  41 */     if (aResult == null) return "";
/*     */ 
/*     */     
/*  44 */     if (!(aResult.getValue() instanceof DuplicationList)) return "";
/*     */     
/*  46 */     ArrayList htmlLines = new ArrayList();
/*     */     
/*  48 */     ArrayList duplicators = 
/*  49 */       IDUPLINES.getMethodsWithIntraClassDuplication((DuplicationList)aResult.getValue());
/*     */     
/*  51 */     if (duplicators.iterator().hasNext()) {
/*  52 */       htmlText = String.valueOf(htmlText) + "<b><font color=#800000><font bgcolor=#FFE0E0>Intraclass Duplication</font></font></b><br>";
/*     */     }
/*  54 */     htmlText = String.valueOf(htmlText) + "<ul>";
/*  55 */     for (Iterator it = duplicators.iterator(); it.hasNext(); ) {
/*  56 */       MethodEntity duplicator = (MethodEntity)((Duplication)it.next()).getDuplicateCode().getEntity();
/*  57 */       htmlText = String.valueOf(htmlText) + "<li>&nbsp;" + linkTo(duplicator.getMethod()) + "  [" + duplicator.getNoOfRelevantLines() + "]";
/*     */     } 
/*  59 */     htmlText = String.valueOf(htmlText) + "</ul>";
/*  60 */     htmlText = String.valueOf(htmlText) + bulletedList(htmlLines);
/*     */     
/*  62 */     duplicators = 
/*  63 */       MethodsWithHierarchyDuplication.getMethodsWithHierarchyDuplication((DuplicationList)aResult.getValue());
/*     */     
/*  65 */     if (duplicators.iterator().hasNext()) {
/*  66 */       htmlText = String.valueOf(htmlText) + "<b><font color=#800000><font bgcolor=#FFE0E0>Hierarchy Duplication</font></font></b><br>";
/*     */     }
/*  68 */     htmlText = String.valueOf(htmlText) + "<ul>";
/*  69 */     for (Iterator it = duplicators.iterator(); it.hasNext(); ) {
/*  70 */       MethodEntity duplicator = (MethodEntity)((Duplication)it.next()).getDuplicateCode().getEntity();
/*  71 */       htmlText = String.valueOf(htmlText) + "<li> &nbsp;" + linkTo(duplicator.getMethod().belongsTo("class")) + " :: " + linkTo(duplicator.getMethod()) + "  [" + duplicator.getNoOfRelevantLines() + "]";
/*     */     } 
/*  73 */     htmlText = String.valueOf(htmlText) + "</ul>";
/*     */ 
/*     */     
/*  76 */     duplicators = 
/*  77 */       MethodsWithExternalDuplication.getUnrelatedMethodsWithDuplication((DuplicationList)aResult.getValue());
/*     */     
/*  79 */     if (duplicators.iterator().hasNext()) {
/*  80 */       htmlText = String.valueOf(htmlText) + "<b><font color=#800000><font bgcolor=#FFE0E0>External Duplication</font></font></b><br>";
/*     */     }
/*  82 */     htmlText = String.valueOf(htmlText) + "<ul>";
/*  83 */     for (Iterator it = duplicators.iterator(); it.hasNext(); ) {
/*  84 */       MethodEntity duplicator = (MethodEntity)((Duplication)it.next()).getDuplicateCode().getEntity();
/*  85 */       htmlText = String.valueOf(htmlText) + "<li>&nbsp;" + linkTo(duplicator.getMethod().belongsTo("class")) + " :: " + linkTo(duplicator.getMethod()) + "  [" + duplicator.getNoOfRelevantLines() + "]";
/*     */     } 
/*     */     
/*  88 */     return String.valueOf(htmlText) + "</ul>";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/*  95 */     if (!(anEntity instanceof Function)) return null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     Function aFunction = (Function)anEntity;
/* 110 */     String returnType = "";
/* 111 */     if (aFunction.getReturnType() != null) {
/* 112 */       returnType = linkTo(aFunction.getReturnType().getFullName());
/*     */     }
/* 114 */     String text = "<big></big>";
/* 115 */     if (anEntity instanceof Method) {
/* 116 */       text = String.valueOf(text) + getAccessModeHTML(((Method)anEntity).getAccessMode());
/*     */     }
/*     */     
/* 119 */     String packageFullName = aFunction.getScope().getFullName();
/* 120 */     if (aFunction instanceof GlobalFunction) {
/* 121 */       packageFullName = ((GlobalFunction)aFunction).getPackage().getFullName();
/*     */     }
/* 123 */     text = String.valueOf(text) + "<big> " + returnType + "  " + linkTo(packageFullName) + " :: " + linkTo(aFunction) + " ( ";
/*     */     
/* 125 */     Iterator paramIt = aFunction.getParameterList().iterator();
/*     */     
/* 127 */     while (paramIt.hasNext()) {
/* 128 */       Parameter p = (Parameter)paramIt.next();
/* 129 */       String theParameter = String.valueOf(linkTo(p.getType().getFullName())) + " " + linkTo(p.getFullName());
/*     */       
/* 131 */       text = String.valueOf(text) + theParameter;
/* 132 */       if (paramIt.hasNext()) text = String.valueOf(text) + ", "; 
/*     */     } 
/* 134 */     text = String.valueOf(text) + " )</big><br>";
/*     */     
/* 136 */     if (aFunction instanceof Method) {
/* 137 */       text = String.valueOf(text) + buildModifiers((Method)anEntity) + "<br>";
/*     */     }
/* 139 */     text = String.valueOf(text) + "<b>" + (new HTMLDetail.MethodDecorations()).getAfterDecoration(anEntity) + "</b><br><hr>";
/* 140 */     text = String.valueOf(text) + printDuplication(aFunction) + "<hr><br>";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     return new ResultEntity(text);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\functions\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */