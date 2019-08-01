/*    */ package classes.lrg.insider.plugins.filters.memoria.packages;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.insider.plugins.filters.memoria.packages.SDPViolation;
/*    */ 
/*    */ public class SDPViolation
/*    */   extends FilteringRule
/*    */ {
/* 13 */   public SDPViolation() { super(new Descriptor("SDP Violation", "package")); }
/*    */ 
/*    */ 
/*    */   
/*    */   public static ArrayList<AbstractEntityInterface> SDPBreakers(AbstractEntityInterface aPackage) {
/* 18 */     ArrayList<AbstractEntityInterface> usedPacakges = ((GroupEntity)aPackage.getGroup("fanout class group").belongsTo("package")).distinct().getElements();
/* 19 */     double referenceIFValue = ((Double)aPackage.getProperty("IF").getValue()).doubleValue();
/* 20 */     ArrayList<AbstractEntityInterface> sdpBreakers = new ArrayList<AbstractEntityInterface>();
/*    */     
/* 22 */     for (AbstractEntityInterface crtPackage : usedPacakges) {
/* 23 */       double crtIFValue = ((Double)crtPackage.getProperty("IF").getValue()).doubleValue();
/* 24 */       if (referenceIFValue < crtIFValue * 0.9D) sdpBreakers.add(crtPackage); 
/*    */     } 
/* 26 */     return sdpBreakers;
/*    */   }
/*    */ 
/*    */   
/* 30 */   public boolean applyFilter(AbstractEntityInterface aPackage) { return (SDPBreakers(aPackage).size() > 0); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\packages\SDPViolation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */