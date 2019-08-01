/*    */ package lrg.memoria.importer.mcc.loader;
/*    */ 
/*    */ import lrg.memoria.core.CodeStripe;
/*    */ import lrg.memoria.core.File;
/*    */ import lrg.memoria.core.FunctionBody;
/*    */ import lrg.memoria.core.Location;
/*    */ import lrg.memoria.core.Package;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultBodyVisitor
/*    */   extends DefaultVisitorRoot
/*    */   implements BodyVisitor
/*    */ {
/*    */   private Integer id;
/*    */   private Package currentPackage;
/*    */   private int noDecisions;
/*    */   private int noAnd;
/*    */   private int noOr;
/*    */   private int noReturn;
/*    */   
/* 22 */   public void setId(Integer id) { this.id = id; }
/*    */   private int noCatch; private int noLoops; private int maxNesting; private int noStatements; private int noCodeLine; private Location location; private int cyclo;
/*    */   
/*    */   public void setLocation(String fileFullName, Integer startPosition, Integer stopPosition) {
/* 26 */     if (fileFullName.compareTo("NULL") == 0) {
/* 27 */       this.location = Location.getUnknownLocation();
/*    */     } else {
/* 29 */       File file = Loader.getInstance().getFileByName(fileFullName);
/* 30 */       this.location = new Location(file);
/* 31 */       this.location.setStartLine(startPosition.intValue());
/* 32 */       this.location.setEndLine(stopPosition.intValue());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 37 */   public void setPackageId(Integer packageId) { this.currentPackage = Loader.getInstance().getPackage(packageId); }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setNoDecisions(Integer noDecisions) { this.noDecisions = noDecisions.intValue(); }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public void setNoLoops(Integer noLoops) { this.noLoops = noLoops.intValue(); }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public void setNoAnd(Integer noAnd) { this.noAnd = noAnd.intValue(); }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public void setNoOr(Integer noOr) { this.noOr = noOr.intValue(); }
/*    */ 
/*    */ 
/*    */   
/* 57 */   public void setCyclomaticNumber(Integer cyclo) { this.cyclo = cyclo.intValue(); }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public void setNoReturn(Integer noReturn) { this.noReturn = noReturn.intValue(); }
/*    */ 
/*    */ 
/*    */   
/* 65 */   public void setNoCatch(Integer noCatch) { this.noCatch = noCatch.intValue(); }
/*    */ 
/*    */ 
/*    */   
/* 69 */   public void setMaxNesting(Integer maxNesting) { this.maxNesting = maxNesting.intValue(); }
/*    */ 
/*    */ 
/*    */   
/* 73 */   public void setNoStatements(Integer noStatements) { this.noStatements = noStatements.intValue(); }
/*    */ 
/*    */ 
/*    */   
/* 77 */   public void setNoCodeLine(Integer noCodeLine) { this.noCodeLine = noCodeLine.intValue(); }
/*    */ 
/*    */   
/*    */   public void addBody() {
/* 81 */     FunctionBody currentBody = new FunctionBody();
/* 82 */     currentBody.setCodeStripe(new CodeStripe(currentBody));
/*    */     
/* 84 */     currentBody.setLocation(this.location);
/* 85 */     currentBody.setMaxNestingLevel(this.maxNesting);
/* 86 */     currentBody.setNumberOfDecisions(this.noDecisions);
/*    */     
/* 88 */     currentBody.setNumberOfExits(this.noReturn);
/* 89 */     currentBody.setNumberOfLines(this.noCodeLine);
/* 90 */     currentBody.setNumberOfLoops(this.noLoops);
/* 91 */     currentBody.setNumberOfStatements(this.noStatements);
/* 92 */     currentBody.setCyclomaticNumber(this.cyclo);
/* 93 */     Loader.getInstance().addBody(this.id, currentBody);
/* 94 */     Loader.getInstance().addBodyToPackage(currentBody, this.currentPackage);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\DefaultBodyVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */