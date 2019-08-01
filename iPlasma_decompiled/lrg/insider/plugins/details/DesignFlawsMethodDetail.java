/*     */ package classes.lrg.insider.plugins.details;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.entities.ResultEntity;
/*     */ import lrg.insider.plugins.core.details.HTMLDetail;
/*     */ import lrg.insider.plugins.details.DesignFlawsMethodDetail;
/*     */ import lrg.insider.plugins.filters.memoria.methods.FeatureEnvy;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IntensiveCoupling;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.Parameter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DesignFlawsMethodDetail
/*     */   extends HTMLDetail
/*     */ {
/*  46 */   public DesignFlawsMethodDetail() { super("Details of Method Design Flaws", "Detail customized depending on design flaw", "method"); }
/*     */ 
/*     */   
/*     */   private String buildModifiers(Method aMethod) {
/*  50 */     String c = "<font color=#606020><font bgcolor=#E0E0FF>";
/*  51 */     String d = "</font></font>";
/*  52 */     String text = "";
/*  53 */     if (aMethod.isAbstract()) text = String.valueOf(text) + c + "abstract" + d; 
/*  54 */     if ((new IsAccessor()).applyFilter(aMethod)) text = String.valueOf(text) + c + "accessor" + d; 
/*  55 */     if (aMethod.isConstructor()) text = String.valueOf(text) + c + "constructor" + d; 
/*  56 */     if (aMethod.isFinal()) text = String.valueOf(text) + c + "final" + d; 
/*  57 */     if (aMethod.isPackage()) text = String.valueOf(text) + c + "package" + d; 
/*  58 */     if (aMethod.isStatic()) text = String.valueOf(text) + c + "static" + d; 
/*  59 */     return text;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/*  64 */     Function aFunction = (Function)anEntity;
/*  65 */     String returnType = "";
/*  66 */     if (aFunction.getReturnType() != null) {
/*  67 */       returnType = linkTo(aFunction.getReturnType().getFullName());
/*     */     }
/*  69 */     String text = "<big></big>";
/*  70 */     if (anEntity instanceof Method) {
/*  71 */       text = String.valueOf(text) + getAccessModeHTML(((Method)anEntity).getAccessMode());
/*     */     }
/*     */     
/*  74 */     text = String.valueOf(text) + "<big> " + returnType + "  " + linkTo(aFunction.getScope().getFullName()) + " :: " + linkTo(aFunction) + " ( ";
/*     */     
/*  76 */     Iterator paramIt = aFunction.getParameterList().iterator();
/*     */     
/*  78 */     while (paramIt.hasNext()) {
/*  79 */       Parameter p = (Parameter)paramIt.next();
/*  80 */       String theParameter = String.valueOf(linkTo(p.getType().getFullName())) + " " + linkTo(p.getFullName());
/*     */       
/*  82 */       text = String.valueOf(text) + theParameter;
/*  83 */       if (paramIt.hasNext()) text = String.valueOf(text) + ", "; 
/*     */     } 
/*  85 */     text = String.valueOf(text) + " )</big><br>";
/*     */     
/*  87 */     if (aFunction instanceof Method) {
/*  88 */       text = String.valueOf(text) + buildModifiers((Method)anEntity) + "<br>";
/*     */     }
/*  90 */     text = String.valueOf(text) + "<b>" + (new HTMLDetail.MethodDecorations()).getAfterDecoration(anEntity) + "</b><br>";
/*     */ 
/*     */     
/*  93 */     if ((new FeatureEnvy()).applyFilter(anEntity)) text = String.valueOf(text) + "<hr>" + printFeatureEnvyDetails(anEntity); 
/*  94 */     if ((new IntensiveCoupling()).applyFilter(anEntity)) text = String.valueOf(text) + "<hr>" + printIntensiveCouplingDetails(anEntity);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     return new ResultEntity(text);
/*     */   }
/*     */   
/*     */   private String printBrainMethodDetails(AbstractEntityInterface anEntity) {
/* 122 */     tmp = "";
/* 123 */     tmp = String.valueOf(tmp) + "<br>This is a &nbsp; <i>Brain Method</i> &nbsp; because:";
/* 124 */     tmp = String.valueOf(tmp) + "<ul>";
/* 125 */     tmp = String.valueOf(tmp) + "<li> it is long (" + anEntity.getProperty("LOC").getValue() + " lines)</li>";
/* 126 */     tmp = String.valueOf(tmp) + "<li> it has many branches (" + anEntity.getProperty("CYCLO").getValue() + ") and a deep nesting level (" + anEntity.getProperty("MAXNESTING").getValue() + ")</li>";
/* 127 */     tmp = String.valueOf(tmp) + "<li> it uses many variables (" + anEntity.getProperty("NOAV").getValue() + ")</li>";
/* 128 */     tmp = String.valueOf(tmp) + "</ul>";
/* 129 */     AbstractEntity abstractEntity = anEntity.belongsTo("class");
/* 130 */     GroupEntity attributesOfClass = abstractEntity.contains("attribute group");
/* 131 */     int usedLocalAttributes = anEntity.getGroup("variables accessed").intersect(attributesOfClass).distinct().size();
/* 132 */     if (usedLocalAttributes == 0 && attributesOfClass.size() == 0) { tmp = String.valueOf(tmp) + "The method uses NO ATTRIBUTES "; }
/* 133 */     else { tmp = String.valueOf(tmp) + "The method uses " + usedLocalAttributes; }
/* 134 */      tmp = String.valueOf(tmp) + " (out of " + attributesOfClass.size() + ") local attributes.";
/*     */     
/* 136 */     HashMap<GroupEntity, GroupEntity> clusteredMethods = (HashMap)anEntity.belongsTo("class").getProperty("Attribute Usage Clusters").getValue();
/* 137 */     int counter = 0;
/* 138 */     String text = "";
/* 139 */     for (GroupEntity crtKey : clusteredMethods.keySet()) {
/* 140 */       if (crtKey.size() > 0 && crtKey.isInGroup(anEntity)) {
/* 141 */         counter++;
/* 142 */         text = String.valueOf(text) + "[ " + commaLinkList(crtKey.getElements()) + " ] use attribute(s): &nbsp;" + commaLinkList(((GroupEntity)clusteredMethods.get(crtKey)).getElements()) + "<br>";
/*     */       } 
/*     */     } 
/*     */     
/* 146 */     tmp = String.valueOf(tmp) + "<br>The method is involved in following groups of methods by common usage of attributes (" + counter + " out of " + clusteredMethods.keySet().size() + "):<br>";
/* 147 */     return String.valueOf(tmp) + text + "<br>";
/*     */   }
/*     */ 
/*     */   
/*     */   private String printFeatureEnvyDetails(AbstractEntityInterface anEntity) {
/* 152 */     tmp = "";
/* 153 */     AbstractEntity abstractEntity = anEntity.belongsTo("class");
/* 154 */     GroupEntity attributesOfClass = abstractEntity.contains("attribute group");
/* 155 */     int usedLocalAttributes = anEntity.getGroup("variables accessed").intersect(attributesOfClass).distinct().size();
/*     */     
/* 157 */     tmp = String.valueOf(tmp) + "<br>This is a &nbsp; <i>Feature Envy</i> &nbsp; because:";
/* 158 */     tmp = String.valueOf(tmp) + "<ul>";
/* 159 */     if (usedLocalAttributes == 0 && attributesOfClass.size() == 0) {
/* 160 */       tmp = String.valueOf(tmp) + "<li>it uses NO ATTRIBUTES of the class, because the class has no attributes!</li>";
/* 161 */     } else if (usedLocalAttributes == 0) {
/* 162 */       tmp = String.valueOf(tmp) + "<li>it uses NO ATTRIBUTES of the class (out of " + attributesOfClass.size() + ")" + "</li>";
/*     */     } else {
/* 164 */       tmp = String.valueOf(tmp) + "<li>it uses few (" + usedLocalAttributes + " out of " + attributesOfClass.size() + ") local attributes </li>";
/* 165 */     }  tmp = String.valueOf(tmp) + "<li> it uses following non-encapsulated attributes:</li>";
/* 166 */     tmp = String.valueOf(tmp) + "<ul>";
/* 167 */     GroupEntity accData = anEntity.getGroup("accessed model data");
/* 168 */     GroupEntity classAndAncestors = anEntity.getGroup("current class and ancestors");
/* 169 */     GroupEntity allMethodsAttributes = classAndAncestors.getGroup("method group").union(classAndAncestors.getGroup("attribute group"));
/*     */     
/* 171 */     ArrayList<AbstractEntityInterface> accessedAttributes = accData.exclude(allMethodsAttributes).getElements();
/* 172 */     for (AbstractEntityInterface anAttribute : accessedAttributes) {
/* 173 */       tmp = String.valueOf(tmp) + "<li>" + classFlawsDecorations(anAttribute.belongsTo("class")) + linkTo(anAttribute.belongsTo("class")) + "." + linkTo(anAttribute);
/* 174 */       GroupEntity users = ((GroupEntity)anAttribute.isUsed("methods accessing variable").belongsTo("class")).distinct().exclude((AbstractEntity)abstractEntity);
/* 175 */       if (users.size() > 0) {
/* 176 */         tmp = String.valueOf(tmp) + " The attribute is also used by: &nbsp;&nbsp;&nbsp;";
/* 177 */         tmp = String.valueOf(tmp) + commaLinkList(users.getElements()) + "</li>"; continue;
/*     */       } 
/* 179 */       tmp = String.valueOf(tmp) + " The attribute is NOT USED by anyone else!";
/*     */     } 
/* 181 */     tmp = String.valueOf(tmp) + "</ul>";
/* 182 */     return String.valueOf(tmp) + "</ul>";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String printIntensiveCouplingDetails(AbstractEntityInterface anEntity) {
/* 188 */     tmp = "";
/* 189 */     GroupEntity classMethods = new GroupEntity("group", new ArrayList());
/*     */     
/* 191 */     if (anEntity.belongsTo("class") != null) classMethods = anEntity.belongsTo("class").contains("method group"); 
/* 192 */     GroupEntity calledMethods = anEntity.getGroup("operations called").applyFilter("model function").exclude(classMethods).distinct();
/* 193 */     GroupEntity calledClasses = ((GroupEntity)calledMethods.belongsTo("class")).distinct();
/*     */     
/* 195 */     tmp = String.valueOf(tmp) + "This method has &nbsp; <i>Intensive Coupling</i> &nbsp; because it calls many methods (";
/* 196 */     tmp = String.valueOf(tmp) + calledMethods.size() + ") from a few classes (" + calledClasses.size() + ") namely: <ul>";
/* 197 */     ArrayList<AbstractEntityInterface> calledMethodsArray = calledMethods.getElements();
/* 198 */     for (AbstractEntityInterface crtMethod : calledMethodsArray) {
/* 199 */       tmp = String.valueOf(tmp) + "<li>" + classFlawsDecorations(crtMethod.belongsTo("class")) + linkTo(crtMethod.belongsTo("class"));
/* 200 */       tmp = String.valueOf(tmp) + " :: " + linkTo(crtMethod);
/*     */     } 
/* 202 */     return String.valueOf(tmp) + "</ul>";
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\details\DesignFlawsMethodDetail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */