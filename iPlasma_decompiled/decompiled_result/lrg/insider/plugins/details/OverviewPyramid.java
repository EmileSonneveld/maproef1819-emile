/*     */ package classes.lrg.insider.plugins.details;
/*     */ 
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.entities.ResultEntity;
/*     */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*     */ import lrg.insider.plugins.core.details.HTMLDetail;
/*     */ import lrg.insider.plugins.details.OverviewPyramid;
/*     */ import lrg.insider.plugins.filters.memoria.classes.IsInner;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OverviewPyramid
/*     */ {
/*     */   private AbstractEntityInterface currentSystem;
/*     */   private GroupEntity methodGroup;
/*     */   private GroupEntity globalFunctionGroup;
/*  22 */   private final String RED = "#CC0000";
/*  23 */   private final String BLUE = "#0000CC";
/*  24 */   private final String GREEN = "#006600";
/*     */   
/*     */   String anddColor;
/*     */   
/*     */   String ahitColor;
/*     */   
/*     */   String fout_callColor;
/*     */   
/*     */   String call_nomColor;
/*     */   
/*     */   String cyc_locColor;
/*     */   
/*     */   String loc_nomColor;
/*     */   String nom_nocColor;
/*     */   String noc_nopColor;
/*     */   
/*     */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/*  41 */     String text = "<h1>System Overview for " + anEntity.getName() + "</h1><hr><br>";
/*     */     
/*  43 */     initMembers(anEntity);
/*  44 */     text = String.valueOf(text) + "<table><tr><td valign=\"top\">";
/*  45 */     text = String.valueOf(text) + buildHTMLPyramid(anEntity);
/*  46 */     text = String.valueOf(text) + "</td><td valign=\"top\">";
/*  47 */     text = String.valueOf(text) + "</td></tr></table>";
/*     */ 
/*     */     
/*  50 */     return new ResultEntity(text);
/*     */   }
/*     */   
/*     */   private void initMembers(AbstractEntityInterface theSystem) {
/*  54 */     this.currentSystem = theSystem;
/*  55 */     this.methodGroup = this.currentSystem.getGroup("method group").applyFilter("model function");
/*  56 */     this.globalFunctionGroup = this.currentSystem.getGroup("global function group").applyFilter("model function");
/*     */   }
/*     */   
/*     */   public String buildHTMLPyramid(AbstractEntityInterface theSystem) {
/*  60 */     initMembers(theSystem);
/*  61 */     result = "";
/*  62 */     double cyc = getCYCLO(), loc = getLOC(), nom = getNOM().size();
/*  63 */     double noc = getNOC().size(), nop = getNOP().size();
/*  64 */     double fout = getFANOUT(), call = getCALLS();
/*  65 */     double andd = getAVG_NDD(), ahit = getAVG_HIT();
/*     */     
/*  67 */     this.anddColor = getColor(andd, 0.2D, 0.41D, 0.6D);
/*  68 */     this.ahitColor = getColor(ahit, 0.1D, 0.21D, 0.3D);
/*     */     
/*  70 */     this.fout_callColor = getColor(fout / call, 0.57D, 0.63D, 0.69D);
/*  71 */     this.call_nomColor = getColor(call / nom, 2.0D, 2.6D, 3.2D);
/*     */     
/*  73 */     this.cyc_locColor = getColor(cyc / loc, 0.17D, 0.21D, 0.25D);
/*  74 */     this.loc_nomColor = getColor(loc / nom, 6.0D, 9.0D, 13.0D);
/*  75 */     this.nom_nocColor = getColor(nom / noc, 4.0D, 7.0D, 9.0D);
/*  76 */     this.noc_nopColor = getColor(noc / nop, 6.0D, 15.0D, 24.0D);
/*     */     
/*  78 */     result = String.valueOf(result) + "<table border=0 cellpadding=0 cellspacing=0>";
/*     */     
/*  80 */     result = String.valueOf(result) + "<tr>";
/*  81 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  82 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  83 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  84 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  85 */     result = String.valueOf(result) + "<td bgcolor=#99FF99> NDD &nbsp;&nbsp;</td>";
/*  86 */     result = String.valueOf(result) + "<td bgcolor=" + this.anddColor + "><div align=right><font color=#FFFFFF>  " + round(andd) + "&nbsp; </font></div></td>";
/*  87 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  88 */     result = String.valueOf(result) + "</tr>";
/*     */     
/*  90 */     result = String.valueOf(result) + "<tr>";
/*  91 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  92 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  93 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  94 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  95 */     result = String.valueOf(result) + "<td bgcolor=#99FF99> HIT &nbsp;&nbsp;</td>";
/*  96 */     result = String.valueOf(result) + "<td bgcolor=" + this.ahitColor + "><div align=right><font color=#FFFFFF>  " + round(ahit) + "&nbsp; </font></div></td>";
/*  97 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  98 */     result = String.valueOf(result) + "</tr>";
/*     */     
/* 100 */     result = String.valueOf(result) + "<tr>";
/* 101 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 102 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 103 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 104 */     result = String.valueOf(result) + "<td bgcolor=" + this.noc_nopColor + "><font color=#FFFFFF>  " + round(noc / nop) + "&nbsp; </font></td>";
/* 105 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33> NOP &nbsp;</td>";
/* 106 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><div align=right> " + HTMLDetail.linkToNumber(getNOP()) + " &nbsp;</div></td>";
/* 107 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 108 */     result = String.valueOf(result) + "</tr>";
/*     */     
/* 110 */     result = String.valueOf(result) + "<tr>";
/* 111 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 112 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 113 */     result = String.valueOf(result) + "<td bgcolor=" + this.nom_nocColor + "><font color=#FFFFFF>  " + round(nom / noc) + "&nbsp; </font></td>";
/* 114 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33> NOC &nbsp;&nbsp;</td>";
/* 115 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 116 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><div align=right> " + HTMLDetail.linkToNumber(getNOC()) + " &nbsp;</div></td>";
/* 117 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 118 */     result = String.valueOf(result) + "</tr>";
/*     */     
/* 120 */     result = String.valueOf(result) + "<tr>";
/* 121 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 122 */     result = String.valueOf(result) + "<td bgcolor=" + this.loc_nomColor + "><font color=#FFFFFF>  " + round(loc / nom) + "&nbsp; </font></td>";
/* 123 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33> NOM &nbsp;&nbsp;</td>";
/* 124 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 125 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 126 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><div align=right> " + HTMLDetail.linkToNumber(getNOM()) + " &nbsp;</div></td>";
/* 127 */     result = String.valueOf(result) + "<td bgcolor=#99CCFF><div align=right> NOM &nbsp;</div></td>";
/* 128 */     result = String.valueOf(result) + "<td bgcolor=" + this.call_nomColor + "><div align=right><font color=#FFFFFF>  " + round(call / nom) + "&nbsp; </font></div></td>";
/* 129 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 130 */     result = String.valueOf(result) + "</tr>";
/*     */     
/* 132 */     result = String.valueOf(result) + "<tr>";
/* 133 */     result = String.valueOf(result) + "<td bgcolor=" + this.cyc_locColor + "><font color=#FFFFFF>  " + round(cyc / loc) + "&nbsp; </font></td>";
/* 134 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33> LOC &nbsp;&nbsp;</td>";
/* 135 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 136 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 137 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 138 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><div align=right> " + intValue(loc) + " &nbsp;</div></td>";
/* 139 */     result = String.valueOf(result) + "<td bgcolor=#99CCFF>&nbsp;" + intValue(call) + " &nbsp;</td>";
/* 140 */     result = String.valueOf(result) + "<td bgcolor=#99CCFF><div align=right>&nbsp; CALL &nbsp;</div></td>";
/* 141 */     result = String.valueOf(result) + "<td bgcolor=" + this.fout_callColor + "><div align=right><font color=#FFFFFF>  " + round(fout / call) + "&nbsp; </font></div></td>";
/* 142 */     result = String.valueOf(result) + "</tr>";
/*     */     
/* 144 */     result = String.valueOf(result) + "<tr>";
/* 145 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33> CYCLO &nbsp;&nbsp;</td>";
/* 146 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 147 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 148 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 149 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 150 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><div align=right> " + intValue(cyc) + " &nbsp;</div></td>";
/* 151 */     result = String.valueOf(result) + "<td bgcolor=#99CCFF>&nbsp;" + intValue(fout) + "&nbsp; </td>";
/* 152 */     result = String.valueOf(result) + "<td bgcolor=#99CCFF>&nbsp;</td>";
/* 153 */     result = String.valueOf(result) + "<td bgcolor=#99CCFF><div align=right>&nbsp; FOUT &nbsp;</div></td>";
/* 154 */     result = String.valueOf(result) + "</tr>";
/* 155 */     result = String.valueOf(result) + "</table>";
/*     */     
/* 157 */     return String.valueOf(result) + writeInterpretation(theSystem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String writeInterpretation(AbstractEntityInterface entity) {
/* 165 */     result = "<font face=\"arial\" size=2> <h3>Interpretation of the Overview Pyramid for module <font COLOR=\"#FF6600\">" + entity.getName() + "</font></h3>";
/*     */     
/* 167 */     result = String.valueOf(result) + "<p><b>Class Hierarchies</b> tend to be " + writeClassHierarchyInterpretation() + "</p>";
/* 168 */     result = String.valueOf(result) + "<p><b>Classes </b> tend to:</p>";
/* 169 */     result = String.valueOf(result) + "<ul>";
/* 170 */     result = String.valueOf(result) + writeClassInterpretation();
/* 171 */     result = String.valueOf(result) + "</ul>";
/* 172 */     result = String.valueOf(result) + "<p><b>Methods</b> tend to:</p>";
/* 173 */     result = String.valueOf(result) + "<ul>";
/* 174 */     result = String.valueOf(result) + writeMethodInterpretation();
/* 175 */     result = String.valueOf(result) + "</ul>";
/* 176 */     return String.valueOf(result) + "<p> &nbsp;";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String writeClassInterpretation() {
/* 182 */     String result = "<li>";
/* 183 */     if (this.nom_nocColor.equals("#CC0000")) { result = String.valueOf(result) + "be rather" + formatWord("large", "#CC0000") + "(i.e. they define many methods);"; }
/* 184 */     else if (this.nom_nocColor.equals("#0000CC")) { result = String.valueOf(result) + "be rather" + formatWord("small", "#0000CC") + "(i.e. have only a few methods);"; }
/* 185 */     else { result = String.valueOf(result) + "contain an" + formatWord("average", "#006600") + "number of methods;"; }
/*     */     
/* 187 */     result = String.valueOf(result) + "<li>be organized in ";
/* 188 */     if (this.noc_nopColor.equals("#CC0000")) { result = String.valueOf(result) + "rather" + formatWord("coarse-grained packages", "#CC0000") + "(i.e. many classes per package);"; }
/* 189 */     else if (this.noc_nopColor.equals("#0000CC")) { result = String.valueOf(result) + "rather" + formatWord("fine-grained packages", "#0000CC") + "(i.e. few classes per package);"; }
/* 190 */     else { result = String.valueOf(result) + formatWord("average-sized packages", "#006600") + ";"; }
/*     */     
/* 192 */     return result;
/*     */   }
/*     */   
/*     */   private String writeClassHierarchyInterpretation() {
/* 196 */     String result = "";
/* 197 */     String explanation = "(i.e. inheritance trees tend to have ";
/*     */     
/* 199 */     if (this.anddColor.equals("#0000CC") && this.ahitColor.equals("#0000CC")) return "rather <b>sparse</b> (i.e. there are mostly standalone classes and few inheritance relations)";
/*     */     
/* 201 */     if (this.ahitColor.equals("#0000CC")) {
/* 202 */       result = String.valueOf(result) + formatWord("shallow", "#0000CC");
/* 203 */       explanation = String.valueOf(explanation) + " only few depth-level(s) and ";
/*     */     }
/* 205 */     else if (this.ahitColor.equals("#CC0000")) {
/* 206 */       result = String.valueOf(result) + formatWord("tall", "#CC0000");
/* 207 */       explanation = String.valueOf(explanation) + " many depth-levels and ";
/*     */     } else {
/*     */       
/* 210 */       result = String.valueOf(result) + "of " + formatWord("average height", "#006600");
/*     */     } 
/*     */ 
/*     */     
/* 214 */     result = String.valueOf(result) + " and ";
/*     */ 
/*     */     
/* 217 */     if (this.anddColor.equals("#CC0000")) {
/* 218 */       result = String.valueOf(result) + formatWord("wide", "#CC0000");
/* 219 */       explanation = String.valueOf(explanation) + "base-classes with many directly derived sub-classes)";
/*     */     }
/* 221 */     else if (this.anddColor.equals("#0000CC")) {
/* 222 */       result = String.valueOf(result) + formatWord("narrow", "#0000CC");
/* 223 */       explanation = String.valueOf(explanation) + "base-classes with few directly derived sub-classes)";
/*     */     } else {
/*     */       
/* 226 */       result = String.valueOf(result) + "of " + formatWord("average width", "#006600");
/* 227 */       explanation = String.valueOf(explanation) + "base-classes with several directly derived sub-classes)";
/*     */     } 
/*     */     
/* 230 */     return String.valueOf(result) + explanation;
/*     */   }
/*     */   
/*     */   private String writeMethodInterpretation() {
/* 234 */     String result = "<li>tend to ";
/* 235 */     if (this.loc_nomColor.equals("#CC0000")) { result = String.valueOf(result) + "be rather" + formatWord("long", "#CC0000"); }
/* 236 */     else if (this.loc_nomColor.equals("#0000CC")) { result = String.valueOf(result) + "be rather" + formatWord("short", "#0000CC"); }
/* 237 */     else { result = String.valueOf(result) + "be " + formatWord("average", "#006600") + "in length "; }
/*     */     
/* 239 */     if ((this.loc_nomColor.equals("#CC0000") && this.cyc_locColor.equals("#0000CC")) || (
/* 240 */       this.loc_nomColor.equals("#0000CC") && this.cyc_locColor.equals("#CC0000")))
/* 241 */     { result = String.valueOf(result) + " yet "; }
/* 242 */     else { result = String.valueOf(result) + " and "; }
/*     */     
/* 244 */     if (this.cyc_locColor.equals("#CC0000")) { result = String.valueOf(result) + "having a rather " + formatWord("complex logic", "#CC0000") + "(i.e. many conditional branches);"; }
/* 245 */     else if (this.cyc_locColor.equals("#0000CC")) { result = String.valueOf(result) + "having a rather " + formatWord("simple logic", "#0000CC") + "(i.e. few conditional branches);"; }
/* 246 */     else { result = String.valueOf(result) + "having an " + formatWord("average logical complexity", "#006600") + ";"; }
/*     */     
/* 248 */     result = String.valueOf(result) + "<li> tend to call ";
/* 249 */     if (this.call_nomColor.equals("#CC0000")) { result = String.valueOf(result) + formatWord("many methods", "#CC0000") + " (high coupling intensity) "; }
/* 250 */     else if (this.call_nomColor.equals("#0000CC")) { result = String.valueOf(result) + formatWord("few methods", "#0000CC") + " (low coupling intensity)"; }
/* 251 */     else { result = String.valueOf(result) + "an " + formatWord("several methods", "#006600"); }
/*     */     
/* 253 */     result = String.valueOf(result) + " from ";
/*     */     
/* 255 */     if (this.fout_callColor.equals("#CC0000")) { result = String.valueOf(result) + formatWord("many other classes", "#CC0000") + " (high coupling dispersion); "; }
/* 256 */     else if (this.fout_callColor.equals("#0000CC")) { result = String.valueOf(result) + formatWord("few other classes", "#0000CC") + "(low coupling dispersion);"; }
/* 257 */     else { result = String.valueOf(result) + "an " + formatWord("several other classes", "#006600") + ";"; }
/*     */ 
/*     */     
/* 260 */     return result;
/*     */   }
/*     */   
/*     */   private String formatWord(String word, String color) {
/* 264 */     result = "&nbsp;&nbsp; <b><font COLOR=\"" + color + "\">";
/* 265 */     return String.valueOf(result) + word + "&nbsp;&nbsp;</b></font> ";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double getCYCLO() {
/* 271 */     cyclo = ((Double)this.methodGroup.getProperty("CYCLO").aggregate("sum").getValue()).doubleValue();
/* 272 */     return ((Double)this.globalFunctionGroup.getProperty("CYCLO").aggregate("sum").getValue()).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double getLOC() {
/* 278 */     loc = ((Double)this.methodGroup.getProperty("LOC").aggregate("sum").getValue()).doubleValue();
/* 279 */     return ((Double)this.globalFunctionGroup.getProperty("LOC").aggregate("sum").getValue()).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 284 */   private GroupEntity getNOM() { return this.methodGroup.union(this.globalFunctionGroup); }
/*     */ 
/*     */   
/*     */   private GroupEntity getNOC() {
/* 288 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsInner());
/* 289 */     return this.currentSystem.getGroup("class group").applyFilter("model class").applyFilter(notComposedFilteringRule);
/*     */   }
/*     */ 
/*     */   
/* 293 */   private GroupEntity getNOP() { return this.currentSystem.getGroup("package group").applyFilter("model package"); }
/*     */ 
/*     */ 
/*     */   
/* 297 */   private double getAVG_NDD() { return ((Double)this.currentSystem.getProperty("AVG_HIT").getValue()).doubleValue(); }
/*     */ 
/*     */ 
/*     */   
/* 301 */   private double getAVG_HIT() { return ((Double)this.currentSystem.getProperty("AVG_NDD").getValue()).doubleValue(); }
/*     */ 
/*     */ 
/*     */   
/*     */   private double getCALLS() {
/* 306 */     loc = ((Double)this.methodGroup.getProperty("FANOUT").aggregate("sum").getValue()).doubleValue();
/* 307 */     return ((Double)this.globalFunctionGroup.getProperty("FANOUT").aggregate("sum").getValue()).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double getFANOUT() {
/* 313 */     loc = ((Double)this.methodGroup.getProperty("FANOUTCLASS").aggregate("sum").getValue()).doubleValue();
/* 314 */     return ((Double)this.globalFunctionGroup.getProperty("FANOUTCLASS").aggregate("sum").getValue()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   private String round(double x) {
/* 319 */     String s = new String((new StringBuilder(String.valueOf(x))).toString());
/* 320 */     int index = s.indexOf(".");
/* 321 */     if (s.length() - index > 2) {
/* 322 */       return s.substring(0, index + 3);
/*     */     }
/* 324 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 329 */   private String intValue(double x) { return (new StringBuilder(String.valueOf((int)x))).toString(); }
/*     */ 
/*     */ 
/*     */   
/*     */   private String getColor(double value, double min, double avg, double max) {
/* 334 */     double dist_min = Math.abs(value - min), dist_avg = Math.abs(value - avg), dist_max = Math.abs(value - max);
/*     */     
/* 336 */     if (dist_min < dist_avg) return "#0000CC"; 
/* 337 */     if (dist_avg < dist_max) return "#006600"; 
/* 338 */     return "#CC0000";
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\details\OverviewPyramid.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */