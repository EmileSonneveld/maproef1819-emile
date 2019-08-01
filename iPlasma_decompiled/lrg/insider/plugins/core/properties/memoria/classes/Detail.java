/*    */ package classes.lrg.insider.plugins.core.properties.memoria.classes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.details.HTMLDetail;
/*    */ import lrg.insider.plugins.core.groups.memoria.containment.ClassHasAttributes;
/*    */ import lrg.insider.plugins.core.groups.memoria.containment.ClassHasMethods;
/*    */ import lrg.insider.plugins.core.properties.memoria.classes.Detail;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import lrg.memoria.core.GenericClass;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ 
/*    */ public class Detail
/*    */   extends HTMLDetail {
/* 17 */   public Detail() { super("Detail", "The detailed information for a class.", "class"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 22 */     if (anEntity instanceof GenericClass) {
/* 23 */       GenericClass aGenericClass = (GenericClass)anEntity;
/* 24 */       String text = "<big>Generic Class Detail: " + linkTo(aGenericClass) + "</big><hr><br>";
/* 25 */       text = String.valueOf(text) + "Full Name: " + aGenericClass.getFullName() + "<br>";
/* 26 */       text = String.valueOf(text) + "Number of parameters: " + aGenericClass.getTemplateParameters().size() + "<br>";
/* 27 */       text = String.valueOf(text) + bulletedLinkList(aGenericClass.getTemplateParameters());
/* 28 */       text = String.valueOf(text) + "Number of instances: " + aGenericClass.getTemplateInstances().size() + "<br>";
/* 29 */       text = String.valueOf(text) + bulletedLinkList(aGenericClass.getTemplateInstances());
/* 30 */       return new ResultEntity(text);
/*    */     } 
/*    */     
/* 33 */     if (anEntity instanceof Class) {
/* 34 */       Class aClass = (Class)anEntity;
/*    */       
/* 36 */       String amc = getAccessModeHTML(aClass.getAccessMode());
/*    */       
/* 38 */       String sik = classFlawsDecorations(anEntity);
/* 39 */       String text = "<big>" + getKindOf(aClass) + " </big><b>" + amc + "</b><big> " + linkTo(aClass.getPackage()) + " . " + linkTo(aClass) + "</big>" + 
/* 40 */         "<br>" + sik + 
/* 41 */         "<hr><br>";
/* 42 */       text = String.valueOf(text) + buildInheritanceHierarchy(aClass) + "<hr>";
/*    */       
/* 44 */       text = String.valueOf(text) + "<table><tr><td valign=\"top\">";
/* 45 */       text = String.valueOf(text) + "<b>methods (" + linkToNumber((new ClassHasMethods()).buildGroupEntity(aClass)) + ")</b><br>";
/* 46 */       text = String.valueOf(text) + bulletedLinkList(aClass.getMethodList(), new HTMLDetail.MethodDecorations());
/* 47 */       text = String.valueOf(text) + "</td><td valign=\"top\">";
/* 48 */       text = String.valueOf(text) + "<b>attributes (" + linkToNumber((new ClassHasAttributes()).buildGroupEntity(aClass)) + ")</b><br>";
/* 49 */       text = String.valueOf(text) + bulletedLinkList(aClass.getAttributeList(), new HTMLDetail.AttributeDecorations());
/* 50 */       text = String.valueOf(text) + "</td></tr></table>";
/*    */       
/* 52 */       text = String.valueOf(text) + bulletedLinkList(aClass.getInnerList());
/* 53 */       return new ResultEntity(text);
/*    */     } 
/* 55 */     return null;
/*    */   }
/*    */   
/*    */   private String getKindOf(DataAbstraction aClass) {
/* 59 */     if (aClass.isStructure())
/* 60 */       return "Structure"; 
/* 61 */     if (aClass.isInterface())
/* 62 */       return "Interface"; 
/* 63 */     if (aClass.isUnion())
/* 64 */       return "Union"; 
/* 65 */     return "class";
/*    */   }
/*    */   
/*    */   private String buildInheritanceHierarchy(DataAbstraction aClass) {
/* 69 */     String text = "";
/* 70 */     ArrayList ancestors = new ArrayList();
/* 71 */     DataAbstraction c = aClass;
/*    */     
/* 73 */     while (c != null) {
/* 74 */       ancestors.add(c);
/* 75 */       ModelElementList<DataAbstraction> ancestorsList = c.getAncestorsList();
/* 76 */       if (ancestorsList.size() > 0) {
/* 77 */         c = (DataAbstraction)ancestorsList.get(0); continue;
/*    */       } 
/*    */       break;
/*    */     } 
/* 81 */     for (int i = 1; i <= ancestors.size(); i++) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 88 */       text = String.valueOf(text) + linkTo((DataAbstraction)ancestors.get(ancestors.size() - i)) + "<br>";
/* 89 */       for (int j = 0; j < i; ) { text = String.valueOf(text) + "\t\t"; j++; }
/*    */       
/* 91 */       for (int j = 0; j < i; ) { text = String.valueOf(text) + "\t\t"; j++; }
/*    */     
/* 93 */     }  return text;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\classes\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */