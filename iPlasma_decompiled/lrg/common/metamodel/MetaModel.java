/*     */ package lrg.common.metamodel;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.entities.ResultEntity;
/*     */ 
/*     */ public class MetaModel
/*     */ {
/*     */   private static MetaModel theMM;
/*     */   private static String currentSourcePath;
/*     */   
/*  15 */   public static MetaModel instance() { return theMM; }
/*     */   private ModelBuilder modelBuilder; private HashMap addressMap;
/*     */   
/*     */   public static void createFrom(ModelBuilder builder, String sourcePath) throws Exception {
/*  19 */     if (theMM == null || currentSourcePath.compareTo(sourcePath) != 0) {
/*  20 */       currentSourcePath = sourcePath;
/*  21 */       theMM = new MetaModel(builder);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void closeMetaModel() {
/*  33 */     if (theMM != null)
/*  34 */       theMM.modelBuilder.cleanModel(); 
/*  35 */     theMM = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public MetaModel(ModelBuilder builder) throws Exception {
/*  40 */     this.modelBuilder = null;
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
/*  61 */     this.modelBuilder = builder;
/*  62 */     if (this.modelBuilder != null) {
/*  63 */       this.modelBuilder.buildModel();
/*  64 */       this.addressMap = this.modelBuilder.getAddressMap();
/*     */     } 
/*     */   }
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
/*  90 */   public AbstractEntityInterface findEntityByAddress(String anAddress) { return (AbstractEntityInterface)this.addressMap.get(anAddress); }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList findAddressesThatStartWith(String prefix) {
/*  95 */     ArrayList matchingAddress = new ArrayList();
/*     */     
/*  97 */     Iterator addressIterator = this.addressMap.keySet().iterator();
/*  98 */     while (addressIterator.hasNext()) {
/*  99 */       String currentAddress = (String)addressIterator.next();
/* 100 */       if (currentAddress.startsWith(prefix)) {
/* 101 */         matchingAddress.add(currentAddress);
/*     */       }
/*     */     } 
/* 104 */     return matchingAddress;
/*     */   }
/*     */   
/*     */   public void addGroupToAddressMap(GroupEntity anEntity) {
/* 108 */     ResultEntity aResultEntity = anEntity.getProperty("Address");
/* 109 */     if (aResultEntity != null) {
/* 110 */       String tmp = (String)aResultEntity.getValue();
/* 111 */       this.addressMap.put(tmp, anEntity);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\metamodel\MetaModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */