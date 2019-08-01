/*     */ package classes.lrg.insider.plugins.core.properties;
/*     */ 
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*     */ import lrg.insider.plugins.filters.memoria.methods.BrainMethod;
/*     */ import lrg.insider.plugins.filters.memoria.methods.ExtensiveCoupling;
/*     */ import lrg.insider.plugins.filters.memoria.methods.FeatureEnvy;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IntensiveCoupling;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*     */ import lrg.insider.plugins.filters.memoria.methods.ShotgunSurgery;
/*     */ import lrg.memoria.core.Method;
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
/*     */ public class MethodDecorations
/*     */   implements AbstractDetail.ListDecorator
/*     */ {
/*     */   public static final String flawColor = "<font color=#800000><font bgcolor=#FFE0E0>";
/*     */   public static final String endFont = "</font></font>";
/*     */   public static final String propertyColor = "<font color=#606020><font bgcolor=#E0E0FF>";
/*     */   
/*     */   public String getBeforeDecoration(AbstractEntityInterface anEntity) {
/* 125 */     if (!(anEntity instanceof Method)) return ""; 
/* 126 */     String c = "<font color=#606020><font bgcolor=#E0E0FF>";
/* 127 */     String d = "</font></font>";
/*     */     
/* 129 */     txt = "<small>";
/* 130 */     txt = String.valueOf(txt) + AbstractDetail.getAccessModeHTML(((Method)anEntity).getAccessMode());
/* 131 */     if ((new IsAccessor()).applyFilter(anEntity)) txt = String.valueOf(txt) + "  " + c + "accessor" + d; 
/* 132 */     return String.valueOf(txt) + "</small>";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAfterDecoration(AbstractEntityInterface anEntity) {
/* 137 */     if (!(anEntity instanceof Method)) return ""; 
/* 138 */     String a = "<font color=#800000><font bgcolor=#FFE0E0>";
/* 139 */     String b = "</font></font>&nbsp;";
/*     */     
/* 141 */     String sik = "";
/* 142 */     double edup = ((Double)anEntity.getProperty("EDUPLINES").getValue()).doubleValue();
/* 143 */     double hdup = ((Double)anEntity.getProperty("HDUPLINES").getValue()).doubleValue();
/* 144 */     double idup = ((Double)anEntity.getProperty("IDUPLINES").getValue()).doubleValue();
/*     */     
/* 146 */     if (edup + hdup + idup > 0.0D) sik = String.valueOf(sik) + a + "Duplication" + b; 
/* 147 */     if ((new BrainMethod()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "BrainMethod" + b; 
/* 148 */     if ((new FeatureEnvy()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "FeatureEnvy" + b; 
/* 149 */     if ((new IntensiveCoupling()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "IntensiveCoupling" + b; 
/* 150 */     if ((new ExtensiveCoupling()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "ExtensiveCoupling" + b; 
/* 151 */     if ((new ShotgunSurgery()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "ShotgunSurgery" + b;
/*     */     
/* 153 */     return sik;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\AbstractDetail$MethodDecorations.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */