/*     */ package classes.lrg.insider.plugins.core.properties;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.plugins.details.AbstractDetail;
/*     */ import lrg.common.metamodel.MetaModel;
/*     */ import lrg.insider.plugins.core.properties.AbstractDetail;
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
/*     */ public abstract class AbstractDetail
/*     */   extends AbstractDetail
/*     */ {
/*  26 */   private static String htmlAble(String s) { return s.replaceAll(">", "&gt;").replaceAll("<", "&lt;"); }
/*     */ 
/*     */   
/*  29 */   public AbstractDetail(String name, String longName, String entityType, String resultEntityTypeName) { super(name, longName, entityType); }
/*     */ 
/*     */ 
/*     */   
/*  33 */   public AbstractDetail(String name, String longName, String[] entityTypes, String resultEntityTypeName) { super(name, longName, entityTypes); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public static String linkToNumber(GroupEntity anEntity) { return "<a href=\"" + anEntity.getProperty("Address") + "\" style=\"text-decoration:none\">" + anEntity.size() + "</a>"; }
/*     */ 
/*     */   
/*     */   public static String linkTo(AbstractEntityInterface anEntity) {
/*  42 */     return 
/*  43 */       "<a href=\"" + anEntity.getProperty("Address") + "\" " + 
/*  44 */       "style=\"text-decoration:none\">" + 
/*  45 */       htmlAble(anEntity.getProperty("Name")) + 
/*  46 */       "</a>";
/*     */   }
/*     */ 
/*     */   
/*  50 */   protected String linkTo(String name, String address) { return "<a href=\"" + address + "\" style=\"text-decoration:none\">" + htmlAble(name) + "</a>"; }
/*     */ 
/*     */   
/*     */   protected String linkTo(String address) {
/*  54 */     AbstractEntityInterface anEntity = MetaModel.instance().findEntityByAddress(address);
/*     */     
/*  56 */     if (anEntity != null) {
/*  57 */       return linkTo(anEntity);
/*     */     }
/*  59 */     return address;
/*     */   }
/*     */   
/*     */   protected String bulletedLinkList(Collection listOfEntities, ListDecorator ld) {
/*  63 */     if (listOfEntities.size() < 1) return ""; 
/*  64 */     text = "<ul>";
/*  65 */     Iterator it = listOfEntities.iterator();
/*  66 */     while (it.hasNext()) {
/*  67 */       AbstractEntityInterface theEntity = (AbstractEntityInterface)it.next();
/*  68 */       text = String.valueOf(text) + "<li>" + ((ld != null) ? ld.getBeforeDecoration(theEntity) : "") + 
/*  69 */         " " + linkTo(theEntity) + " " + (
/*  70 */         (ld != null) ? ld.getAfterDecoration(theEntity) : "") + "</li>";
/*     */     } 
/*  72 */     return String.valueOf(text) + "</ul>";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  77 */   protected String bulletedLinkList(Collection listOfEntities) { return bulletedLinkList(listOfEntities, null); }
/*     */ 
/*     */   
/*     */   protected String bulletedList(ArrayList listOfStrings) {
/*  81 */     if (listOfStrings.size() < 1) return ""; 
/*  82 */     text = "<ul>";
/*  83 */     Iterator it = listOfStrings.iterator();
/*  84 */     while (it.hasNext())
/*  85 */       text = String.valueOf(text) + "<li>" + htmlAble(it.next()) + "</li>"; 
/*  86 */     return String.valueOf(text) + "</ul>";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String commaLinkList(ArrayList list) {
/*  91 */     text = "";
/*  92 */     Iterator it = list.iterator();
/*  93 */     while (it.hasNext())
/*  94 */       text = String.valueOf(text) + linkTo((AbstractEntityInterface)it.next()) + ", "; 
/*  95 */     return text.substring(0, text.lastIndexOf(","));
/*     */   }
/*     */ 
/*     */   
/*     */   protected String image(String relativePath) {
/* 100 */     String workDir = System.getProperty("user.dir");
/* 101 */     return "<img src=file://" + workDir + System.getProperty("file.separator") + relativePath;
/*     */   }
/*     */   
/*     */   protected static String getAccessModeHTML(int mode) {
/* 105 */     switch (mode) {
/*     */       case 1:
/* 107 */         return "<font bgcolor=#C0F0C0>public</font>";
/*     */       case 2:
/* 109 */         return "<font bgcolor=#E0FFA0>protected</font>";
/*     */       case 3:
/* 111 */         return "<font bgcolor=#FFE0A0></font>";
/*     */       case 4:
/* 113 */         return "<font bgcolor=#FFC0C0>private</font>";
/*     */     } 
/* 115 */     return "<font bgcolor=#D0D0D0>unknown access mode</font>";
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\AbstractDetail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */