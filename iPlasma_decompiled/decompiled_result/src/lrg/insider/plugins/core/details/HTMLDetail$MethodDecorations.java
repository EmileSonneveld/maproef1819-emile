/*     */ package classes.lrg.insider.plugins.core.details;
/*     */ 
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.insider.plugins.core.details.HTMLDetail;
/*     */ import lrg.insider.plugins.filters.memoria.methods.BrainMethod;
/*     */ import lrg.insider.plugins.filters.memoria.methods.ExtensiveCoupling;
/*     */ import lrg.insider.plugins.filters.memoria.methods.FeatureEnvy;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IntensiveCoupling;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*     */ import lrg.insider.plugins.filters.memoria.methods.ShotgunSurgery;
/*     */ import lrg.insider.plugins.filters.memoria.methods.ShouldBeProtected;
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
/*     */   implements HTMLDetail.ListDecorator
/*     */ {
/*     */   public static final String flawColor = "<font color=#800000><font bgcolor=#FFE0E0>";
/*     */   public static final String endFont = "</font></font>";
/*     */   public static final String propertyColor = "<font color=#606020><font bgcolor=#E0E0FF>";
/*     */   
/*     */   public String getBeforeDecoration(AbstractEntityInterface anEntity) {
/* 170 */     if (!(anEntity instanceof Method)) return ""; 
/* 171 */     String c = "<font color=#606020><font bgcolor=#E0E0FF>";
/* 172 */     String d = "</font></font>";
/*     */     
/* 174 */     txt = "<small>";
/* 175 */     txt = String.valueOf(txt) + HTMLDetail.getAccessModeHTML(((Method)anEntity).getAccessMode());
/* 176 */     if ((new IsAccessor()).applyFilter(anEntity)) txt = String.valueOf(txt) + "  " + c + "accessor" + d; 
/* 177 */     return String.valueOf(txt) + "</small>";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAfterDecoration(AbstractEntityInterface anEntity) {
/* 182 */     if (!(anEntity instanceof Method)) return ""; 
/* 183 */     String a = "<font color=#800000><font bgcolor=#FFE0E0>";
/* 184 */     String b = "</font></font>&nbsp;";
/*     */     
/* 186 */     String sik = "";
/* 187 */     double edup = ((Double)anEntity.getProperty("EDUPLINES").getValue()).doubleValue();
/* 188 */     double hdup = ((Double)anEntity.getProperty("HDUPLINES").getValue()).doubleValue();
/* 189 */     double idup = ((Double)anEntity.getProperty("IDUPLINES").getValue()).doubleValue();
/*     */     
/* 191 */     if (edup + hdup + idup > 0.0D) sik = String.valueOf(sik) + a + "Duplication" + b;
/*     */     
/* 193 */     if ((new ShouldBeProtected()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "MakePROTECTED!" + b;
/*     */     
/* 195 */     if ((new BrainMethod()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "BrainMethod" + b; 
/* 196 */     if ((new FeatureEnvy()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "FeatureEnvy" + b; 
/* 197 */     if ((new IntensiveCoupling()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "IntensiveCoupling" + b; 
/* 198 */     if ((new ExtensiveCoupling()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "ExtensiveCoupling" + b; 
/* 199 */     if ((new ShotgunSurgery()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "ShotgunSurgery" + b;
/*     */     
/* 201 */     return sik;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\details\HTMLDetail$MethodDecorations.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */