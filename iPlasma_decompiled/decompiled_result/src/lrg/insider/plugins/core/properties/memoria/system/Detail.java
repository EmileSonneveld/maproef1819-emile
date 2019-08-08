/*    */ package classes.lrg.insider.plugins.core.properties.memoria.system;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.details.HTMLDetail;
/*    */ import lrg.insider.plugins.core.properties.memoria.system.Detail;
/*    */ import lrg.insider.plugins.details.OverviewPyramid;
/*    */ import lrg.memoria.core.System;
/*    */ 
/*    */ public class Detail extends HTMLDetail {
/* 12 */   public Detail() { super("Detail", "Constructs a detailed HTML String to be shown in the browser.", "system"); }
/*    */ 
/*    */   
/*    */   private String printDesignFlasws(AbstractEntityInterface theSystem) {
/* 16 */     text = "";
/* 17 */     GroupEntity classes = theSystem.getGroup("class group");
/* 18 */     GroupEntity methods = theSystem.contains("method group").union(theSystem.contains("global function group"));
/*    */     
/* 20 */     classes = classes.applyFilter("model class");
/* 21 */     methods = methods.applyFilter("model function");
/* 22 */     text = String.valueOf(text) + "<font bgcolor=#FFE0E0>";
/*    */ 
/*    */     
/* 25 */     GroupEntity gp = classes.applyFilter("Brain Class");
/* 26 */     if (gp.size() > 0) text = String.valueOf(text) + "Brain Class: " + linkToNumber(gp) + "<br>";
/*    */     
/* 28 */     gp = methods.applyFilter("Brain Method");
/* 29 */     if (gp.size() > 0) text = String.valueOf(text) + "Brain Method: " + linkToNumber(gp) + " <br>";
/*    */     
/* 31 */     gp = classes.applyFilter("God Class");
/* 32 */     if (gp.size() > 0) text = String.valueOf(text) + "God Class: " + linkToNumber(gp) + "<br>";
/*    */     
/* 34 */     gp = classes.applyFilter("Data Class");
/* 35 */     if (gp.size() > 0) text = String.valueOf(text) + "Data Class: " + linkToNumber(gp) + "<br>";
/*    */     
/* 37 */     gp = classes.applyFilter("Tradition Breaker");
/* 38 */     if (gp.size() > 0) text = String.valueOf(text) + "Tradition Breaker: " + linkToNumber(gp) + " (classes)<br>";
/*    */     
/* 40 */     gp = classes.applyFilter("Refused Parent Bequest");
/* 41 */     if (gp.size() > 0) text = String.valueOf(text) + "Refusing Parent Bequest: " + linkToNumber(gp) + " (classes)<br>";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 48 */     gp = methods.applyFilter("Feature Envy");
/* 49 */     if (gp.size() > 0) text = String.valueOf(text) + "Feature Envy [" + linkToNumber(gp) + " methods]<br>";
/*    */     
/* 51 */     gp = methods.applyFilter("Intensive Coupling");
/* 52 */     if (gp.size() > 0) text = String.valueOf(text) + "Intensive Coupling [" + linkToNumber(gp) + " methods]<br>";
/*    */     
/* 54 */     gp = methods.applyFilter("Extensive Coupling");
/* 55 */     if (gp.size() > 0) text = String.valueOf(text) + "Extensive Coupling [" + linkToNumber(gp) + " methods]<br>";
/*    */     
/* 57 */     gp = methods.applyFilter("Shotgun Surgery");
/* 58 */     if (gp.size() > 0) text = String.valueOf(text) + "Shotgun Surgery [" + linkToNumber(gp) + " methods]<br>";
/*    */     
/* 60 */     return String.valueOf(text) + "</font>";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 66 */     if (anEntity instanceof System) {
/* 67 */       System aSystem = (System)anEntity;
/* 68 */       String text = "<h1>System Detail: " + linkTo(aSystem) + "</h1><hr><br>";
/*    */       
/* 70 */       text = String.valueOf(text) + "<table><tr><td valign=\"top\">";
/* 71 */       text = String.valueOf(text) + (new OverviewPyramid()).buildHTMLPyramid(aSystem);
/* 72 */       text = String.valueOf(text) + "</td><td valign=\"top\">";
/*    */       
/* 74 */       text = String.valueOf(text) + "</td></tr></table>";
/*    */       
/* 76 */       return new ResultEntity(text);
/*    */     } 
/* 78 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\system\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */