/*    */ package classes.lrg.insider.plugins.details;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.details.HTMLDetail;
/*    */ import lrg.insider.plugins.details.PackageDesignFlaws;
/*    */ import lrg.insider.plugins.filters.memoria.packages.SDPViolation;
/*    */ 
/*    */ 
/*    */ public class PackageDesignFlaws
/*    */   extends HTMLDetail
/*    */ {
/* 16 */   public PackageDesignFlaws() { super("Details of Package Design Flaws", "Package Design Flaws", "package"); }
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aPackage) {
/* 19 */     String text = "<h1>Package " + linkTo(aPackage) + "</h1><hr><br>";
/*    */     
/* 21 */     GroupEntity faninClassGroup = aPackage.getGroup("fanin class group");
/* 22 */     double fanin = faninClassGroup.size();
/* 23 */     double faninP = ((GroupEntity)faninClassGroup.belongsTo("package")).distinct().size();
/*    */     
/* 25 */     GroupEntity fanoutClassGroup = aPackage.getGroup("fanout class group");
/* 26 */     double fanout = fanoutClassGroup.size();
/* 27 */     double fanoutP = ((GroupEntity)fanoutClassGroup.belongsTo("package")).distinct().size();
/*    */     
/* 29 */     double IF = ((Double)aPackage.getProperty("IF").getValue()).doubleValue();
/* 30 */     DecimalFormat twoDecimals = new DecimalFormat("#0.00");
/*    */     
/* 32 */     text = String.valueOf(text) + "<p>The package is used by " + fanin + " classes from " + faninP + "packages.</p>";
/* 33 */     text = String.valueOf(text) + "<p>The package uses " + fanout + " classes from " + fanoutP + "packages.</p>";
/* 34 */     text = String.valueOf(text) + "<p>The package has an Instability Factor of " + twoDecimals.format(IF) + "</p>";
/*    */     
/* 36 */     ArrayList<AbstractEntityInterface> sdpBreakers = SDPViolation.SDPBreakers(aPackage);
/*    */     
/* 38 */     if (sdpBreakers.size() == 0) return new ResultEntity(text);
/*    */     
/* 40 */     text = String.valueOf(text) + "<p>The package breaks the Stable Dependencies Principle(SDP), by depending on following packages, which are more instable than the current package:</p>";
/*    */     
/* 42 */     text = String.valueOf(text) + "<ul>";
/* 43 */     for (AbstractEntityInterface crtBreaker : sdpBreakers) {
/* 44 */       text = String.valueOf(text) + "<li>" + linkTo(crtBreaker) + "(InstabilityFactor = " + twoDecimals.format(crtBreaker.getProperty("IF").getValue()) + ")";
/*    */     }
/* 46 */     text = String.valueOf(text) + "</ul>";
/*    */     
/* 48 */     return new ResultEntity(text);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\details\PackageDesignFlaws.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */