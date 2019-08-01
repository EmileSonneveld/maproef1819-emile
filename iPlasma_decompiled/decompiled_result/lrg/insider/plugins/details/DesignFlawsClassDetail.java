/*     */ package classes.lrg.insider.plugins.details;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.entities.ResultEntity;
/*     */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*     */ import lrg.insider.plugins.core.details.HTMLDetail;
/*     */ import lrg.insider.plugins.core.groups.memoria.containment.ClassHasAttributes;
/*     */ import lrg.insider.plugins.core.groups.memoria.containment.ClassHasMethods;
/*     */ import lrg.insider.plugins.details.DesignFlawsClassDetail;
/*     */ import lrg.insider.plugins.filters.memoria.classes.DataClass;
/*     */ import lrg.insider.plugins.filters.memoria.classes.GodClass;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IsConstructor;
/*     */ import lrg.insider.plugins.filters.memoria.variables.IsConstant;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.DataAbstraction;
/*     */ import lrg.memoria.core.GenericClass;
/*     */ import lrg.memoria.core.ModelElementList;
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
/*     */ public class DesignFlawsClassDetail
/*     */   extends HTMLDetail
/*     */ {
/*  36 */   public DesignFlawsClassDetail() { super("Details of Class Design Flaws", "Detail with Full Information on Design Flaws", "class"); }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/*  41 */     if (anEntity instanceof GenericClass) {
/*  42 */       GenericClass aGenericClass = (GenericClass)anEntity;
/*  43 */       String text = "<big>Generic Class Detail: " + linkTo(aGenericClass) + "</big><hr><br>";
/*  44 */       text = String.valueOf(text) + "Full Name: " + aGenericClass.getFullName() + "<br>";
/*  45 */       text = String.valueOf(text) + "Number of parameters: " + aGenericClass.getTemplateParameters().size() + "<br>";
/*  46 */       text = String.valueOf(text) + bulletedLinkList(aGenericClass.getTemplateParameters());
/*  47 */       text = String.valueOf(text) + "Number of instances: " + aGenericClass.getTemplateInstances().size() + "<br>";
/*  48 */       text = String.valueOf(text) + bulletedLinkList(aGenericClass.getTemplateInstances());
/*  49 */       return new ResultEntity(text);
/*     */     } 
/*     */     
/*  52 */     if (anEntity instanceof Class) {
/*  53 */       Class aClass = (Class)anEntity;
/*     */       
/*  55 */       String amc = getAccessModeHTML(aClass.getAccessMode());
/*     */       
/*  57 */       String text = "<big>" + getKindOf(aClass) + " </big><b>" + amc + "</b><big> " + linkTo(aClass.getPackage()) + " . " + linkTo(aClass) + "</big>" + 
/*  58 */         "<br>" + classFlawsDecorations(anEntity) + 
/*  59 */         "<hr><br>";
/*  60 */       text = String.valueOf(text) + buildInheritanceHierarchy(aClass);
/*     */ 
/*     */       
/*  63 */       if ((new DataClass()).applyFilter(anEntity)) text = String.valueOf(text) + "<hr>" + printDataClassDetails(anEntity); 
/*  64 */       if ((new GodClass()).applyFilter(anEntity)) text = String.valueOf(text) + "<hr>" + printGodClassDetails(anEntity);
/*     */ 
/*     */       
/*  67 */       text = String.valueOf(text) + "<hr>";
/*  68 */       text = String.valueOf(text) + "<table><tr><td valign=\"top\">";
/*  69 */       text = String.valueOf(text) + "<b>methods (" + linkToNumber((new ClassHasMethods()).buildGroupEntity(aClass)) + ")</b><br>";
/*  70 */       text = String.valueOf(text) + bulletedLinkList(aClass.getMethodList(), new HTMLDetail.MethodDecorations());
/*  71 */       text = String.valueOf(text) + "</td><td valign=\"top\">";
/*  72 */       text = String.valueOf(text) + "<b>attributes (" + linkToNumber((new ClassHasAttributes()).buildGroupEntity(aClass)) + ")</b><br>";
/*  73 */       text = String.valueOf(text) + bulletedLinkList(aClass.getAttributeList(), new HTMLDetail.AttributeDecorations());
/*  74 */       text = String.valueOf(text) + "</td></tr></table>";
/*     */       
/*  76 */       return new ResultEntity(text);
/*     */     } 
/*  78 */     return null;
/*     */   }
/*     */   
/*     */   private String writeAttributesList(AbstractEntityInterface anEntity, GroupEntity foreignData) {
/*  82 */     String tmp = "";
/*  83 */     ArrayList<AbstractEntityInterface> allMethods = anEntity.getGroup("method group").getElements();
/*  84 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsConstant());
/*     */ 
/*     */ 
/*     */     
/*  88 */     for (AbstractEntityInterface crtMethod : allMethods) {
/*  89 */       GroupEntity allAccesses = crtMethod.uses("variables accessed").applyFilter("is attribute").applyFilter(notComposedFilteringRule);
/*  90 */       allAccesses = allAccesses.union(crtMethod.uses("operations called").applyFilter("is accessor")).distinct();
/*     */       
/*  92 */       ArrayList<AbstractEntityInterface> accessedForeignData = allAccesses.intersect(foreignData).getElements();
/*  93 */       if (accessedForeignData.size() > 0) {
/*  94 */         tmp = String.valueOf(tmp) + "<ul>";
/*  95 */         tmp = String.valueOf(tmp) + "<li>" + linkTo(crtMethod) + (new HTMLDetail.MethodDecorations()).getAfterDecoration(crtMethod);
/*  96 */         tmp = String.valueOf(tmp) + "<ul>";
/*  97 */         for (AbstractEntityInterface theAttribute : accessedForeignData) {
/*  98 */           tmp = String.valueOf(tmp) + "<li>" + classFlawsDecorations(theAttribute.belongsTo("class")) + linkTo(theAttribute.belongsTo("class"));
/*  99 */           tmp = String.valueOf(tmp) + " :: " + linkTo(theAttribute) + (new HTMLDetail.AttributeDecorations()).getBeforeDecoration(crtMethod);
/*     */         } 
/* 101 */         tmp = String.valueOf(tmp) + "</ul>";
/* 102 */         tmp = String.valueOf(tmp) + "</ul>";
/*     */       } 
/*     */     } 
/* 105 */     return tmp;
/*     */   }
/*     */   
/*     */   private String printGodClassDetails(AbstractEntityInterface anEntity) {
/* 109 */     tmp = "";
/* 110 */     DecimalFormat twoDecimals = new DecimalFormat("#0.00");
/* 111 */     GroupEntity foreignData = anEntity.getGroup("foreign data").distinct();
/* 112 */     GroupEntity foreignDataClasses = ((GroupEntity)foreignData.belongsTo("class")).distinct();
/* 113 */     ArrayList<AbstractEntityInterface> fDataLista = foreignData.getElements();
/* 114 */     tmp = String.valueOf(tmp) + "<br>This is a &nbsp; <i>God Class</i> &nbsp; because:";
/* 115 */     tmp = String.valueOf(tmp) + "<ul>";
/* 116 */     tmp = String.valueOf(tmp) + "<li> some of its methods access directly (or via getter/setters) ";
/* 117 */     tmp = String.valueOf(tmp) + anEntity.getProperty("ATFD").getValue() + " attributes from  " + foreignDataClasses.size() + " external classes:";
/* 118 */     tmp = String.valueOf(tmp) + writeAttributesList(anEntity, foreignData);
/* 119 */     tmp = String.valueOf(tmp) + "<li> the methods are very complex, i.e. have many branches (" + anEntity.getProperty("WMC").getValue() + " in total, and " + anEntity.getProperty("AMW") + " in average)</li>";
/* 120 */     tmp = String.valueOf(tmp) + "<li> it is non-cohesive (TCC=" + twoDecimals.format(anEntity.getProperty("TCC").getValue()) + ") with respect to the way methods use the attributes of the class. ";
/* 121 */     return String.valueOf(tmp) + "</ul>";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String printDataClassDetails(AbstractEntityInterface anEntity) {
/* 128 */     tmp = "";
/* 129 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsConstructor());
/* 130 */     GroupEntity allMethods = anEntity.contains("method group");
/* 131 */     GroupEntity publicMethods = allMethods.applyFilter("is public").applyFilter(notComposedFilteringRule);
/* 132 */     GroupEntity publicAttr = anEntity.contains("attribute group").applyFilter("not encapsulated");
/* 133 */     GroupEntity accessorMethods = publicMethods.applyFilter("is accessor");
/*     */     
/* 135 */     int totalPublicMembers = publicMethods.size() + publicAttr.size();
/*     */     
/* 137 */     tmp = String.valueOf(tmp) + "<br>This is a &nbsp; <i>Data Class</i> because:";
/* 138 */     tmp = String.valueOf(tmp) + "<ul>";
/* 139 */     tmp = String.valueOf(tmp) + "<li> only " + (publicMethods.size() - accessorMethods.size()) + " of all the " + totalPublicMembers + " interface members are functional methods. ";
/* 140 */     tmp = String.valueOf(tmp) + "<li> The lack of encapsulation happens because of these ";
/* 141 */     if (publicAttr.size() > 0)
/* 142 */       tmp = String.valueOf(tmp) + "public attributes: " + bulletedLinkList(publicAttr.getElements(), new HTMLDetail.AttributeDecorations()); 
/* 143 */     if (accessorMethods.size() > 0) {
/* 144 */       if (publicAttr.size() > 0) tmp = String.valueOf(tmp) + " and "; 
/* 145 */       tmp = String.valueOf(tmp) + " accessor methods (getter and setters): " + bulletedLinkList(accessorMethods.getElements(), new HTMLDetail.MethodDecorations());
/*     */     } 
/* 147 */     tmp = String.valueOf(tmp) + "</ul>";
/* 148 */     tmp = String.valueOf(tmp) + "<br> The non-encapsulated methods are used from OUTSIDE the class as follows: ";
/* 149 */     ArrayList<AbstractEntityInterface> publicAttrArray = publicAttr.getElements();
/* 150 */     ArrayList<AbstractEntityInterface> accessorMethodsArray = accessorMethods.getElements();
/* 151 */     tmp = String.valueOf(tmp) + "<ul>";
/* 152 */     for (AbstractEntityInterface crtAttr : publicAttrArray) {
/* 153 */       tmp = String.valueOf(tmp) + "<li> " + linkTo(crtAttr);
/* 154 */       tmp = String.valueOf(tmp) + "<ul>";
/* 155 */       ArrayList<AbstractEntityInterface> methodUsers = crtAttr.getGroup("methods accessing variable").exclude(allMethods).distinct().getElements();
/* 156 */       if (methodUsers.size() == 0) tmp = String.valueOf(tmp) + "MAKE PRIVATE! (not used from outside)"; 
/* 157 */       for (AbstractEntityInterface crtMethod : methodUsers) {
/* 158 */         tmp = String.valueOf(tmp) + "<li>" + classFlawsDecorations(crtMethod.belongsTo("class")) + linkTo(crtMethod.belongsTo("class"));
/* 159 */         tmp = String.valueOf(tmp) + " :: " + linkTo(crtMethod) + (new HTMLDetail.MethodDecorations()).getAfterDecoration(crtMethod);
/*     */       } 
/*     */       
/* 162 */       tmp = String.valueOf(tmp) + "</ul>";
/*     */     } 
/*     */     
/* 165 */     for (AbstractEntityInterface crtAccessor : accessorMethodsArray) {
/* 166 */       tmp = String.valueOf(tmp) + "<li> " + linkTo(crtAccessor);
/* 167 */       ArrayList<AbstractEntityInterface> methodUsers = crtAccessor.getGroup("operations calling me").exclude(allMethods).distinct().getElements();
/*     */       
/* 169 */       if (methodUsers.size() == 0) tmp = String.valueOf(tmp) + "<b>MAKE PRIVATE! (not used from outside the class) </b>";
/*     */       
/* 171 */       tmp = String.valueOf(tmp) + "<ul>";
/* 172 */       for (AbstractEntityInterface crtMethod : methodUsers) {
/* 173 */         tmp = String.valueOf(tmp) + "<li>" + classFlawsDecorations(crtMethod.belongsTo("class")) + linkTo(crtMethod.belongsTo("class"));
/* 174 */         tmp = String.valueOf(tmp) + "." + linkTo(crtMethod) + (new HTMLDetail.MethodDecorations()).getAfterDecoration(crtMethod);
/*     */       } 
/* 176 */       tmp = String.valueOf(tmp) + "</ul>";
/*     */     } 
/*     */ 
/*     */     
/* 180 */     return String.valueOf(tmp) + "</ul>";
/*     */   }
/*     */ 
/*     */   
/*     */   private String printBrainClassDetails(AbstractEntityInterface anEntity) {
/* 185 */     tmp = "";
/* 186 */     DecimalFormat twoDecimals = new DecimalFormat("#0.00");
/* 187 */     tmp = String.valueOf(tmp) + "<br>This is a &nbsp; <i>Brain Class</i> because:";
/* 188 */     tmp = String.valueOf(tmp) + "<ul>";
/* 189 */     tmp = String.valueOf(tmp) + "<li> it is long (" + anEntity.getProperty("LOCC").getValue() + " lines)</li>";
/* 190 */     tmp = String.valueOf(tmp) + "<li> it's methods are very complex, i.e. have many branches (" + anEntity.getProperty("WMC").getValue() + " in total, and " + anEntity.getProperty("AMW") + " in average)</li>";
/* 191 */     tmp = String.valueOf(tmp) + "<li> it contains following &nbsp; <i>Brain Methods</i>:";
/* 192 */     tmp = String.valueOf(tmp) + bulletedLinkList(anEntity.contains("method group").applyFilter("Brain Method").getElements(), new HTMLDetail.MethodDecorations()) + "</li>";
/* 193 */     tmp = String.valueOf(tmp) + "<li> it is non-cohesive (TCC=" + twoDecimals.format(anEntity.getProperty("TCC").getValue()) + ") with respect to the way methods use the attributes of the class. ";
/* 194 */     tmp = String.valueOf(tmp) + "Following cohesive groups of methods were detected: <br>";
/* 195 */     HashMap<GroupEntity, GroupEntity> attribClusteredMethods = (HashMap)anEntity.getProperty("Attribute Usage Clusters").getValue();
/* 196 */     for (GroupEntity crtKey : attribClusteredMethods.keySet()) {
/* 197 */       if (crtKey.size() > 0) tmp = String.valueOf(tmp) + commaLinkList(((GroupEntity)attribClusteredMethods.get(crtKey)).distinct().getElements()) + " &nbsp; &nbsp;are accessed in common by [ " + commaLinkList(crtKey.getElements()) + " ] <br>";
/*     */     
/*     */     } 
/* 200 */     return String.valueOf(tmp) + "</ul>";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String getKindOf(Class aClass) {
/* 206 */     if (aClass.isStructure())
/* 207 */       return "Structure"; 
/* 208 */     if (aClass.isInterface())
/* 209 */       return "Interface"; 
/* 210 */     if (aClass.isUnion())
/* 211 */       return "Union"; 
/* 212 */     return "class";
/*     */   }
/*     */   
/*     */   private String buildInheritanceHierarchy(Class aClass) {
/* 216 */     String text = "";
/* 217 */     ArrayList ancestors = new ArrayList();
/* 218 */     Class clazz = aClass;
/*     */     
/* 220 */     while (clazz != null) {
/* 221 */       ancestors.add(clazz);
/* 222 */       ModelElementList<DataAbstraction> ancestorsList = clazz.getAncestorsList();
/* 223 */       if (ancestorsList.size() > 0) {
/* 224 */         DataAbstraction dataAbstraction = (DataAbstraction)ancestorsList.get(0); continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 228 */     for (int i = 1; i <= ancestors.size(); i++) {
/* 229 */       text = String.valueOf(text) + linkTo((Class)ancestors.get(ancestors.size() - i)) + "<br>";
/* 230 */       for (int j = 0; j < i; ) { text = String.valueOf(text) + "&nbsp;&nbsp;&nbsp;&nbsp;"; j++; }
/*     */       
/* 232 */       for (int j = 0; j < i; ) { text = String.valueOf(text) + "&nbsp;&nbsp;&nbsp;&nbsp;"; j++; }
/*     */     
/* 234 */     }  return text;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\details\DesignFlawsClassDetail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */