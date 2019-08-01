/*     */ package classes.lrg.insider.metamodel;
/*     */ import lrg.common.abstractions.managers.EntityTypeManager;
/*     */ import lrg.insider.metamodel.Address;
/*     */ import lrg.insider.metamodel.HismoModelBuilder;
/*     */ import lrg.memoria.hismo.core.AbstractHistory;
/*     */ import lrg.memoria.hismo.core.AttributeHistory;
/*     */ import lrg.memoria.hismo.core.ClassHistory;
/*     */ import lrg.memoria.hismo.core.GlobalFunctionHistory;
/*     */ import lrg.memoria.hismo.core.GlobalVariableHistory;
/*     */ import lrg.memoria.hismo.core.LocalVariableHistory;
/*     */ import lrg.memoria.hismo.core.MethodHistory;
/*     */ import lrg.memoria.hismo.core.NamespaceHistory;
/*     */ 
/*     */ public class HismoModelBuilder extends ModelBuilder {
/*     */   private SystemHistory currentSystemHistory;
/*     */   private File cachesDir;
/*     */   
/*     */   public HismoModelBuilder(String cachesDir, String name, ProgressObserver observer) {
/*  19 */     this.cachesDir = new File(cachesDir);
/*  20 */     this.name = name;
/*  21 */     this.observer = observer;
/*     */   }
/*     */   private String name; private ProgressObserver observer;
/*     */   public void buildModel() throws Exception {
/*  25 */     loadModel();
/*  26 */     createEntityTypes();
/*  27 */     attachSpecificEntityTypes();
/*     */   }
/*     */   
/*     */   public void cleanModel() throws Exception {
/*  31 */     for (Iterator it = this.currentSystemHistory.getVersionIterator(); it.hasNext();)
/*  32 */       System.unloadSystemFromMemory((System)it.next()); 
/*  33 */     this.currentSystemHistory = null;
/*     */   }
/*     */ 
/*     */   
/*  37 */   protected void loadModel() throws Exception { this.currentSystemHistory = (new HismoLoader(this.cachesDir, this.name, this.observer)).getSystemHistory(); }
/*     */ 
/*     */ 
/*     */   
/*     */   private void attachSpecificEntityTypes() throws Exception {
/*  42 */     for (AbstractHistory temp : this.currentSystemHistory.getNamespaceHistories().getHistoriesCollection()) {
/*     */       
/*  44 */       NamespaceHistory currentNamespaceHistory = (NamespaceHistory)temp;
/*  45 */       GlobalFunctionHistoryGroup gfhg = currentNamespaceHistory.getGlobalFunctionHistories();
/*  46 */       for (AbstractHistory tmp : gfhg.getHistoriesCollection()) {
/*  47 */         GlobalFunctionHistory currentGlobalFunctionHistory = (GlobalFunctionHistory)tmp;
/*  48 */         addLocalVariablesHistories(currentGlobalFunctionHistory);
/*     */         
/*  50 */         currentGlobalFunctionHistory.setEntityType(EntityTypeManager.getEntityTypeForName("global function history"));
/*  51 */         this.addressMap.put(Address.buildFor(currentGlobalFunctionHistory), currentGlobalFunctionHistory);
/*     */       } 
/*     */       
/*  54 */       GlobalVariableHistoryGroup gvhg = currentNamespaceHistory.getGlobalVariableHistories();
/*  55 */       for (AbstractHistory tmp : gvhg.getHistoriesCollection()) {
/*  56 */         GlobalVariableHistory currentGlobalVariableHistory = (GlobalVariableHistory)tmp;
/*  57 */         currentGlobalVariableHistory.setEntityType(EntityTypeManager.getEntityTypeForName("global variable history"));
/*  58 */         this.addressMap.put(Address.buildFor(currentGlobalVariableHistory), currentGlobalVariableHistory);
/*     */       } 
/*     */       
/*  61 */       ClassHistoryGroup chg = currentNamespaceHistory.getClassHistories();
/*  62 */       for (AbstractHistory tmp : chg.getHistoriesCollection()) {
/*  63 */         ClassHistory currentClassHistory = (ClassHistory)tmp;
/*     */         
/*  65 */         AttributeHistoryGroup ahg = currentClassHistory.getAttributeHistories();
/*  66 */         for (AbstractHistory atmp : ahg.getHistoriesCollection()) {
/*  67 */           AttributeHistory currentAttributeHistory = (AttributeHistory)atmp;
/*  68 */           currentAttributeHistory.setEntityType(EntityTypeManager.getEntityTypeForName("attribute history"));
/*  69 */           this.addressMap.put(Address.buildFor(currentAttributeHistory), currentAttributeHistory);
/*     */         } 
/*     */         
/*  72 */         MethodHistoryGroup mhg = currentClassHistory.getMethodHistories();
/*  73 */         for (AbstractHistory mtmp : mhg.getHistoriesCollection()) {
/*  74 */           MethodHistory currentMethodHistory = (MethodHistory)mtmp;
/*  75 */           addLocalVariablesHistories(currentMethodHistory);
/*     */           
/*  77 */           currentMethodHistory.setEntityType(EntityTypeManager.getEntityTypeForName("method history"));
/*  78 */           this.addressMap.put(Address.buildFor(currentMethodHistory), currentMethodHistory);
/*     */         } 
/*     */         
/*  81 */         currentClassHistory.setEntityType(EntityTypeManager.getEntityTypeForName("class history"));
/*  82 */         this.addressMap.put(Address.buildFor(currentClassHistory), currentClassHistory);
/*     */       } 
/*     */       
/*  85 */       currentNamespaceHistory.setEntityType(EntityTypeManager.getEntityTypeForName("namespace history"));
/*  86 */       this.addressMap.put(Address.buildFor(currentNamespaceHistory), currentNamespaceHistory);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     this.currentSystemHistory.setEntityType(EntityTypeManager.getEntityTypeForName("system history"));
/* 120 */     this.addressMap.put(Address.buildForRoot(), this.currentSystemHistory);
/*     */   }
/*     */ 
/*     */   
/*     */   private void addLocalVariablesHistories(FunctionHistory currentFunctionHistory) {
/* 125 */     LocalVariableHistoryGroup lvhg = currentFunctionHistory.getLocalVariableHistories();
/* 126 */     for (AbstractHistory tmp : lvhg.getHistoriesCollection()) {
/* 127 */       LocalVariableHistory currentLocalVariableHistory = (LocalVariableHistory)tmp;
/* 128 */       currentLocalVariableHistory.setEntityType(EntityTypeManager.getEntityTypeForName("local variable history"));
/* 129 */       this.addressMap.put(Address.buildFor(currentLocalVariableHistory), currentLocalVariableHistory);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void registerEntityTypes() throws Exception {
/* 134 */     EntityTypeManager.registerEntityType("result", "");
/* 135 */     EntityTypeManager.registerEntityType("numerical", "");
/* 136 */     EntityTypeManager.registerEntityType("boolean", "");
/* 137 */     EntityTypeManager.registerEntityType("string", "");
/*     */     
/* 139 */     EntityTypeManager.registerEntityType("system history", "");
/* 140 */     EntityTypeManager.registerEntityType("package history", "system history");
/* 141 */     EntityTypeManager.registerEntityType("namespace history", "system history");
/* 142 */     EntityTypeManager.registerEntityType("class history", "package history");
/* 143 */     EntityTypeManager.registerEntityType("type history", "namespace history");
/* 144 */     EntityTypeManager.registerEntityType("method history", "class history");
/* 145 */     EntityTypeManager.registerEntityType("attribute history", "class history");
/*     */     
/* 147 */     EntityTypeManager.registerEntityType("global function history", "package history");
/* 148 */     EntityTypeManager.registerEntityType("global variable history", "package history");
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\metamodel\HismoModelBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */