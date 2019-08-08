/*     */ package classes.lrg.insider.plugins.tools;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*     */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*     */ import lrg.insider.gui.ui.utils.ProgressBar;
/*     */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*     */ import lrg.insider.plugins.filters.memoria.classes.IsInner;
/*     */ import lrg.insider.plugins.tools.OverviewPyramid;
/*     */ import lrg.memoria.core.System;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OverviewPyramid
/*     */   extends AbstractEntityTool
/*     */ {
/*     */   private ProgressBar progress;
/*     */   private System currentSystem;
/*     */   private GroupEntity methodGroup;
/*     */   private GroupEntity globalFunctionGroup;
/*     */   
/*  26 */   public OverviewPyramid() { super("OverviewPyramid", "Building the Overview Pyramid", "system"); }
/*     */ 
/*     */   
/*     */   private void initMembers(AbstractEntityInterface theSystem) {
/*  30 */     this.currentSystem = (System)theSystem;
/*     */     
/*  32 */     this.methodGroup = this.currentSystem.getGroup("method group").applyFilter("model function");
/*  33 */     this.globalFunctionGroup = this.currentSystem.getGroup("global function group").applyFilter("model function");
/*     */   }
/*     */ 
/*     */   
/*     */   private String getColor(double value, double min, double avg, double max) {
/*  38 */     String red = "#FF0000", blue = "#0000FF", green = "#009900";
/*     */     
/*  40 */     double dist_min = Math.abs(value - min), dist_avg = Math.abs(value - avg), dist_max = Math.abs(value - max);
/*     */     
/*  42 */     if (dist_min < dist_avg) return blue; 
/*  43 */     if (dist_avg < dist_max) return green;
/*     */     
/*  45 */     return red;
/*     */   }
/*     */   
/*     */   public void run(AbstractEntityInterface abstractEntityInterface, Object o) {
/*  49 */     if (!(abstractEntityInterface instanceof System))
/*  50 */       return;  initMembers(abstractEntityInterface);
/*  51 */     ArrayList<String> params = (ArrayList)o;
/*  52 */     if (((String)params.get(0)).equals("")) {
/*  53 */       System.out.println(buildPyramid());
/*     */     } else {
/*     */       try {
/*  56 */         PrintStream out_stream = new PrintStream(new FileOutputStream((String)params.get(0)));
/*  57 */         out_stream.print(buildPyramid());
/*  58 */         out_stream.close();
/*  59 */       } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String buildHTMLPyramid(AbstractEntityInterface theSystem) {
/*  65 */     initMembers(theSystem);
/*  66 */     result = "";
/*  67 */     double cyc = getCYCLO(), loc = getLOC(), nom = getNOM().size();
/*  68 */     double noc = getNOC().size(), nop = getNOP().size();
/*  69 */     double fout = getFANOUT(), call = getCALLS();
/*  70 */     double andd = getAVG_NDD(), ahit = getAVG_HIT();
/*     */     
/*  72 */     String anddColor = getColor(andd, 0.2D, 0.41D, 0.6D);
/*  73 */     String ahitColor = getColor(ahit, 0.1D, 0.21D, 0.3D);
/*     */     
/*  75 */     String fout_callColor = getColor(fout / call, 0.57D, 0.63D, 0.69D);
/*  76 */     String call_nomColor = getColor(call / nom, 2.0D, 2.6D, 3.2D);
/*     */     
/*  78 */     String cyc_locColor = getColor(cyc / loc, 0.17D, 0.21D, 0.25D);
/*  79 */     String loc_nomColor = getColor(loc / nom, 6.0D, 9.0D, 13.0D);
/*  80 */     String nom_nocColor = getColor(nom / noc, 4.0D, 7.0D, 9.0D);
/*  81 */     String noc_nopColor = getColor(noc / nop, 6.0D, 15.0D, 24.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     result = String.valueOf(result) + "<table border=0 cellpadding=0 cellspacing=0>";
/*     */     
/*  88 */     result = String.valueOf(result) + "<tr>";
/*  89 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  90 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  91 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  92 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  93 */     result = String.valueOf(result) + "<td bgcolor=#99FF99><b>NDD</b>&nbsp;&nbsp;</td>";
/*     */     
/*  95 */     result = String.valueOf(result) + "<td bgcolor=" + anddColor + "><div align=right><font color=#FFFFFF>  " + round(andd) + "&nbsp; </font></div></td>";
/*  96 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*  97 */     result = String.valueOf(result) + "</tr>";
/*     */     
/*  99 */     result = String.valueOf(result) + "<tr>";
/* 100 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 101 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 102 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 103 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 104 */     result = String.valueOf(result) + "<td bgcolor=#99FF99><b>HIT</b>&nbsp;&nbsp;</td>";
/*     */     
/* 106 */     result = String.valueOf(result) + "<td bgcolor=" + ahitColor + "><div align=right><font color=#FFFFFF>  " + round(ahit) + "&nbsp; </font></div></td>";
/* 107 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 108 */     result = String.valueOf(result) + "</tr>";
/*     */     
/* 110 */     result = String.valueOf(result) + "<tr>";
/* 111 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 112 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 113 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 114 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><font color=#FFFFFF><font bgcolor=" + noc_nopColor + "><b> " + round(noc / nop) + "&nbsp;</b></font></font></td>";
/* 115 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><b>NOP</b>&nbsp;</td>";
/*     */     
/* 117 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><div align=right> " + AbstractDetail.linkToNumber(getNOP()) + " &nbsp;</div></td>";
/* 118 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 119 */     result = String.valueOf(result) + "</tr>";
/*     */     
/* 121 */     result = String.valueOf(result) + "<tr>";
/* 122 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 123 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*     */     
/* 125 */     result = String.valueOf(result) + "<td bgcolor=" + nom_nocColor + "><font color=#FFFFFF>  " + round(nom / noc) + "&nbsp; </font></td>";
/* 126 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><b>NOC</b>&nbsp;&nbsp;</td>";
/* 127 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/*     */     
/* 129 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><div align=right> " + AbstractDetail.linkToNumber(getNOC()) + " &nbsp;</div></td>";
/* 130 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 131 */     result = String.valueOf(result) + "</tr>";
/*     */     
/* 133 */     result = String.valueOf(result) + "<tr>";
/* 134 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/*     */     
/* 136 */     result = String.valueOf(result) + "<td bgcolor=" + loc_nomColor + "><font color=#FFFFFF>  " + round(loc / nom) + "&nbsp; </font></td>";
/* 137 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><b>NOM</b>&nbsp;&nbsp;</td>";
/* 138 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 139 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/*     */     
/* 141 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><div align=right> " + AbstractDetail.linkToNumber(getNOM()) + " &nbsp;</div></td>";
/* 142 */     result = String.valueOf(result) + "<td bgcolor=#FF99FF><div align=right><b>NOM</b>&nbsp;</div></td>";
/*     */     
/* 144 */     result = String.valueOf(result) + "<td bgcolor=" + call_nomColor + "><div align=right><font color=#FFFFFF>  " + round(call / nom) + "&nbsp; </font></div></td>";
/* 145 */     result = String.valueOf(result) + "<td>&nbsp;</td>";
/* 146 */     result = String.valueOf(result) + "</tr>";
/*     */     
/* 148 */     result = String.valueOf(result) + "<tr>";
/*     */     
/* 150 */     result = String.valueOf(result) + "<td bgcolor=" + cyc_locColor + "><font color=#FFFFFF>  " + round(cyc / loc) + "&nbsp; </font></td>";
/* 151 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><b>LOC</b>&nbsp;&nbsp;</td>";
/* 152 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 153 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 154 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/*     */     
/* 156 */     result = String.valueOf(result) + "<td bgcolor=#FF99FF>&nbsp;<b>" + intValue(call) + "</b>&nbsp;</td>";
/* 157 */     result = String.valueOf(result) + "<td bgcolor=#FF99FF><div align=right>&nbsp;<b>CALL</b>&nbsp;</div></td>";
/*     */     
/* 159 */     result = String.valueOf(result) + "<td bgcolor=" + fout_callColor + "><div align=right><font color=#FFFFFF>  " + round(fout / call) + "&nbsp; </font></div></td>";
/* 160 */     result = String.valueOf(result) + "</tr>";
/*     */     
/* 162 */     result = String.valueOf(result) + "<tr>";
/* 163 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><b>CYC</b>&nbsp;&nbsp;</td>";
/* 164 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 165 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 166 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 167 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33>&nbsp;</td>";
/* 168 */     result = String.valueOf(result) + "<td bgcolor=#FFFF33><div align=right><b>" + intValue(cyc) + "</b>&nbsp;</div></td>";
/* 169 */     result = String.valueOf(result) + "<td bgcolor=#FF99FF><b>&nbsp;" + intValue(fout) + "&nbsp;</b></td>";
/* 170 */     result = String.valueOf(result) + "<td bgcolor=#FF99FF>&nbsp;</td>";
/* 171 */     result = String.valueOf(result) + "<td bgcolor=#FF99FF><div align=right>&nbsp;<b>FOUT</b>&nbsp;</div></td>";
/* 172 */     result = String.valueOf(result) + "</tr>";
/*     */     
/* 174 */     return String.valueOf(result) + "</table>";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String buildPyramid() {
/* 180 */     pyramid = new String();
/*     */     
/* 182 */     double cyclo = getCYCLO();
/* 183 */     double loc = getLOC();
/* 184 */     double nom = getNOM().size();
/* 185 */     double noc = getNOC().size();
/* 186 */     double nop = getNOP().size();
/*     */     
/* 188 */     double avgHIT = getAVG_HIT();
/* 189 */     double avgNDD = getAVG_NDD();
/*     */     
/* 191 */     double calls = getCALLS();
/* 192 */     double fanout = getFANOUT();
/*     */     
/* 194 */     pyramid = String.valueOf(pyramid) + "\t\t\t\tANDC\t" + round(avgNDD) + "\n";
/* 195 */     pyramid = String.valueOf(pyramid) + "\t\t\t\tAHH\t" + round(avgHIT) + "\n";
/* 196 */     pyramid = String.valueOf(pyramid) + "\t\t\t" + round(noc / nop) + "\t" + "NOP" + "\t" + nop + "\n";
/* 197 */     pyramid = String.valueOf(pyramid) + "\t\t" + round(nom / noc) + "\t" + "NOC" + "\t\t" + noc + "\n";
/* 198 */     pyramid = String.valueOf(pyramid) + "\t" + round(loc / nom) + "\t" + "NOM" + "\t" + "\t\t" + nom + "\t" + "NOM" + "\t" + round(calls / nom) + "\n";
/* 199 */     pyramid = String.valueOf(pyramid) + round(cyclo / loc) + "\t" + "LOC" + "\t\t\t\t" + loc + "\t" + calls + "\t" + "CALLS" + "\t" + round(fanout / calls) + "\n";
/* 200 */     return String.valueOf(pyramid) + "CYCLO\t\t\t\t\t" + cyclo + "\t" + fanout + "\t\t" + "FANOUT" + "\n";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double getCYCLO() {
/* 207 */     cyclo = ((Double)this.methodGroup.getProperty("CYCLO").aggregate("sum").getValue()).doubleValue();
/* 208 */     return ((Double)this.globalFunctionGroup.getProperty("CYCLO").aggregate("sum").getValue()).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double getLOC() {
/* 214 */     loc = ((Double)this.methodGroup.getProperty("LOC").aggregate("sum").getValue()).doubleValue();
/* 215 */     return ((Double)this.globalFunctionGroup.getProperty("LOC").aggregate("sum").getValue()).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 220 */   private GroupEntity getNOM() { return this.methodGroup.union(this.globalFunctionGroup); }
/*     */ 
/*     */   
/*     */   private GroupEntity getNOC() {
/* 224 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsInner());
/* 225 */     return this.currentSystem.getGroup("class group").applyFilter("model class").applyFilter(notComposedFilteringRule);
/*     */   }
/*     */ 
/*     */   
/* 229 */   private GroupEntity getNOP() { return this.currentSystem.getGroup("package group").applyFilter("model package"); }
/*     */ 
/*     */ 
/*     */   
/* 233 */   private double getAVG_NDD() { return ((Double)this.currentSystem.getProperty("AVG_NDD").getValue()).doubleValue(); }
/*     */ 
/*     */ 
/*     */   
/* 237 */   private double getAVG_HIT() { return ((Double)this.currentSystem.getProperty("AVG_HIT").getValue()).doubleValue(); }
/*     */ 
/*     */ 
/*     */   
/*     */   private double getCALLS() {
/* 242 */     loc = ((Double)this.methodGroup.getProperty("FANOUT").aggregate("sum").getValue()).doubleValue();
/* 243 */     return ((Double)this.globalFunctionGroup.getProperty("FANOUT").aggregate("sum").getValue()).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double getFANOUT() {
/* 249 */     loc = ((Double)this.methodGroup.getProperty("FANOUTCLASS").aggregate("sum").getValue()).doubleValue();
/* 250 */     return ((Double)this.globalFunctionGroup.getProperty("FANOUTCLASS").aggregate("sum").getValue()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   private String round(double x) {
/* 255 */     String s = new String((new StringBuilder(String.valueOf(x))).toString());
/* 256 */     int index = s.indexOf(".");
/* 257 */     if (s.length() - index > 2) {
/* 258 */       return s.substring(0, index + 3);
/*     */     }
/* 260 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 265 */   private String intValue(double x) { return (new StringBuilder(String.valueOf((int)x))).toString(); }
/*     */ 
/*     */ 
/*     */   
/* 269 */   public String getToolName() { return "OverviewPyramid"; }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getParameterList() {
/* 273 */     ArrayList<String> parList = new ArrayList<String>();
/* 274 */     parList.add("File name ");
/* 275 */     return parList;
/*     */   }
/*     */   
/*     */   public ArrayList<String> getParameterExplanations() {
/* 279 */     ArrayList<String> parList = new ArrayList<String>();
/* 280 */     parList.add("Name of the file which will store the overview pyramide");
/* 281 */     return parList;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\OverviewPyramid.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */