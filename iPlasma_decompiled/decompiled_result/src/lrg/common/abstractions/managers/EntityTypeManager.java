/*     */ package lrg.common.abstractions.managers;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.EntityType;
/*     */ import lrg.common.abstractions.plugins.AbstractPlugin;
/*     */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*     */ 
/*     */ 
/*     */ public class EntityTypeManager
/*     */ {
/*  17 */   private static HashMap entityTypes = new HashMap();
/*     */ 
/*     */   
/*  20 */   private static EntityType findEntityType(String entityTypeName) { return (EntityType)entityTypes.get(entityTypeName); }
/*     */ 
/*     */ 
/*     */   
/*  24 */   public static ArrayList getAllPropertiesForName(String entityTypeName) { return findEntityType(entityTypeName).nameAllPropertyComputers(); }
/*     */ 
/*     */ 
/*     */   
/*  28 */   public static ArrayList getAllGroupsForName(String entityTypeName) { return findEntityType(entityTypeName).nameAllGroupBuilders(); }
/*     */ 
/*     */ 
/*     */   
/*  32 */   public static ArrayList getAllFiltersForName(String entityTypeName) { return findEntityType(entityTypeName).nameAllFilteringRules(); }
/*     */ 
/*     */   
/*     */   public static EntityType getEntityTypeForName(String entityTypeName) {
/*  36 */     EntityType theEntityType = findEntityType(entityTypeName);
/*  37 */     if (theEntityType == null) {
/*  38 */       theEntityType = new EntityType(entityTypeName);
/*  39 */       entityTypes.put(entityTypeName, theEntityType);
/*     */     } 
/*  41 */     return theEntityType;
/*     */   }
/*     */ 
/*     */   
/*  45 */   public static Collection getAllTypes() { return entityTypes.values(); }
/*     */ 
/*     */   
/*     */   public static ArrayList getAllSubtypesForName(String entityTypeName) {
/*  49 */     ArrayList subTypes = new ArrayList();
/*     */     
/*  51 */     Iterator it = entityTypes.values().iterator();
/*     */     
/*  53 */     while (it.hasNext()) {
/*  54 */       EntityType entityType = (EntityType)it.next();
/*  55 */       if (entityType.getSupertypeName().compareTo(entityTypeName) == 0)
/*  56 */         subTypes.add(entityType); 
/*     */     } 
/*  58 */     return subTypes;
/*     */   }
/*     */   
/*     */   public static void registerEntityType(String entityTypeName, String supertypeName) {
/*  62 */     EntityType newEntityType = new EntityType(entityTypeName, supertypeName);
/*  63 */     entityTypes.put(entityTypeName, newEntityType);
/*     */   }
/*     */   
/*     */   public static void attach(AbstractPlugin someCommand) {
/*  67 */     String[] entityTypeNames = someCommand.getDescriptorObject().getAllEntityTypeNames();
/*     */ 
/*     */     
/*  70 */     for (int i = 0; i < entityTypeNames.length; i++) {
/*  71 */       EntityType anEntityType = getEntityTypeForName(entityTypeNames[i]);
/*  72 */       anEntityType.attach(someCommand);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void attach(AbstractPlugin someCommand, String entityTypeName) {
/*  77 */     EntityType anEntityType = getEntityTypeForName(entityTypeName);
/*  78 */     anEntityType.attach(someCommand);
/*     */   }
/*     */   
/*     */   public static void unAttach(FilteringRule aRule) {
/*  82 */     EntityType anEntityType = getEntityTypeForName(aRule.getDescriptorObject().getEntityTypeName());
/*  83 */     anEntityType.unAttach(aRule);
/*     */   }
/*     */   
/*     */   public static boolean loadFromCache() {
/*  87 */     if (!CacheManager.getStaticETCache().exists()) return false; 
/*     */     try {
/*  89 */       serin = CacheManager.readStaticETStream();
/*  90 */       entityTypes = (HashMap)serin.readObject();
/*  91 */       serin.close();
/*  92 */       return true;
/*  93 */     } catch (IOException e) {
/*  94 */       System.err.println("ERROR: Unable to load from cache !");
/*  95 */       return false;
/*  96 */     } catch (ClassNotFoundException e) {
/*  97 */       System.err.println("ERROR: Unable to load from cache !");
/*  98 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeStaticEntityTypesToCache() {
/*     */     try {
/* 104 */       serout = CacheManager.writeStaticETStream();
/* 105 */       serout.writeObject(entityTypes);
/* 106 */       serout.close();
/* 107 */     } catch (IOException e) {
/* 108 */       System.err.println("ERROR: Unable to write to cache !");
/* 109 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\managers\EntityTypeManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */