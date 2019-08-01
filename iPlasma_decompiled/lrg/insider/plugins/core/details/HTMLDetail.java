/*     */ package classes.lrg.insider.plugins.core.details;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.plugins.details.AbstractDetail;
/*     */ import lrg.common.metamodel.MetaModel;
/*     */ import lrg.insider.plugins.core.details.HTMLDetail;
/*     */ import lrg.insider.plugins.filters.memoria.classes.BrainClass;
/*     */ import lrg.insider.plugins.filters.memoria.classes.DataClass;
/*     */ import lrg.insider.plugins.filters.memoria.classes.FutileHierarchy;
/*     */ import lrg.insider.plugins.filters.memoria.classes.GodClass;
/*     */ import lrg.insider.plugins.filters.memoria.classes.RefusedParentBequest;
/*     */ import lrg.insider.plugins.filters.memoria.classes.TraditionBreaker;
/*     */ import lrg.insider.plugins.filters.memoria.methods.BrainMethod;
/*     */ import lrg.insider.plugins.filters.memoria.methods.FeatureEnvy;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IntensiveCoupling;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class HTMLDetail
/*     */   extends AbstractDetail
/*     */ {
/*  32 */   private static String htmlAble(String s) { return s.replaceAll(">", "&gt;").replaceAll("<", "&lt;"); }
/*     */ 
/*     */ 
/*     */   
/*  36 */   public HTMLDetail(String name, String longName, String entityType) { super(name, longName, entityType); }
/*     */ 
/*     */ 
/*     */   
/*  40 */   public HTMLDetail(String name, String longName, String[] entityTypes) { super(name, longName, entityTypes); }
/*     */ 
/*     */   
/*     */   public static String linkToNumber(GroupEntity anEntity) {
/*  44 */     if (anEntity.size() == 0) return "0"; 
/*  45 */     MetaModel.instance().addGroupToAddressMap(anEntity);
/*  46 */     return "<a href=\"" + anEntity.getProperty("Address") + "\" style=\"text-decoration:none\">" + anEntity.size() + "</a>";
/*     */   }
/*     */   
/*     */   public static String classFlawsDecorations(AbstractEntityInterface anEntity) {
/*  50 */     String a = "<font color=#800000><font bgcolor=#FFE0E0>";
/*  51 */     String b = "</font></font>&nbsp;";
/*     */     
/*  53 */     String sik = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     if ((new BrainClass()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "BrainClass" + b + "   "; 
/*  61 */     if ((new DataClass()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "DataClass" + b + "   "; 
/*  62 */     if ((new GodClass()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "GodClass" + b + "   "; 
/*  63 */     if ((new TraditionBreaker()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "TraditionBreaker" + b + "   "; 
/*  64 */     if ((new RefusedParentBequest()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "RefusedParentBequest" + b + "   "; 
/*  65 */     if ((new FutileHierarchy()).applyFilter(anEntity)) sik = String.valueOf(sik) + a + "FutileHierarchy" + b;
/*     */     
/*  67 */     int brainmethods = 0;
/*  68 */     int fenvy = 0;
/*  69 */     int intcoupl = 0;
/*     */     
/*  71 */     ArrayList<AbstractEntityInterface> methods = anEntity.contains("method group").getElements();
/*     */     
/*  73 */     for (AbstractEntityInterface meth : methods) {
/*  74 */       if ((new FeatureEnvy()).applyFilter(meth)) fenvy++; 
/*  75 */       if ((new BrainMethod()).applyFilter(meth)) brainmethods++; 
/*  76 */       if ((new IntensiveCoupling()).applyFilter(meth)) intcoupl++;
/*     */     
/*     */     } 
/*  79 */     if (fenvy > 0) sik = String.valueOf(sik) + a + fenvy + " FeatureEnvy" + b; 
/*  80 */     if (brainmethods > 0) sik = String.valueOf(sik) + a + brainmethods + " BrainMethod" + b; 
/*  81 */     if (intcoupl > 0) sik = String.valueOf(sik) + a + intcoupl + " IntensiveCoupling" + b;
/*     */ 
/*     */     
/*  84 */     return sik;
/*     */   }
/*     */   public static String linkTo(AbstractEntityInterface anEntity) {
/*  87 */     return 
/*  88 */       "<a href=\"" + anEntity.getProperty("Address") + "\" " + 
/*  89 */       "style=\"text-decoration:none\">" + 
/*  90 */       htmlAble(anEntity.getProperty("Name")) + 
/*  91 */       "</a>";
/*     */   }
/*     */ 
/*     */   
/*  95 */   protected String linkTo(String name, String address) { return "<a href=\"" + address + "\" style=\"text-decoration:none\">" + htmlAble(name) + "</a>"; }
/*     */ 
/*     */   
/*     */   protected String linkTo(String address) {
/*  99 */     AbstractEntityInterface anEntity = MetaModel.instance().findEntityByAddress(address);
/*     */     
/* 101 */     if (anEntity != null) {
/* 102 */       return linkTo(anEntity);
/*     */     }
/* 104 */     return address;
/*     */   }
/*     */   
/*     */   protected String bulletedLinkList(Collection listOfEntities, ListDecorator ld) {
/* 108 */     if (listOfEntities.size() < 1) return ""; 
/* 109 */     text = "<ul>";
/* 110 */     Iterator it = listOfEntities.iterator();
/* 111 */     while (it.hasNext()) {
/* 112 */       AbstractEntityInterface theEntity = (AbstractEntityInterface)it.next();
/* 113 */       text = String.valueOf(text) + "<li>" + ((ld != null) ? ld.getBeforeDecoration(theEntity) : "") + 
/* 114 */         " " + linkTo(theEntity) + " " + (
/* 115 */         (ld != null) ? ld.getAfterDecoration(theEntity) : "") + "</li>";
/*     */     } 
/* 117 */     return String.valueOf(text) + "</ul>";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 122 */   protected String bulletedLinkList(Collection listOfEntities) { return bulletedLinkList(listOfEntities, null); }
/*     */ 
/*     */   
/*     */   protected String bulletedList(ArrayList listOfStrings) {
/* 126 */     if (listOfStrings.size() < 1) return ""; 
/* 127 */     text = "<ul>";
/* 128 */     Iterator it = listOfStrings.iterator();
/* 129 */     while (it.hasNext())
/* 130 */       text = String.valueOf(text) + "<li>" + htmlAble(it.next()) + "</li>"; 
/* 131 */     return String.valueOf(text) + "</ul>";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String commaLinkList(ArrayList list) {
/* 136 */     String text = " ";
/* 137 */     Iterator it = list.iterator();
/* 138 */     while (it.hasNext())
/* 139 */       text = String.valueOf(text) + linkTo((AbstractEntityInterface)it.next()) + ", "; 
/* 140 */     if (text.lastIndexOf(",") > 0) text = text.substring(0, text.lastIndexOf(",")); 
/* 141 */     return text;
/*     */   }
/*     */   
/*     */   protected String image(String relativePath) {
/* 145 */     String workDir = System.getProperty("user.dir");
/* 146 */     return "<img src=file://" + workDir + System.getProperty("file.separator") + relativePath;
/*     */   }
/*     */   
/*     */   protected static String getAccessModeHTML(int mode) {
/* 150 */     switch (mode) {
/*     */       case 1:
/* 152 */         return "<font bgcolor=#C0F0C0>public</font>";
/*     */       case 2:
/* 154 */         return "<font bgcolor=#E0FFA0>protected</font>";
/*     */       case 3:
/* 156 */         return "<font bgcolor=#FFE0A0></font>";
/*     */       case 4:
/* 158 */         return "<font bgcolor=#FFC0C0>private</font>";
/*     */     } 
/* 160 */     return "<font bgcolor=#D0D0D0>unknown access mode</font>";
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\details\HTMLDetail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */