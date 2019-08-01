/*    */ package classes.lrg.insider.plugins.core.properties.hismo;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*    */ import lrg.insider.plugins.core.properties.hismo.HismoDetail;
/*    */ import lrg.memoria.core.ExplicitlyDefinedType;
/*    */ import lrg.memoria.core.Function;
/*    */ import lrg.memoria.core.Location;
/*    */ import lrg.memoria.core.Variable;
/*    */ import lrg.memoria.hismo.core.AbstractHistory;
/*    */ import lrg.memoria.hismo.core.AbstractVersion;
/*    */ 
/*    */ public abstract class HismoDetail
/*    */   extends AbstractDetail
/*    */ {
/* 16 */   public HismoDetail(String name, String longName, String entityType, String type) { super(name, longName, entityType, type); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public HismoDetail(String name, String longName, String[] entityTypes, String resultEntityTypeName) { super(name, longName, entityTypes, resultEntityTypeName); }
/*    */ 
/*    */   
/*    */   public String computeHismoDetail(AbstractHistory aHistory) {
/* 25 */     text = "";
/* 26 */     text = String.valueOf(text) + "Number of versions: " + aHistory.getVersions().size() + "<br>";
/* 27 */     text = String.valueOf(text) + "First version: " + aHistory.getFirstVersion().versionName() + "<br>";
/* 28 */     return String.valueOf(text) + "Last version: " + aHistory.getLastVersion().versionName() + "<br>";
/*    */   }
/*    */ 
/*    */   
/*    */   public String computeLocationDetail(AbstractHistory aHistory) {
/* 33 */     AbstractVersion firstVersion = aHistory.getFirstVersion();
/* 34 */     AbstractVersion lastVersion = aHistory.getLastVersion();
/* 35 */     Location loc1 = null, loc2 = null;
/* 36 */     if (firstVersion instanceof ExplicitlyDefinedType) {
/* 37 */       loc1 = ((ExplicitlyDefinedType)firstVersion).getLocation();
/* 38 */       loc2 = ((ExplicitlyDefinedType)lastVersion).getLocation();
/*    */     } 
/* 40 */     if (firstVersion instanceof Function) {
/* 41 */       loc1 = ((Function)firstVersion).getLocation();
/* 42 */       loc2 = ((Function)lastVersion).getLocation();
/*    */     } 
/* 44 */     if (firstVersion instanceof Variable) {
/* 45 */       loc1 = ((Variable)firstVersion).getLocation();
/* 46 */       loc2 = ((Variable)lastVersion).getLocation();
/*    */     } 
/* 48 */     if (loc1 == null) {
/* 49 */       return "";
/*    */     }
/* 51 */     ArrayList locations = new ArrayList();
/* 52 */     text = "";
/* 53 */     text = String.valueOf(text) + "Location:";
/* 54 */     locations.add(String.valueOf(loc1.getFile().getFullName()) + " - line: " + loc1.getStartLine());
/* 55 */     locations.add(String.valueOf(loc2.getFile().getFullName()) + " - line: " + loc2.getStartLine());
/* 56 */     return String.valueOf(text) + bulletedList(locations);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\hismo\HismoDetail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */