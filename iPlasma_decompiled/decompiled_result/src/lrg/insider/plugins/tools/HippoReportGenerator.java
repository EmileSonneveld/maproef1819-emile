/*     */ package classes.lrg.insider.plugins.tools;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*     */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.System;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HippoReportGenerator
/*     */   extends AbstractEntityTool
/*     */ {
/*  20 */   public HippoReportGenerator() { super("HippoReportGenerator", "ReportGenerator", "system"); }
/*     */ 
/*     */ 
/*     */   
/*  24 */   public String getToolName() { return "ReportGenerator"; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run(AbstractEntityInterface abstractEntityInterface, Object o) {
/*  29 */     if (!(abstractEntityInterface instanceof System))
/*  30 */       return;  System system = (System)abstractEntityInterface;
/*  31 */     ArrayList<String> params = (ArrayList)o;
/*  32 */     String prefix = (String)params.get(0);
/*     */ 
/*     */     
/*  35 */     GroupEntity allClassGroup = system.getGroup("class group").applyFilter("model class");
/*  36 */     GroupEntity allMethodGroup = system.getGroup("method group").applyFilter("model function");
/*     */ 
/*     */     
/*  39 */     GroupEntity classGroup = allClassGroup.applyFilter(new FilteringRule("Name", "starts as", "class", prefix));
/*  40 */     GroupEntity methodGroup = allMethodGroup.applyFilter(new FilteringRule("scope name", "starts as", "method", prefix));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     GroupEntity godClasses;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  51 */     int noGodClasses = (godClasses = classGroup.applyFilter("God Class")).size(); GroupEntity brainClasses;
/*  52 */     int noBrainClasses = (brainClasses = classGroup.applyFilter("Brain Class")).size(); GroupEntity shotgunMethods;
/*  53 */     int noShutgunSurgery = (shotgunMethods = methodGroup.applyFilter("Shotgun Surgery")).size(); GroupEntity intensiveMethods;
/*  54 */     int noIntensive = (intensiveMethods = methodGroup.applyFilter("Intensive Coupling")).size(); GroupEntity extensiveMethods;
/*  55 */     int noExtensive = (extensiveMethods = methodGroup.applyFilter("Extensive Coupling")).size(); GroupEntity traditionClasses;
/*  56 */     int noTraditionBreakers = (traditionClasses = classGroup.applyFilter("Tradition Breaker")).size(); GroupEntity refusedClasses;
/*  57 */     int noRefusedBequest = (refusedClasses = classGroup.applyFilter("Refused Parent Bequest 2")).size();
/*     */     
/*  59 */     String text = "";
/*     */     
/*  61 */     text = String.valueOf(text) + "GodClasses\n";
/*  62 */     for (int i = 0; i < godClasses.size(); i++) {
/*  63 */       AbstractEntityInterface tmp = godClasses.getElementAt(i);
/*  64 */       text = String.valueOf(text) + tmp.getName() + "\t" + tmp.getProperty("ATFD") + "\t" + tmp.getProperty("WMC") + "\t" + tmp.getProperty("TCC") + "\n";
/*     */     } 
/*     */     
/*  67 */     text = String.valueOf(text) + "BrainClasses\n";
/*  68 */     for (int i = 0; i < brainClasses.size(); i++) {
/*  69 */       AbstractEntityInterface tmp = brainClasses.getElementAt(i);
/*  70 */       text = String.valueOf(text) + tmp.getName() + "\t" + tmp.getProperty("WMC") + "\n";
/*     */     } 
/*     */     
/*  73 */     text = String.valueOf(text) + "ShutgunSurgery\n";
/*  74 */     for (int i = 0; i < shotgunMethods.size(); i++) {
/*  75 */       AbstractEntityInterface tmp = shotgunMethods.getElementAt(i);
/*  76 */       text = String.valueOf(text) + ((Function)tmp).getScope().getName() + "::" + tmp.getName() + "\t" + tmp.getProperty("CM") + "\t" + tmp.getProperty("CC") + "\n";
/*     */     } 
/*     */     
/*  79 */     text = String.valueOf(text) + "IntensiveCoupling\n";
/*  80 */     for (int i = 0; i < intensiveMethods.size(); i++) {
/*  81 */       AbstractEntityInterface tmp = intensiveMethods.getElementAt(i);
/*  82 */       text = String.valueOf(text) + ((Function)tmp).getScope().getName() + "::" + tmp.getName() + "\t" + tmp.getProperty("CINT") + "\t" + tmp.getProperty("CDISP") + "\t" + tmp.getProperty("MAXNESTING") + "\n";
/*     */     } 
/*     */     
/*  85 */     text = String.valueOf(text) + "ExtensiveCoupling\n";
/*  86 */     for (int i = 0; i < extensiveMethods.size(); i++) {
/*  87 */       AbstractEntityInterface tmp = extensiveMethods.getElementAt(i);
/*  88 */       text = String.valueOf(text) + ((Function)tmp).getScope().getName() + "::" + tmp.getName() + "\t" + tmp.getProperty("CINT") + "\t" + tmp.getProperty("CDISP") + "\t" + tmp.getProperty("MAXNESTING") + "\n";
/*     */     } 
/*     */     
/*  91 */     text = String.valueOf(text) + "TraditionBrakers\n";
/*  92 */     for (int i = 0; i < traditionClasses.size(); i++) {
/*  93 */       AbstractEntityInterface tmp = traditionClasses.getElementAt(i);
/*  94 */       text = String.valueOf(text) + tmp.getName() + "\n";
/*     */     } 
/*     */     
/*  97 */     text = String.valueOf(text) + "RefusedBequest\n";
/*  98 */     for (i = 0; i < refusedClasses.size(); i++) {
/*  99 */       AbstractEntityInterface tmp = refusedClasses.getElementAt(i);
/* 100 */       text = String.valueOf(text) + tmp.getName() + "\n";
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 105 */       PrintStream out_stream = new PrintStream(new FileOutputStream(prefix));
/* 106 */       out_stream.print(String.valueOf(noGodClasses) + "\t" + noBrainClasses + "\t" + noShutgunSurgery + "\t" + noIntensive + "\t" + noExtensive + "\t" + noTraditionBreakers + "\t" + noRefusedBequest + "\n\n");
/* 107 */       out_stream.print(text);
/* 108 */       out_stream.close();
/* 109 */     } catch (Exception i) {
/* 110 */       Exception ex; System.out.println("Error writing the result!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayList<String> getParameterList() {
/* 115 */     ArrayList<String> parList = new ArrayList<String>();
/* 116 */     parList.add("Prefix");
/* 117 */     return parList;
/*     */   }
/*     */   
/*     */   public ArrayList<String> getParameterExplanations() {
/* 121 */     ArrayList<String> parList = new ArrayList<String>();
/* 122 */     parList.add("Prefix");
/* 123 */     return parList;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\HippoReportGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */