/*    */ package classes.lrg.insider.plugins.core.properties.memoria.packages;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.groups.memoria.containment.PackageHasClasses;
/*    */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*    */ import lrg.insider.plugins.core.properties.memoria.packages.Detail;
/*    */ import lrg.memoria.core.Package;
/*    */ 
/*    */ public class Detail extends AbstractDetail {
/* 12 */   public Detail() { super("Detail", "Constructs a detailed HTML String to be shown in the browser.", "package", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 16 */     if (anEntity instanceof Package) {
/* 17 */       Package aPackage = (Package)anEntity;
/* 18 */       String text = "<h1>Package " + linkTo(aPackage) + "</h1><hr><br>";
/*    */       
/* 20 */       GroupEntity classes = (new PackageHasClasses()).buildGroupEntity(aPackage);
/* 21 */       GroupEntity methods = aPackage.contains("method group");
/* 22 */       GroupEntity gp = aPackage.contains("global function group");
/* 23 */       methods.union(gp);
/*    */       
/* 25 */       text = String.valueOf(text) + "<b>Package Summary</b><br>";
/* 26 */       text = String.valueOf(text) + "Classes (" + linkToNumber(classes) + ") <br><br>";
/*    */ 
/*    */       
/* 29 */       text = String.valueOf(text) + "<font bgcolor=#FFE0E0>";
/*    */       
/* 31 */       gp = classes.applyFilter("Brain Class");
/* 32 */       if (gp.size() > 0) text = String.valueOf(text) + "Brain Class: " + linkToNumber(gp) + "<br>";
/*    */       
/* 34 */       gp = classes.applyFilter("Data Class");
/* 35 */       if (gp.size() > 0) text = String.valueOf(text) + "Data Class: " + linkToNumber(gp) + "<br>";
/*    */       
/* 37 */       gp = classes.applyFilter("God Class");
/* 38 */       if (gp.size() > 0) text = String.valueOf(text) + "God Class: " + linkToNumber(gp) + "<br>";
/*    */       
/* 40 */       gp = classes.applyFilter("Tradition Breaker");
/* 41 */       if (gp.size() > 0) text = String.valueOf(text) + "Tradition Breaker: " + linkToNumber(gp) + " (classes)<br>";
/*    */       
/* 43 */       gp = classes.applyFilter("Refused Parent Bequest");
/* 44 */       if (gp.size() > 0) text = String.valueOf(text) + "Refusing Parent Bequest: " + linkToNumber(gp) + " (classes)<br>";
/*    */       
/* 46 */       gp = classes.applyFilter("Futile Hierarchy");
/* 47 */       if (gp.size() > 0) text = String.valueOf(text) + "Futile Hierarchy: " + linkToNumber(gp) + " (classes)<br>";
/*    */ 
/*    */       
/* 50 */       gp = methods.applyFilter("Brain Method");
/* 51 */       if (gp.size() > 0) text = String.valueOf(text) + "Brain Method: " + linkToNumber(gp) + " <br>";
/*    */       
/* 53 */       gp = methods.applyFilter("Feature Envy");
/* 54 */       if (gp.size() > 0) text = String.valueOf(text) + "Feature Envy: " + linkToNumber(gp) + " (methods)<br>";
/*    */       
/* 56 */       gp = methods.applyFilter("Intensive Coupling");
/* 57 */       if (gp.size() > 0) text = String.valueOf(text) + "Intensive Coupling: " + linkToNumber(gp) + " (methods)<br>";
/*    */       
/* 59 */       gp = methods.applyFilter("Extensive Coupling");
/* 60 */       if (gp.size() > 0) text = String.valueOf(text) + "Extensive Coupling: " + linkToNumber(gp) + " (methods)<br>";
/*    */       
/* 62 */       gp = methods.applyFilter("Shotgun Surgery");
/* 63 */       if (gp.size() > 0) text = String.valueOf(text) + "Shotgun Surgery: " + linkToNumber(gp) + " (methods)<br>";
/*    */       
/* 65 */       text = String.valueOf(text) + "</font>";
/*    */ 
/*    */       
/* 68 */       return new ResultEntity(text);
/*    */     } 
/* 70 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\packages\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */